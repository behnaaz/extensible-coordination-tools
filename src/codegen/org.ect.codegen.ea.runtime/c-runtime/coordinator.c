#include<pthread.h>
#include <stdlib.h>
#include<assert.h>

#include<internals.h>

pthread_mutex_t engine_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t engine_cond = PTHREAD_COND_INITIALIZER;
short terminate=0;

port_t port_init(const char * restrict name, enum PORTTYPE type) 
{
	port_t restrict port = (port_t) malloc(sizeof(struct _port));
	port->name = name;
	port->type = type;
		
	pthread_mutexattr_t mattr;
#ifdef __USE_UNIX98
	pthread_mutexattr_settype(&mattr, PTHREAD_MUTEX_ERRORCHECK);
#endif
	int ret = pthread_mutex_init(&port->mutex, &mattr);
	assert (ret==0);
	ret = pthread_cond_init(&port->txn_complete, NULL);
	assert (ret==0);

	return port;
}

void port_destroy(port_t restrict port)
{
	assert (port!=NULL);
	//free(port->name);
	pthread_mutex_destroy(&port->mutex);
	pthread_cond_destroy(&port->txn_complete);
	free(port);
}

void coordinator_notify(port_t restrict port) 
{
	//Wake up the engine thread
	pthread_mutex_lock(&engine_mutex);
	pthread_cond_signal(&engine_cond);
	pthread_mutex_unlock(&engine_mutex);
}

void * coordinator_start(void * restrict startstates) 
{
	state_t current=((state_t *)startstates)[0];

	while(!terminate)
	{
		pthread_mutex_lock(&engine_mutex);
		if (!do_step(&current))
			pthread_cond_wait(&engine_cond, &engine_mutex);

		pthread_mutex_unlock(&engine_mutex);
	}
	return NULL;
}

void coordinator_stop()
{
	//Wake up the engine thread
	pthread_mutex_lock(&engine_mutex);
	terminate=1;
	pthread_cond_signal(&engine_cond);
	pthread_mutex_unlock(&engine_mutex);
}
