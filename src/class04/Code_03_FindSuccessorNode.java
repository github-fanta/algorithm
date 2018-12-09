
package class04;
/**
 * 二叉树找后继结点
 * @author liq
 *
 */
public class Code_03_FindSuccessorNode {

	public static class Node{
		int value;
		Node left;
		Node right;
		Node parent;
		Node(int value){
			this.value = value;
		}
	}
	
	/**
	 * 找后继结点
	 */
	public static Node getSuccessorNode(Node head) {
		if (head == null) {
			return head;
		}
		
		if (head.right != null) {//右子树不为空，向下找右子树最左结点
			return getLeftMost(head.right);
		}else { //右子树为空，看当前节点是哪个结点的左子树的最右的结点。
			Node parent = head.parent;
			while(parent != null && parent.left != head) {
				head = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	//找到二叉树最左的结点
	private static Node getLeftMost(Node head) {
		if (head == null) {
			return head;
		}
		while(head.left != null) {//走到最左
			head = head.left;
		}
		return head;
	}
	
	/**
	 * 找前驱结点
	 */
	public static Node getPredecessorNode(Node head) {
		if (head == null) {
			return head;
		}
		if (head.left != null) {	//左子树不为空，向下找左子树最右的结点
			return getRightMost(head.left);
		}else {	//左子树为空，想上找，看当前节点是哪个结点的右子树的最左的结点
			Node parent = head.parent;
			while(parent != null && parent.right != head) {
				head = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	//获取子树的最右结点
	private static Node getRightMost(Node head) {
		if (head == null) {
			return head;
		}
		while(head.right != null) {  //走到最右
			head = head.right;
		}
		return head;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		/**
		 * 测试1：获取后继结点
		 */
		/*Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));*/
		
		/**
		 * 测试2：获取前驱结点
		 */
		Node test = head.left.left;// 10's pre is null
		System.out.println(test.value + " pre: " + getPredecessorNode(test));
		test = head.left.left.right;     
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.left;                
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.left.right;           
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.left.right.right;     
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head;                     
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.right.left.left;      
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.right.left;          
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.right;               
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
		test = head.right.right;          
		System.out.println(test.value + " pre: " + getPredecessorNode(test).value);

	}
}
