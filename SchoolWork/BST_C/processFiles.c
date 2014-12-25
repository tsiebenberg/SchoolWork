/*
	Taylor Siebenberg
	cs352
	processFiles.c
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "tree.h"
   
#define FALSE 0
#define TRUE 1
#define MAX_CHARS 160

void fillList(FILE *txtFile, node **root, int rFlag, int iFlag);
void processCommands(char *file, FILE *commandFile, FILE *saveFile, node *root, int rFlag, int iFlag);

void processFiles(char *file, int rFlag, int iFlag) {

	FILE *txtFile, *commandFile, *saveFile;
	char *txtFileName, *commandFileName, *saveFileName;
	node *root = NULL;

	txtFileName = malloc( sizeof(char) * MAX_CHARS);	
	commandFileName = malloc( sizeof(char) * MAX_CHARS);	
	saveFileName = malloc( sizeof(char) * MAX_CHARS);	

	strcpy(txtFileName, file);
	strcat(txtFileName, ".txt");

	strcpy(commandFileName, file);
	strcat(commandFileName, ".commands");

	strcpy(saveFileName, file);
	strcat(saveFileName, ".save");

	txtFile = fopen( txtFileName, "r");
  	if( txtFile == NULL ) {
     	exit(0);
   	}
	
	commandFile = fopen( commandFileName, "r");
  	if( commandFile == NULL ) {
     	exit(0);
   	}

	saveFile = fopen( saveFileName, "w");
  	if( saveFile == NULL ) {
     	exit(0);
   	}

	fillList(txtFile, &root, rFlag, iFlag);

	//printTree(root);

	processCommands(file, commandFile, saveFile, root, rFlag, iFlag);	

	int result;
   	result = fclose(txtFile);
  	if( result != 0 )
     	fprintf(stderr, "an error occured when closing the file, %s\n", txtFileName);

   	result = fclose(commandFile);
  	if( result != 0 )
     	fprintf(stderr, "an error occured when closing the file, %s\n", commandFileName);

   	result = fclose(saveFile);
  	if( result != 0 )
     	fprintf(stderr, "an error occured when closing the file, %s\n", saveFileName);

	free(txtFileName);
	free(commandFileName);
	free(saveFileName);
}  // processFiles

void fillList(FILE *txtFile, node **root, int rFlag, int iFlag) {
	while( 1 ) {
 		char *line = malloc( MAX_CHARS * sizeof(char) );
                
  		fgets(line, MAX_CHARS, txtFile);
        
       	if( feof(txtFile) ) {
          	free(line);
			break;
     	}
		else { 
			line = line + 8;
      		insert(root, createNode(line));
		}
  	}
} // fillList

void processCommands(char *file, FILE *commandFile, FILE *saveFile, node *root, int rFlag, int iFlag) {
	printf("%s:\n\n", file);
	while( 1 ) {
 		char *command = malloc( MAX_CHARS * sizeof(char) );

  		fgets(command, MAX_CHARS, commandFile);
                
       	if( feof(commandFile) ) {
          	free(command);
         	break;
     	}
/*
		if( command[0] == 'S' ) {
			//if( sFlag == FALSE ) {
				printf("%s", command);
				printList(base, saveFile);
				//sFlag = TRUE;
			//}
			//else {
			//	printf("%s", command);	
			//}
		}
		else*/
		//printf("%s", command);
		 if( command[0] == 'T' ) {
			node *ptr;
			char *searchTitle;

			printf("TITLE\n");

			searchTitle = command + 6;

			//printList(base, stdout);

			ptr = find(root, searchTitle);
		
			if( ptr == NULL || strcmp(ptr->title, searchTitle) != 0 ) 
				printf("NOT FOUND: %s", searchTitle);
			else
				printf("FOUND: %s", searchTitle);
		}/*
		else if( command[0] == 'A' ) {
			struct node_struct *ptr;	
			char *searchTitle;
			
			printf("ADD\n");

			searchTitle = command + 4;

			ptr = find(searchTitle, base, iFlag);

			if( ptr->element == NULL || strcmp(ptr->element, searchTitle) != 0 ) {
				insert(searchTitle, &base, iFlag);
				printf("ADDED: %s", searchTitle);
			}
			else
				printf("FOUND: %s", searchTitle);
		}
		else if( command[0] == 'D' ) {
			struct node_struct *ptr;
			char *searchTitle;
		
			printf("DELETE-TITLE\n");
		
			searchTitle = command + 13;
	
			ptr = find(searchTitle, base, iFlag);
	
			if( ptr->element == NULL || strcmp(ptr->element, searchTitle) != 0 ) {
				printf("NOT FOUND: %s", searchTitle);
			}
			else {
				delete(&base, searchTitle, iFlag);
				printf("DELETED: %s", searchTitle);
			}
		}*/

	}
	printf("\n");
}  //processCommands

