/*
	Taylor Siebenberg
	cs352
	shuffle.c
	Proccess pairs of lines from stdin.  Takes a char from each line and concatenates them to make a new line.  Then counts the numbers of each present character.	
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int updateArray(char ch, int n, char totals[1000], int totalNums[1000] );
void output(int n, char totals[1000], int totalNums[1000], char *argument);
void sort(char totals[1000], int totalNums[1000], int n);
int max(int a, int b);

int main ( int argc, char *argv[] ) {
	
	char *argument;						// argument determines to print strings forward or backward
	int pairs;							// number of pairs of lines to process
	char totals[1000];					// array to hold the different unique chars
	int totalNums[1000];				// arry to hold the count of each char
	int n = 0;							// number of unique chars

	///////////////////////	Error checking 	/////////////////////////////
	if ( argc < 3 ) {																//	checks if there are at least 2 command line arguments
		fprintf(stderr, "Usage: shuffle -b|-f <num>\nwhere <num> is greater than 0\n");
		exit(1);
	}
	
	pairs = atoi(argv[2]);				// pairs of lines to process

	if ( (strcmp(argv[1], "-b") != 0) && (strcmp(argv[1], "-f") != 0) ) {			//  if the first argument isnt b or f exit with 1 and print usage			
		fprintf(stderr, "Usage: shuffle -b|-f <num>\nwhere <num> is greater than 0\n");
		exit(1);
	}
	
	argument = argv[1];					//  the argument to determine if to print forward or backward

	if ( pairs <= 0 ) {																//  if pairs is below 0, exit with 0 and print usage
		fprintf(stderr, "Usage: shuffle -b|-f <num>\nwhere <num> is greater than 0\n");
		exit(1);
	} 

	//////////////////////// Read in each pair of lines /////////////////////////
	int i;
	for(i = 0; i < pairs; i++) {
		char str1[1000], str2[1000], ch;		// str1 is line one and str2 is line two
		int i = 0, str1L = 0, str2L = 0;		// str1L and str2L are lengths of each variable
			

		while( (ch = getchar()) != '\n' ) {						// read in first line
			str1[i] = ch;
			i++;
			str1L++;
			n = updateArray(ch, n, totals, totalNums);
		}
		//str1[i] = '\0';	

		i = 0;
		while( (ch = getchar()) != '\n') {						// read in second line
			str2[i] = ch;
			i++;
			str2L++;
			n = updateArray(ch, n, totals, totalNums);	
		}		
		//str2[i] = '\0';		
	
//////////////////////Proccess determened by the command line argument ///////////////////////////////////////

		if ( strcmp(argument, "-f") == 0 ) {					// if argument if -f, print the chars out from the front of each string
			int i;
			for( i = 0; i < max(str1L, str2L); i++ ) {				// from the first char of str1 to the end
				if( i < str1L && i < str2L ) {							// print both chars if both strings are larger than i
					printf("%c%c", str1[i], str2[i] );
				} 
				else {													// else print the one that is still has chars
					if ( i < str1L ) {
						printf("%c", str1[i]);
					}
					else {
						printf("%c", str2[i]);
					}
				}
			}
		}
		else {													// else argument is -b, print the chars out from the end of each string
			int j, i = max(str1L, str2L);
			if ( i == str1L ) {									// if str1 is the longest string
				j = str2L - 1;											// j = shorter string length
				for( i = max(str1L, str2L) - 1; i >= 0; i--) {				// for each char starting from the end to the front			
					if( j >= 0 ) {											//  if the smaller string stil has chars, print them
						printf("%c%c", str1[i], str2[j]);
						j--;
					}
					else {													// else only print the larger string chars
						printf("%c", str1[i]);
					}
				}	
			}
			else {												// else str2 is the largest string
				j =  str1L - 1;
				for( i = max(str1L, str2L) - 1; i >= 0; i--) {
					if ( j >= 0 ) {
						printf("%c%c", str1[j], str2[i]);			// same logic as above
						j--;
					}
					else {
						printf("%c", str2[i]);
					}
				}	
			}	
		}
		
		printf("\n\n");
	}		

	sort(totals, totalNums, n);					// sort the arrays alphabetically	
	output(n, totals, totalNums, argument);		// output the total char data	

	return 0;
}

////////// Updates the totals array with counts and new chars ////////////////////
int updateArray(char ch, int n, char totals[1000], int totalNums[1000]) {
	int i;
	if ( n == 0 ) {					// if this is the first read in
		totals[0] = ch;
		totalNums[0]++;
		n++;
		return n;
	}
	for(i = 0; i < n; i++ ) {		// check if ch is already in the array totals
		if( totals[i] == ch ) {
			totalNums[i]++;				// update the count for that char in totalNums
			return n;
		}	
	}
	totals[n] = ch;					// if ch is not in the array, update the count and array
	totalNums[n]++;
	n++;
	
	return n;
}

///////////// Outputs the total char data ////////////////////
void output(int n, char totals[1000], int totalNums[1000], char *argument) {
	printf("Totals:\n");
	int i, count = 0;
	for(i = 0; i < n; i++) {							// For each element in the total array
		if ( totals[i] >= 32 && totals[i] < 127 ) {			// print only printable chars
			printf("%7c", totals[i]);
			count++;
			if( count == 8 ) {									// once proccessed 8 lines, print out the 8 counts of each of the chars from totalNums
				printf("\n");
				int j;
				for( j = i - 7; j <= i; j++ ) {
					printf("%7d", totalNums[j]);
				}
				printf("\n");
				count = 0;
			}
		}
		if ( i == (n - 1) ) {								// if we are on the last element of teh array
			printf("\n");
			int j;
			for( j = i - count + 1; j <= i; j++ ) {				// print the counts 
				if ( totals[j] >= 32 && totals[j] <= 127 ) {
					printf("%7d", totalNums[j]);
				}
			}
			printf("\n");
			count = 0;
		}
	}
	
}

//////////////// Insertion sort to sort both total and totalNums ////////////////
void sort(char totals[1000], int totalNums[1000], int n) {
	int i, j, tempI;
	char tempC;
	for(i = 0; i < n; i++) {
		tempC = totals[i];
		tempI = totalNums[i];
		j = i - 1;
		while( tempC < totals[j] && j >=0 ) {
			totals[j+1] = totals[j];
			totalNums[j+1] = totalNums[j];
			j = j - 1;
		}
		totals[j+1] = tempC;
		totalNums[j+1] = tempI;
	}
}

////////// Determines the max of two ints /////////////////////////
int max(int a, int b) {
	if( a > b ) 
		return a;
	else 
		return b;
}	
