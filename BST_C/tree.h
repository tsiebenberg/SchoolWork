
typedef struct TreeNode {
	char *title;
	struct TreeNode *leftChild, *rightChild, *parent; 
} node;

extern node *find(node *root, char *title);
extern void insert(node **root, node *newNode);
extern void delete(node **root, char *title);
extern void printTree(node *aNode);
extern node * createNode(char *title);
