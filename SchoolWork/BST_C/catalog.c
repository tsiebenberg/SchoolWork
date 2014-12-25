/*
	Taylor Siebenberg
	cs352
	disAssemble.c
	A mini disassembler for mips assembly language
*/

#include <stdio.h>
#include <stdlib.h>
#include "catalog.h"

#define FALSE 0
#define TRUE 1

int main(int argc, char *argv[]) {

	int i, numFiles, rFlag, iFlag;
	char *files[10];//, *progName = argv[0];

	numFiles = 0;
	rFlag = iFlag = FALSE;
	
	processOpts(argc, argv, files, &numFiles, &rFlag, &iFlag);   // process the command line arguments

	for(i = 0; i < numFiles; i++) {
		processFiles(files[i], rFlag, iFlag);
	}

//  TEST PROCESSOPTS
/*
	printf("rFlag = %d\niFlag = %d\nnumFiles = %d\n", rFlag, iFlag, numFiles);
	for(i = 0; i < numFiles; i++) {
		printf("files[%d] = %s\n", i, files[i]);
	}
*/


		

	return 0;

}  // main
