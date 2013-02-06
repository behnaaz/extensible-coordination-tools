#include<pthread.h>
#include <stdlib.h>
#include <string.h>
#include<assert.h>

#include<internals.h>


static void execute_equation(const equation_t restrict eq) 
{
	//eq->sink->data_size = eq->source->data_size;	
	assert (eq->sink->data_size >0);

	//eq->sink->data_value = malloc(eq->sink->data_size);
	assert( eq->sink->data_value!=NULL && 
		eq->source->data_value!=NULL );
	memcpy( eq->sink->data_value, 
		eq->source->data_value, 
		eq->sink->data_size);

	int i;
	for (i=eq->transforms_length-1; i>=0; i--)
		eq->sink->data_value = 
			eq->transforms[i](eq->sink->data_value, &eq->sink->data_size); 
}
	
static void execute_constraint(const equation_t * restrict equations, size_t length) 
{
	int i;
	//Start txn -- should block until all readers and writers are asleep
	for (i=0; i<length; i++) 
	{
		port_lock(equations[i]->source);
		port_lock(equations[i]->sink);
		equations[i]->source->txn_state = executing;	
		equations[i]->sink->txn_state = executing;	
	}
	
	//Do the actual data transfer
	for (i=0; i<length; i++) 
		execute_equation(equations[i]);

	//Close txn
	for (i=0; i<length; i++) 
	{
//		equations[i]->source->data_value = NULL;
//		equations[i]->source->data_size = 0;		
		equations[i]->source->txn_state = complete;	
		equations[i]->sink->txn_state = complete;	

		port_signal(equations[i]->source);
		port_signal(equations[i]->sink);
		port_unlock(equations[i]->sink);
		port_unlock(equations[i]->source);
	}
}

static int test_predicate(const predicate_t  restrict pred) 
{
	if (pred==NULL)
		return 1;

	port_lock(pred->target);
	for (int i=0; i<pred->predfuncs_length; i++) 
	{
		void *input = pred->target;
		int input_len = pred->target->data_size; 		

		if (pred->predfuncs[i](input, input_len)==0)
		{
//			pred->target = NULL;
//			pred->target->data_size = -1; 		
			pred->target->txn_state = complete; 		
			
			port_signal(pred->target);
			port_unlock(pred->target);
			return 0;
		}
	}
	port_unlock(pred->target);

	return 1;
}

static int test_transition(const transition_t restrict tr)
{
	int i, ready=1;
	for (i=0; i<tr->ports_length; i++) 
	{	
		ready = (tr->ports[i]->type==sink_port && tr->ports[i]->txn_state==read_pending) ||
		 	(tr->ports[i]->type==source_port && tr->ports[i]->txn_state==write_pending);
		if (!ready) return 0;
	}

	return test_predicate(tr->predicate);
}

int do_step(state_t * restrict current) 
{
	for (int i=0; i<(*current)->outtrans_length; i++)
	{
		transition_t tr = (*current)->outtrans[i];
		if (test_transition(tr))
		{
			execute_constraint(tr->conjunction,tr->conjunction_length);
			*current = tr->target;
			return 1;
		}
	}

	return 0;
}
