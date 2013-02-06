# source files.
SRC =  	engine.c ports.c

OBJ = $(SRC:.c=.o)

OUT = ../libca.a

# include directories
INCLUDES = -I. -I/usr/include/linux

# C++ compiler flags (-g -O2 -Wall)
CCFLAGS = -std=c99 -Wall -g 

# compiler
CC = gcc

# library paths
#LIBS = -L../ -L/usr/local/lib -lm

# compile flags
LDFLAGS = -fpic -shared -Wl,-soname,$(OUT)

.SUFFIXES: .c

default: dep $(OUT)

.c.o:
	$(CC) $(INCLUDES) $(CFLAGS) -c $< -o $@

$(OUT): $(OBJ)
	ar rcs $(OUT) $(OBJ)

depend: dep

dep:
	makedepend -- $(CFLAGS) -- $(INCLUDES) $(SRC)

clean:
	rm -f $(OBJ) $(OUT) Makefile.bak
# DO NOT DELETE

engine.o: /usr/include/pthread.h /usr/include/features.h
engine.o: /usr/include/sys/cdefs.h /usr/include/bits/wordsize.h
engine.o: /usr/include/gnu/stubs.h /usr/include/gnu/stubs-64.h
engine.o: /usr/include/endian.h /usr/include/bits/endian.h
engine.o: /usr/include/linux/sched.h /usr/include/linux/auxvec.h
engine.o: /usr/include/asm/auxvec.h /usr/include/asm-x86_64/auxvec.h
engine.o: /usr/include/linux/time.h /usr/include/linux/types.h
engine.o: /usr/include/linux/posix_types.h /usr/include/linux/stddef.h
engine.o: /usr/include/asm/posix_types.h
engine.o: /usr/include/asm-x86_64/posix_types.h /usr/include/asm/types.h
engine.o: /usr/include/asm-x86_64/types.h /usr/include/linux/signal.h
engine.o: /usr/include/asm/signal.h /usr/include/asm-x86_64/signal.h
engine.o: /usr/include/linux/time.h /usr/include/asm-generic/signal.h
engine.o: /usr/include/asm/siginfo.h /usr/include/asm-x86_64/siginfo.h
engine.o: /usr/include/asm-generic/siginfo.h /usr/include/bits/pthreadtypes.h
engine.o: /usr/include/bits/setjmp.h /usr/include/stdlib.h
engine.o: /usr/include/linux/stddef.h /usr/include/sys/types.h
engine.o: /usr/include/bits/types.h /usr/include/bits/typesizes.h
engine.o: /usr/include/sys/select.h /usr/include/bits/select.h
engine.o: /usr/include/bits/sigset.h /usr/include/bits/time.h
engine.o: /usr/include/sys/sysmacros.h /usr/include/alloca.h
engine.o: /usr/include/string.h /usr/include/assert.h ./engine.h ./common.h
engine.o: ./ports.h
ports.o: /usr/include/stdlib.h /usr/include/features.h
ports.o: /usr/include/sys/cdefs.h /usr/include/bits/wordsize.h
ports.o: /usr/include/gnu/stubs.h /usr/include/gnu/stubs-64.h
ports.o: /usr/include/linux/stddef.h /usr/include/sys/types.h
ports.o: /usr/include/bits/types.h /usr/include/bits/typesizes.h
ports.o: /usr/include/linux/time.h /usr/include/linux/types.h
ports.o: /usr/include/linux/posix_types.h /usr/include/linux/stddef.h
ports.o: /usr/include/asm/posix_types.h /usr/include/asm-x86_64/posix_types.h
ports.o: /usr/include/asm/types.h /usr/include/asm-x86_64/types.h
ports.o: /usr/include/endian.h /usr/include/bits/endian.h
ports.o: /usr/include/sys/select.h /usr/include/bits/select.h
ports.o: /usr/include/bits/sigset.h /usr/include/bits/time.h
ports.o: /usr/include/sys/sysmacros.h /usr/include/bits/pthreadtypes.h
ports.o: /usr/include/alloca.h /usr/include/assert.h ./common.h ./ports.h
ports.o: /usr/include/pthread.h /usr/include/linux/sched.h
ports.o: /usr/include/linux/auxvec.h /usr/include/asm/auxvec.h
ports.o: /usr/include/asm-x86_64/auxvec.h /usr/include/linux/signal.h
ports.o: /usr/include/asm/signal.h /usr/include/asm-x86_64/signal.h
ports.o: /usr/include/linux/time.h /usr/include/asm-generic/signal.h
ports.o: /usr/include/asm/siginfo.h /usr/include/asm-x86_64/siginfo.h
ports.o: /usr/include/asm-generic/siginfo.h /usr/include/bits/setjmp.h
