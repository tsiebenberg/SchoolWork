/* 
	Taylor Siebenberg
	cs352
	skipArgs.c
	Prints the strings from stdin depending on if there ascii value is divisible by the first argument
*/

#include <stdio.h>
#include <stdlib.h>

int main( int argc, char *argv[] ) {

	if( argc <= 1 ) {										//  makes sure there are command line arguments
		printf("Usage: skipArgs <number> [arg ...]\n");
		exit(1);
	}

	int div = atoi(argv[1]);								// div = first argument, divisor to determine what side to print to below
	
	printf("Left-justified strings:\n");		

	if( div <= 0 ) 											// makes sure div is positive
		div = 1;	

	int i;
	for( i = 2; i < argc; i++  ) {							//  Go through each command to print left-justified
	
		printf("%4d:|", (i - 1));
		
		if( ((i - 1) % div) != 0) 								//  If i is not divisibe by div print on the left side
			printf("%-15s|               |\n", argv[i]);
		else 													//  else print on the right
			printf("               |%-15s|\n", argv[i]);

	}  // for

	printf("Right-justified strings:\n");	

	for( i = 2; i < argc; i++ ) { 							//  Go through each command to print right-justified
	
		printf("%4d:|", (i - 1));
		
		if( ((i - 1) % div) != 0) 								//  If i is not divisibe by div print on the left side
			printf("%15s|               |\n", argv[i]);
		else 													//  else print on the right
			printf("               |%15s|\n", argv[i]);

	}  // for

	return 0;

} // main
