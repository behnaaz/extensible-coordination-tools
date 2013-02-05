#include<stdlib.h>
#include <unistd.h>
#include<stdio.h>
#include<pthread.h>

#include<ports.h>
#include<internals.h>

#define MAXSLEEP 1000000

void *reader(void * restrict port)
{
	port_t p = (port_t) port;
	unsigned int seed;
	int val;
	printf("Reader #%lx reading from port %s[%s]\n",pthread_self(),port_name(p),port_type(p));
	for (int i=0; i<100; i++)
	{
		usleep(rand_r(&seed)%MAXSLEEP);
		port_read(p, &val, sizeof(int));
		printf("reader got %d\n",val);		
	}

	return NULL;
}

void *writer(void * restrict port)
{
	port_t p = (port_t) port;
	unsigned int seed;
	printf("Writer #%lx writing to port %s[%s]\n",pthread_self(),port_name(p),port_type(p));
	for (int i=100; i>0; i--) 
	{
		usleep(rand_r(&seed)%MAXSLEEP);
		printf("writer put %d\n",i);
		port_write(p, &i, sizeof(int));
	}

	return NULL;
}

int main (int argc, char *argv[])
{
	port_t a,b;
	b = port_init("B", sink_port);
	a = port_init("A", source_port);

	equation_t eq = malloc(sizeof(struct _equation));
	eq->source = a;
	eq->sink = b;
	eq->transforms_length=0;

	transition_t trans = malloc(sizeof(struct _transition));
	trans->ports = malloc(sizeof(struct _port)*2);
	trans->ports[0]=a;
	trans->ports[1]=b;
	trans->ports_length = 2;
	trans->conjunction = &eq;
	trans->conjunction_length = 1;

	state_t init = malloc(sizeof(struct _state));
	init->start = true;
	trans->target = init;
	init->outtrans = &trans;
	init->outtrans_length = 1;
	
	pthread_t reader_thr, writer_thr, coord_thr;
	puts("starting engine...\n");
	pthread_create(&coord_thr, NULL, &coordinator_start, &init);
	pthread_create(&writer_thr, NULL, &writer, a);
	pthread_create(&reader_thr, NULL, &reader, b);

	pthread_join(writer_thr, NULL);
	pthread_join(reader_thr, NULL);
	puts("stopping engine...\n");
	coordinator_stop();
	return 0;
}

