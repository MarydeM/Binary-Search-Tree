public class Node{

	private Node left;
	private Node right;
	private int data;

	public Node(int data){
		this.data = data;
		this.right = null;
		this.left = null;
	}

	public int getData(){
		return data;
	}

	public Node getLeft(){
		return left;
	}

	public Node getRight(){
		return right;
	}

	public void setData(int data){
		this.data = data;
	}

	public void setLeft(Node left){
		this.left = left;
	}

	public void setRight(Node right){
		this.right = right;
	}
}