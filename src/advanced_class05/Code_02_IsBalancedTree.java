package advanced_class05;


/**
 * 用树形DP思想,判断是否是二叉平衡树
 * @author liq
 *
 */
public class Code_02_IsBalancedTree {

	static class Node{
	    int val;
	    Node left;
	    Node right;
		public Node(int val) {
			this.val = val;
		}
	}
	
	public static boolean isBalancedTree(Node head) {
		return process(head).isB;
	}

	private static ReturnData process(Node head) {
		if(head == null) return new ReturnData(true, 0);
		ReturnData leftInfo = process(head.left);
		if(!leftInfo.isB) return new ReturnData(false, 0);
		ReturnData rightInfo = process(head.right);
		if(!rightInfo.isB) return new ReturnData(false, 0);
		
		return new ReturnData(Math.abs(leftInfo.h - rightInfo.h) <= 1, Math.max(leftInfo.h, rightInfo.h) + 1);
	}
	
	static class ReturnData{
		boolean isB;
		int h;
		public ReturnData(boolean isB, int h) {
			this.isB = isB;
			this.h = h;
		}
	}
	
	
	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		//head1.right.left.right.left = new Node(10);
		System.out.println(isBalancedTree(head1));
	}
}
