CC = gcc 
CFLAGS = -g -Wall -I${INCLUDE}

#LDFLAGS=/home/cs352/fall14/assign9
#LIBS=shuffle

INCLUDE=./


all:    catalog

catalog: catalog.o processOpts.o processFiles.o tree.o
	${CC} ${CFLAGS} -o catalog catalog.o processOpts.o processFiles.o tree.o

catalog.o: catalog.c catalog.h
	${CC} ${CFLAGS} -c catalog.c catalog.h

# Target for processOpts.o
processOpts.o: processOpts.c
	${CC} ${CFLAGS} -c processOpts.c

# Target for processFiles.o
processFiles.o: processFiles.c tree.h
	${CC} ${CFLAGS} -c processFiles.c tree.h

# Target for tree.o
tree.o: tree.c tree.h
	${CC} ${CFLAGS} -c tree.c tree.h

# Target for processInstruction.o
#processInstruction.o: processInstruction.c regCodes.h opCodes.h formats.h functCodes.h
#	${CC} ${CFLAGS} -c processInstruction.c regCodes.h opCodes.h formats.h functCodes.h

# Add a clean target that removes the .o files
clean:
	rm *.o
