/*
	Taylor Siebenberg
	cs352
	boxFill.c
	"The program will print “boxes” of asterisks which are filled with a specified character." (Assignment Script)
*/

#include <stdio.h>
#include <stdlib.h>

int main( int argc, char *argv[] ) {

	// Check if there are < 2 arguments
	if ( argc < 3 ) {
		printf("Usage: boxFill <number> <fillCharacter>\n");
		exit(1);
	}

	int size = atoi(argv[1]);			// Size of the box
	char fill = argv[2][0];				// Character to fill box with
	
	// Check if size is out of bounds 3 <= size >= 80
	if ( size < 3 ) {
		size = 3;
	}
	if ( size > 80 ) {
		size = 80;
	}

	// Prints the box to stdout
	int i;
	for( i = 0; i < size; i++ ) {					// for rows=0 size size
	
		if ( i == 0 || i == (size - 1) ) { 				// if i = top or bottom print *

			int j;
			for( j = 0; j < size; j++ ) 					// for col=0 to size
				printf("*");
			printf("\n");
		}	
		else {											// else print * then fill then *						
			printf("*");
			
			int j;
			for( j = 0; j < (size - 2); j++ ) {				// for col=0 to size - 2
				printf("%c", fill);
			}
			printf("*\n");
		}
		
	}

	
	return 0;
	
}	/* main */
