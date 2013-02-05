#ifndef H_ENGINE
#define H_ENGINE
#include<coordinator.h>

typedef struct _state *state_t;
typedef struct _transition *transition_t;
typedef struct _equation *equation_t;
typedef struct _predicate *predicate_t;

typedef void *(*transform_t)(void * restrict input, int *input_length);
typedef int (*predfunc_t)(const void * restrict input, int input_length);

struct _equation {
	port_t sink, source;
	transform_t *transforms;
	size_t transforms_length;
};

struct _predicate {
	port_t target;
	predfunc_t *predfuncs;
	size_t predfuncs_length;
};

struct _transition {
	state_t target;
	port_t *ports;
	size_t ports_length;
	predicate_t predicate;
	equation_t *conjunction;
	size_t conjunction_length;
};

struct _state {
	char *name;
	enum BOOLEAN start;
	transition_t *outtrans ;
	size_t outtrans_length;
	port_t *cells;
	size_t cells_length;
};

int do_step(state_t * restrict current);
#endif
