/*
	Taylor Siebenberg
	cs352
	cItems.c
	"The program will read the C program one character at a time using getchar(). As the program is being read, you will print the program to standard out. The printed program will show only comments, string literals, and character literals. Any other non-whitespace character will be replaced by a blank space."
*/

#include <stdio.h>
#include <stdlib.h>

void strLiteral(int table[5][10000], int lines);
void charConstant(int table[5][10000], int lines);
void maybeComment(int table[5][10000], int *lines, int *p);
void oneLineComment(int table[5][10000], int lines, int *p);
void blockComment(int table[5][10000], int *lines);
void blockEnd(int table[5][10000], int *lines);
void outputTable(int table[5][10000], int lines);


int main( int argc, char *argv[] ) {

	char ch;
	int bl, sl, cc, olc, bc, lines; 		// counts, bl = blank lines, sl = string literals, cc = character constant, olc = one line constant, bc = block comment, lines = line numbers
	bl = sl = cc = olc = bc = lines = 0; 	

	int table[5][10000];				// table that stores data per line

	int tFlag = 0;					// tflag is the t-argument flag
	int flag = 0; 					// flag is used to determing blank lines
	int *p, *q;					// pointers

	if( argc == 2 ) {
		tFlag = 1;
	}

	// Main loop of the state machine, changes state depending on the current character
	while( (ch = getchar()) != EOF ) {
	
		if( ch == '"' ) {			// If string literal begings, change state to string literal
			printf("%c", ch);
			flag = -10000;
			strLiteral(table, lines);			
		}
		else if( ch == '\'') {			// If character constant begins, change state to  character constant
			printf("%c", ch);
			flag = -10000;
			charConstant(table, lines);
		}
		else if( ch == '/' ) {			// Found possible comment, change state to look for possible comment
			p = &flag;
			q = &lines;
			maybeComment(table, q, p);
			flag = *p;
		}
		else {					// Else stay in the current state
			if( ch == '\n' ) {			//character is a new line
				printf("\n");	
				flag += 1;
				if( flag > 0 ) {			//Flag determine if the current line is a blank line, increase blank line count
					table[0][lines] = 1;
				}
				flag = 0;
				lines++;
			}
			else if( ch == '\t' ) {			//character is a tab
				printf("\t");
				flag += 1;
			}
			else if( ch == ' ')  {			// character is a space, could possibly be a blank line
				printf(" ");
				flag += 1;
			}
			else {					// otherwise, just print a space
				printf(" ");
				flag = -10000;
			}
		}

	}	// while

	if( tFlag == 1 ) {				// Print the counts and table if t-argument is passed
		int i;
		for( i = 0; i < lines; i++ ) {	// run through the table to determine counts
			if( table[0][i] != 0 ) {	// if blank line cell was updated increase count
				bl++;
			}
			if ( table[1][i] != 0 ) {	// string literal count
				sl += table[1][i];
			}
			if( table[2][i] != 0 ) {	// character constant count
				cc += table[2][i];	
			}
			if( table[3][i] != 0 ) {	// 1 line comment count
				olc += table[3][i];
			}
			if( table[4][i] != 0 ) {	// block constant count
				if( table[4][i] != 10000 ) {
					bc += table[4][i];
				}
			}
		}
	
		// Print the counts
		printf("The program contains:\n");
		printf("%10d blank lines\n", (bl));
		printf("%10d string literals\n", sl);
		printf("%10d character constants\n", cc);
		printf("%10d one line comments\n", olc);
		printf("%10d block comments\n", bc);
		printf("%10d lines\n", lines);

		outputTable(table, lines);	// call function to output tables
	}
	return 0;
}	// main


/*
	String Literal state,  parses the comming characters as if they were in a string literal.  
*/
void strLiteral(int table[5][10000], int lines) {
	
	char ch;
		
	while( (ch = getchar()) != '"' ) {	// while we are not at the end of a string, print the character
		printf("%c", ch);
		if( ch == '\\' ) {		// if there is an escape character, only print the following character
			ch = getchar();
			printf("%c", ch);
		}
	}
	printf("%c", ch);
	table[1][lines] += 1;			// update the table count for string literals
}

/*
	Character constant state, parses the coming characters as if they were in a character constant
*/
void charConstant(int table[5][10000], int lines) {
	
	char ch;

	while( (ch = getchar()) != '\'' ) {	// while we are not at the end of the character constant, print the character
		printf("%c", ch);
	}
	printf("%c", ch);
	table[2][lines] += 1;			// update the count for character constants

}

/*
	Comment state, Determines if we are in a comment
*/
void maybeComment(int table[5][10000], int *lines, int *p) {

	char ch;

	ch = getchar();
	if( ch == '/' ) {				// if the character is a backslash, change to one line comment state, afterwards update the count
		printf("/%c", ch);
		oneLineComment(table, *lines, p);
		*lines = *lines + 1;
	}
	else if( ch == '*' ) {				// if the character is a star, change to block comment state, 
		printf("/%c", ch);
		blockComment(table, lines);		
		*p = -10000;					// turn blank line flag off
	}
	else {
		printf("  ");
	}
}

/*
	One line comment state, parases the coming characters as if they were in a one line comment
*/
void oneLineComment(int table[5][10000], int lines, int *p) {
	
	char ch;

	while( (ch = getchar()) != '\n' ) {	//while we are not at the end of the comment, print the characters
		printf("%c", ch);
	}
	printf("%c", ch);
	table[3][lines] += 1;				// update the one line comment count
	*p = 0;						// turn blank line flag on

}

/*
	block comment state, parses the coming characters as if they were in a block comment
*/
void blockComment(int table[5][10000], int *lines) {

	char ch;

	while( (ch = getchar()) != '*' ) {		// while we are not at the "possible" end of the block comment, print the characters and update lines count.
		printf("%c", ch);
		if( ch == '\n' ) {
			table[4][*lines] = 10000;		// adds a value to help print out the table data at the end
			*lines = *lines + 1;
		}
	}
	printf("%c", ch);
	blockEnd(table, lines);				// we found a star, possible block comment end, call function to determine
}

/*
	block comment end fuction, determines if we are at the end of a block comment or not
*/
void blockEnd(int table[5][10000], int *lines) {

	char ch;
	
	while( (ch = getchar()) == '*' ) {	// prints all the coming stars
		printf("%c", ch);
	}
	if( ch == '/' ) {			// if the character is a backslash, comment ends. Update block comment count
		printf("%c", ch);
		table[4][*lines] += 1;
	}
	else if( ch == '\n') {			// if the character is a new line, update the line count
		printf("%c", ch);
		table[4][*lines] = 10000;		// used to print out the table
		*lines = *lines + 1;
		blockComment(table, lines);
	}
	else {					// else its is not a block comment ending character, return to block comment state
		printf("%c", ch);
		blockComment(table, lines);
	}
}

/*
	fuction to output the data stored in table.  Determines in what line each item is found
*/
void outputTable(int table[5][10000], int lines) {
	printf("     |   blank   |  string   | character |  1 line   |   block   |\n");
	printf("line |   line    | litteral  | constant  |  comment  |  comment  |\n");
	printf("-----|-----------|-----------|-----------|-----------|-----------|\n");
	int i;
	for(i = 0; i < lines; i++) {			// loop through all the lines
		printf("%4d |", i+1);
		int j;
		for( j = 0; j < 5; j++ ) {			// loop through each item in the table; blank lines, string lierals, character constants, 1 line comments, block comments
			if( table[j][i] > 0 ) {				// if there is data in the field, print an indicator		
				printf("    XXX    |");
			}
			else {						// print a blank space
				printf("           |");
			}
		}
		printf("\n");
	}
}
