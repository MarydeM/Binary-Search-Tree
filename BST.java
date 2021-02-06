public class BST{

	public Node root;

	/** constructor */
	public BST(){
		root = null;
	}

	/** takes an int, makes a node with it, and inserts 
	it in the correct part of the tree */
	void insert(int value){
		//base case
		if (root == null){
			Node node = new Node(value);
			root = node;
		}
		else{
			//if the value is less than root, go left
			if (value < root.getData()){
				//if the left is empty, put node there.
				if (root.getLeft() == null){
					root.setLeft(new Node(value));
				}
				//if not, call recursive function
				else{
					secondaryInsert(value, root.getLeft());
				}
			}
			//if the value is greater than root, go right
			else if (value > root.getData()){
				//if the right is empty, put node there.
				if (root.getRight() == null){
					root.setRight(new Node(value));
				}
				//if not, call recursive function
				else{
					secondaryInsert(value, root.getRight());
				}
			}
		}
	}
	/** Searches a node and its children and traverses the tree until 
	the proper place to insert the given node is found */
	private void secondaryInsert(int value, Node parent){
		//if the vlaue is less than the current node, go left
		if (value < parent.getData()){
			if (parent.getLeft() == null){
				parent.setLeft(new Node(value));
			}
			else{
				secondaryInsert(value, parent.getLeft());
			}
		}
		//if the vlaue is greater than the current node, go right
		else if (value > parent.getData()){
			if (parent.getRight() == null){
				parent.setRight(new Node(value));
			}
			else{
				secondaryInsert(value, parent.getRight());
			}
		}
	}

	/** tells the user whether or not a given value is in the tree */
	boolean search(int value){
		Node node = root;
		//if the node is not empty
		while (node != null){
			//if the current node is not what we are looking for...
			if(node.getData() != value){
				//go left if the number is less than
				if(value < node.getData()){
					node = node.getLeft();
				}
				//go right if the number is greater than
				else{
					node = node.getRight();
				}
			}
			//else, we found it!
			else{
				return true;
			}
		}
		//the node was never found
		return false;
	}

	/** takes a value and returns both the node and the parent node */
	private Node[] nodeSearch(int value){
		Node node = root;
		Node parent = null;
		Node[] nodes = new Node[2];
		//if the node is not empty
		while (node.getData() != value){
			//if the current node is not what we are looking for...
			if(node.getData() != value){
				//go left if the number is less than
				if(value < node.getData()){
					if (node.getLeft() != null){
						parent = node;
						node = node.getLeft();
					}
					else{
						return null;
					}
				}
				//go right if the number is greater than
				else{
					if(node.getRight() != null){
						parent = node;
						node = node.getRight();
					}
					else{
						return null;
					}
				}
			}
		}
		nodes[0] = parent;
		nodes[1] = node;
		return nodes;
	}

	/** takes a value to be deleted, gets the node and parent of thet node,
	and feeds them to the recursive deleter */
	void delete(int value){
		Node[] nodes = nodeSearch(value);
		Node parent = nodes[0];
		Node toDelete = nodes[1];
		//if the value is not in the tree, stop
		if(toDelete == null){
			return;
		}
		else{
			secondaryDelete(parent, toDelete);
		}
	}

	/** deletes a given node from the tree using recursion */
	private void secondaryDelete(Node parent, Node toDelete){
		//case 0
		if(toDelete.getLeft() == null && toDelete.getRight() == null){
			//if the parent is the root, just empty the array
			if (toDelete == root){
				root = null;
			}
			else{
				//check which side the child is on
				if(parent.getRight() == toDelete){
					parent.setRight(null);
				}
				else{
					parent.setLeft(null);
				}
			}
		}
		//case 1
		else if((toDelete.getLeft() == null && toDelete.getRight() != null) ||
			(toDelete.getRight() == null && toDelete.getLeft() != null)){
			//if deleting the root, make it's child the new root
			if(toDelete == root){
				if(toDelete.getRight() == null){
					root = toDelete.getLeft();
					toDelete.setLeft(null);
				}
				else{
					root = toDelete.getRight();
					toDelete.setRight(null);
				}
			}
			else{
				//check which side the child is on
				if(parent.getLeft() == toDelete){
					//check which side the child's child is on
					if(toDelete.getRight() == null){
						parent.setLeft(toDelete.getLeft());
					}
					else{
						parent.setLeft(toDelete.getRight());
					}
				}
				else{
					//check which side the child's child is on
					if(toDelete.getRight() == null){
						//if the child node on right is less than the patent node, swap
						if(parent.getData() > (toDelete.getLeft()).getData()){
							int value = parent.getData();
							parent.setData((toDelete.getLeft()).getData());
							(toDelete.getLeft()).setData(value);
						}
						parent.setRight(toDelete.getLeft());
					}
					else{
						parent.setRight(toDelete.getRight());
					}
				}
			}
		}
		//case 2
		else if(toDelete.getLeft() != null && toDelete.getRight() != null){
			//find successor
			Node successorParent = toDelete;
			Node successor = toDelete.getRight();
			while(successor.getLeft() != null){
				successorParent = successor;
				successor = successor.getLeft();
			}
			//set the node to be deleted's value to that of the successor node
			toDelete.setData(successor.getData());
			//now delete the successor
			secondaryDelete(successorParent, successor);
		}

	}

	/** returns the smallest value in the tree */
	int min(){
		if (root == null){
			return -1;
		}
		else{
			Node node = root;
			Node current = root.getLeft();
			while(current != null){
				node = current;
				current = current.getLeft();
			}
			return node.getData();
		}
	}

	/** returns the largest value in the tree */
	int max(){
		if (root == null){
			return -1;
		}
		else{
			Node node = root;
			Node current = root.getRight();
			while(current != null){
				node = current;
				current = current.getRight();
			}
			return node.getData();
		}
	}

	/** Puts all of the numbers in the tree in order and turns 
	that into a string */
	String inorder(){
		String ordered = "";
		//base case
		if(root == null){
			return ordered;
		}
		else{
			ordered = secondaryInOrder(root, ordered);
		}
		return ordered;	
	}

	/** recursively puts all nodes in numerical order, LNR */
	private String secondaryInOrder(Node node, String ordered){
		//if we have reached an empty node, go to the previous one
		if (node == null){
			return ordered;
		}
		//check the left nodes
		ordered = secondaryInOrder(node.getLeft(), ordered);
		//add the current node to the string
		if (ordered == ""){
			ordered += node.getData();
		}
		else{
			ordered += ", " + node.getData();
		}
		//check the right nodes
		ordered = secondaryInOrder(node.getRight(), ordered);
		return ordered;
	}

	/** Returns a string of the nodes in pre-order; NLR */
	String preorder(){
		String preordered = "";
		if (root == null){
			return preordered;
		}
		else{
			preordered = secondaryPreorder(root, preordered);
		}
		return preordered;
	}

	/** recursively puts all nodes in pre-order */
	private String secondaryPreorder(Node node, String preordered){
		//if we have reached an empty node, go back
		if(node == null){
			return preordered;
		}
		//add the current node to the string
		if (preordered == ""){
			preordered += node.getData();
		}
		else{
			preordered += ", " + node.getData();
		}
		//add the left nodes
		preordered = secondaryPreorder(node.getLeft(), preordered);
		//then add the right
		preordered = secondaryPreorder(node.getRight(), preordered);
		return preordered;
	}

	/** Returns a string of the nodes in post-order; LRN */
	String postorder(){
		String postordered = "";
		if (root == null){
			return postordered;
		}
		else{
			postordered = secondaryPostorder(root, postordered);
		}
		return postordered;
	}	

	/** recursively puts all nodes in post-order, LRN */
	private String secondaryPostorder(Node node, String postordered){
		//if we have reached an empty node, go back
		if(node == null){
			return postordered;
		}
		//add the left nodes
		postordered = secondaryPostorder(node.getLeft(), postordered);
		//then add the right
		postordered = secondaryPostorder(node.getRight(), postordered);
		//add the current node to the string
		if (postordered == ""){
			postordered += node.getData();
		}
		else{
			postordered += ", " + node.getData();
		}
		return postordered;
	}

}
