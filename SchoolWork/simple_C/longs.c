/*
	Taylor Siebenberg
	cs352
	long.c
	The program counts the number of numbrs in a file
*/

#include <stdio.h>
#include <stdlib.h>

int main( int argc, char *argv[] ) {
	
	int	unsigned long  num, sum = 0;				// 	num stores each number when found
	int ch; 
	int line = 1;
	int numFlag = 0;										//	ch for each read from getchar		

	printf("              number       line\n");

	while( (ch = getchar()) != EOF ) {
		
		if( ch >= 48 && ch <= 57 ) {
			if ( numFlag == 0  ) {
				numFlag = 1;
				num = ch - 48;
			}
			else {
				num = num*10 + (ch - 48);
			}
		}
		else {
			if( numFlag == 1 ) {
				printf("%20lu %10d\n", num, line);
				sum = sum + num;
				num = 0;
				numFlag = 0;		
			}
		}		

		if( ch == '\n' )							//	Counts # of lines
			line++;
	}

	printf("\nThe sum of all the integers: %lu\n", sum);
	
	return 0;

}	/* main */
