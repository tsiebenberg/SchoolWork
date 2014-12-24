/*
	Taylor Siebenberg
	cs352
	tree.c
	
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "tree.h"

node * treeMin(node *root);
void treeShift(node **root, node *u, node *v);
/*
int main(int argc, char *argv[] ) {
	node *root = NULL;
	insert(&root, createNode("f\n"));
	insert(&root, createNode("e\n"));
	insert(&root, createNode("c\n"));
	insert(&root, createNode("b\n"));
	insert(&root, createNode("g\n"));	
	insert(&root, createNode("d\n"));
	insert(&root, createNode("a\n"));
	printTree(root);
	delete(&root, "c\n");
	delete(&root, "f\n");
	//delete(&root, "e\n");
	//delete(&root, "b\n");
	//delete(&root, "g\n");
	//delete(&root, "d\n");
	//delete(&root, "a\n");
	printf("\n");
	printTree(root);
	//node *temp = treeMin(root);
	//printf("\n%s", temp->title);
	return 0;	
}
*/
node * find(node *root, char *title) {
	if( root == NULL || strcmp(title, root->title) == 0 ) {
		return root;
	}
	if( strcmp(title, root->title) < 0 )
		return find(root->leftChild, title);
	else
		return find(root->rightChild, title);
		
}

void insert(node **root, node *newNode) {	

	node *parent = NULL;
	node *finder = *root;
	
	while( finder != NULL ) {
		parent = finder;
		if( strcmp(newNode->title, finder->title) < 0 )
			finder = finder->leftChild;
		else
			finder = finder->rightChild;
	}
	newNode->parent = parent;
	
	if( parent == NULL)
		*root = newNode;
	else if( strcmp(newNode->title, parent->title) < 0 )
		parent->leftChild = newNode;
	else
		parent->rightChild = newNode; 

}

void delete(node **root, char *title) {
	node *del = find(*root, title);
	if(del->leftChild == NULL)
		treeShift(root, del, del->rightChild);
	else if(del->rightChild == NULL)
		treeShift(root, del, del->leftChild);
	else {
		node *min = treeMin(del->rightChild);
		if( min->parent != del) {
			treeShift(root, min, min->rightChild);
			min->rightChild = del->rightChild;
			min->rightChild->parent = min; 
		} 
		treeShift(root, del, min);
		min->leftChild = del->leftChild;
		min->leftChild->parent = min;
	}
	free(del);
}

void treeShift(node **root, node *u, node *v) {
	if( u->parent == NULL ) 
		*root = v;
	else if( u == u->parent->leftChild ) 
		u->parent->leftChild = v;
	else
		u->parent->rightChild = v;
	if( v != NULL )
		v->parent = u->parent;
}

void printTree(node *aNode) {
	if(aNode != NULL) {
		printTree(aNode->leftChild);
		printf("%s", aNode->title);
		printTree(aNode->rightChild);
	}
}

node * createNode(char *title){
	node *newNode;
	newNode = (node *) malloc(sizeof(node));
	newNode->title = title;
	newNode->leftChild = NULL;
	newNode->rightChild = NULL;
	return newNode;
}

node * treeMin(node *root) {
	while(root->leftChild != NULL )
		root = root->leftChild;
	return root;
}
