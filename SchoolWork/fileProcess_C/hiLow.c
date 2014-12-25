/*
	Taylor Siebenberg
	cs352
	hiLow.c
	Count the number of integers from stdin.  Ouput the largest, second largetst, smallest, and second smalles.  Output the remaining numbers.
*/

#include <stdio.h>
#include <stdlib.h>

int main ( int argc, char *argv[] ) {
	
	int i, s1, s2, l1, l2, count, a[100000]; 
	int s1Count, s2Count, l1Count, l2Count;
	
	s1Count = s2Count = l1Count = l2Count = 0;	

	scanf("%d\n", &count);										// read the # of integers
	
	if( count > 100000 ) {										// checks that count is within processing range
		count = 100000;
	}
	if ( count < 0 ) {											// "										   "
		count = 0;
	}
	if ( count == 0 ) {											//  if there are no arguments, return
		return 0;
	}

	for(i = 0; i < count; i++) {								// go through each number determined by count

		scanf("%d\n", &a[i]);

		if( i == 0 ) {												// set variables if first run in for loop
			s1 = s2 = l1 = l2 = a[0];
		}
	
		if( a[i] < s1 ) {											// set smallest and 2nd smallest variables
			s2 = s1;
			s1 = a[i];
		} 
		if ( a[i] < s2 && s1 < a[i] ) {
			s2 = a[i];
		}
		if ( s1 == s2 && a[i] > s2 ) {
			s2 = a[i];
		}

		if( a[i] > l1 ) {											//  set largest and 2nd largest variables
			l2 = l1; 
			l1 = a[i];
		}
		if ( a[i] > l2 && l1 > a[i] ) {
			l2 = a[i];
		}
		if ( l1 == l2 && a[i] < l2 ) {
			l2 = a[i];
		}	
	}

	for( i = 0; i < count; i++ ) { 									// record number of times l1, l2, s1, s2 occure in array a
	
		if ( s1 == a[i] ) 	
			s1Count++;
		if ( s2 == a[i] )
			s2Count++;
		if ( l1 == a[i] ) 
			l1Count++;
		if ( l2 == a[i] )
			l2Count++;	
	
	}		

	////////////////////////  Output	////////////////////////////////////////
	printf("There are %d numbers in the array\n\n", count);		//	print the count

	if ( l1 == s1 ) {   // if largets = smallest
		if ( l1Count == 1 )
 			printf("The largest and smallest number is %d and appears %d time.\n", l1, l1Count);
		else
 			printf("The largest and smallest number is %d and appears %d times.\n", l1, l1Count);
	}
	else {				// else print the largets, 2nd largets, smallest and 2nd smallest numbers
		if ( l1Count == 1 )         
			printf("The largest number is %d and appears %d time.\n", l1, l1Count);
		else
			printf("The largest number is %d and appears %d times.\n", l1, l1Count);
		
		if ( l2Count == 1 ) 
			printf("The 2nd largest number is %d and appears %d time.\n\n", l2, l2Count);
		else
			printf("The 2nd largest number is %d and appears %d times.\n\n", l2, l2Count);
		
		if ( s1Count == 1 )
			printf("The smallest number is %d and appears %d time.\n", s1, s1Count);
		else
			printf("The smallest number is %d and appears %d times.\n", s1, s1Count);
	
		if ( s2Count == 1 ) 
			printf("The 2nd smallest number is %d and appears %d time.\n\n", s2, s2Count);
		else
			printf("The 2nd smallest number is %d and appears %d times.\n\n", s2, s2Count);
	}
	
	int rest = count - (l1Count + l2Count + s1Count + s2Count);    // ints to help determine the remaining ints left in the array
	int lineCount = 0;
	
	if ( rest <= 0 ) {   // if the largest, 2nd largest, etc. are the only numbers in the array, return
		return 0; 	
	}
	else {				// else print out the remaining ints in a field of 12 characters, 5 to a line
		printf("The other %d numbers in the array are:\n", rest);
		for( i = 0; i < count; i++ ) {
			if(a[i] != l1 && a[i] != l2 && s1 != a[i] && s2 != a[i] ) {
				printf("%12d", a[i]);
				lineCount++;
			}
			if ( lineCount == 5 ) {
				printf("\n");
				lineCount = 0;
			}
		}
		if ( lineCount != 0 ) {
			printf("\n");
		}
	}
	

	return 0;	

} // 	main
