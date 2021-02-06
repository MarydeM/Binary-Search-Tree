# Binary-Search-Tree
Utilizing nodes and a link list, the user can insert values and perform multiple functions within this tree.

User functions include:
*insert(int)  - inserts a given value in the tree by feeding that value and the root node into secondaryInsert()

*search(int)  - searches for a given value and returns true if that value is in the tree 

*delete(int)  - removes a given value from the tree by finding the node and its parent with nodeSearch(), and then feeding those nodes into secondaryDelete()

*min() - returns the minimum value in the tree

*max()  - returns the maximum value in the tree

*inorder()  - feeds the root and an empty string to secondaryInOrder()

*preorder()  - feeds the root and am empty string to secondaryPreorder()

*postorder()  - feeds the root and am empty string to secondaryPostorder()


Background functions include:
*secondaryInsert(int, Node)  - compares the left and right child nodes to the given value and either finds where to insert the value, or traverses the tree recursively by feeding the next appropriate node back into itself

*nodeSearch(int)  - takes in a value, finds the node with that value, and returns the found node and its parent node; this is useful for deletion of given values

*secondaryDelete(Node parent, Node toDelete)  - takes in two nodes and deletes the child node; if the child node has children of it's own, the function calls itself until all of the appropriate nodes have been moved up

*secondaryInOrder(Node, String)  - puts all of the values of the tree in numerical order and returns them as a string separated my commas and spaces; it traverses the tree recursively in left child, parent, right child order

*secondaryPreorder(Node, String)  - puts all of the values of the tree in preorder and returns them as a string separated my commas and spaces; it traverses the tree recursively in parent, left child, right child order

*secondaryPostorder(Node, String)  - puts all of the values of the tree in postorder and returns them as a string separated my commas and spaces; it traverses the tree recursively in left child, right child, parent order




