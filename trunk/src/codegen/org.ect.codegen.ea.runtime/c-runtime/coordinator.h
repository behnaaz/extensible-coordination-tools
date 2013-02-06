#ifndef H_COMMON
#define H_COMMON

#include <ports.h>

enum BOOLEAN { false, true };
enum TXNSTATE { none, read_pending, write_pending, executing, complete };
enum PORTTYPE { source_port,  sink_port, buffer };

struct _port {
	const char *name;
	enum PORTTYPE type;
	pthread_mutex_t mutex;
	pthread_cond_t txn_complete;
	enum TXNSTATE txn_state;
	volatile void *data_value;
	volatile int data_size;
};

/**
These functions are for internal use by the Reo engine
*/
#define BUF_SZ 1024;
#define port_lock(port)		pthread_mutex_lock(&port->mutex)
#define port_unlock(port)	pthread_mutex_unlock(&port->mutex)

#define port_signal(port)	pthread_cond_signal(&port->txn_complete)
#define port_wait(port,cond) 	{while (cond) \
					pthread_cond_wait(&port->txn_complete, &port->mutex); }

extern port_t port_init(const char * restrict name, enum PORTTYPE type) ;
extern void port_destroy(port_t restrict port);

extern void coordinator_notify(port_t restrict port);
void * coordinator_start(void * restrict startstates);
void coordinator_stop();

#endif 
