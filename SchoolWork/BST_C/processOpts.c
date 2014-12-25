/*
	Taylor Siebenberg
	cs352
	processOpts.c
	Process the command line arguments given for disAssemble.c
*/

#include <stdio.h>
#include <stdlib.h>
//#include <string.h>
#include <getopt.h>
//#include "processOpts.h"

#define FALSE 0
#define TRUE 1

void usage(char *progName, FILE *file, int exitValue);

struct option longopts[] = { 
    {"reverse",   	no_argument, 		NULL,   'r'},
    {"insensitive", no_argument,  		NULL,   'i'},
	{"usage", 		no_argument,		NULL, 	'u'},
    { NULL,         0,                  NULL,    0 } 
};

void processOpts(int argc, char *argv[], char *files[], int *numFiles, int *rFlag, int *iFlag, int *jFlag, int *aFlag) {

	int i;
	char ch;
	char *progName = argv[0];

    while( (ch = getopt_long(argc, argv, "ri", longopts, NULL)) != -1 ) {
        switch(ch) {
            case 0:
                printf("processOpts: getopt_long returned 0\n");
                break;
            case 'r':
              	*rFlag = TRUE;
                break;
            case 'i':
				*iFlag = TRUE;
                break;
            case 'u':
                usage(progName, stdout, 0);
            case '?':
                usage(progName, stderr, 1);
            default:
                fprintf(stderr, "processOpts: reached default\n");
                break;

        }  //  switch
    }  // while

	argc -= optind;
	argv += optind;

	if( argc == 0 ) {
		fprintf(stderr, "Missing file name\n");
		usage(progName, stderr, 1);
	}
	
	for(i = 0; i < argc; i++ ) {
		files[i] = argv[i];
		*numFiles = *numFiles + 1;
	}

}  // processOpts

void usage(char *progName, FILE *file, int exitValue) {
  	fprintf(file, "Usage: %s [-r] [-i] file [file ...]\n", progName);
   	fprintf(file, "  --usage           print this information and exit\n"); 
   	fprintf(file, "  -r --reverse      reverse alphabetic order\n");
   	fprintf(file, "  -i --insensitive  case insensitive order\n");
	fprintf(file, "Default is case-sensitive alphabetic ordering\n");
	exit(exitValue);
} // usage
