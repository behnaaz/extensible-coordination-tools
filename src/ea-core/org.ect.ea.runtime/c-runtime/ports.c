#include <stdlib.h>
#include<assert.h>

#include<coordinator.h>
#include<ports.h>


int port_read(port_t restrict sink, void *result, size_t size)
{
	assert (sink!=NULL && result!=NULL && size>0);
	assert (sink->type==sink_port);

	int ret = port_lock(sink);	
	assert(ret==0);
	sink->txn_state = read_pending;
	
	sink->data_value = result;
	sink->data_size = size;

	coordinator_notify(sink);//callback
	port_wait(sink, sink->txn_state!=complete);

	assert (size==sink->data_size);

	ret = port_unlock(sink);
	assert(ret==0);

	return 0;
}

int port_write(port_t restrict source, void * value, size_t size)
{
	assert (source!=NULL && value!=NULL && size>0);
	assert (source->type==source_port);

	int ret = port_lock(source);	
	assert(ret==0);
	//Here we just duplicate the pointer.
	//The actual copy is done in execute_constraint()
	source->data_value = value;
	source->data_size = size;
	source->txn_state = write_pending;
	
	coordinator_notify(source);//callback
	port_wait(source, source->txn_state!=complete);

	ret = port_unlock(source);
	assert(ret==0);

	return 0;
}

int timed_read(port_t restrict sink, void *result, size_t size,
		const struct timespec *restrict abstime)
{
	return 0;
}

int timed_write(port_t restrict source, void *value, size_t  size, 
		const struct timespec *restrict abstime)
{
	return 0;
}
	
