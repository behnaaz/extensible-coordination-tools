#ifndef H_PORTS
#define H_PORTS

#include<pthread.h>
#include<time.h>

/**
	This header defines the public port interface functions.
	The port_t struct should be treated as strictly opaque by components.
	It is liable to change so only use the macros below to access it!
*/
typedef struct _port *port_t; 

#define port_name(port)	port->name	
#define port_type(port)	(port->type==sink_port ? "sink": port->type==source_port ? "source" : "buffer")

extern int port_read(/*IN/OUT*/ port_t restrict sink, /*IN/OUT*/ void *result, /*IN*/ size_t size);
extern int port_write(/*IN/OUT*/ port_t restrict source, /*IN*/ void *value, /*IN*/ size_t size);

extern int timed_read(/*IN/OUT*/ port_t restrict sink, /*IN/OUT*/void *result, /*IN*/ size_t size, 
		/*IN*/ const struct timespec *restrict abstime);
extern int timed_write(/*IN/OUT*/ port_t restrict source, /*IN*/ void *value, /*IN*/ size_t size, 
		/*IN*/ const struct timespec *restrict abstime);

#endif 
