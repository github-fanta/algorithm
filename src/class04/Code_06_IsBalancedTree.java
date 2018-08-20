package class04;
/**
 * 判断一颗二叉树是否是平衡二叉树（二叉树中任何一个结点的左右子树高度差不超过1）
 * @author liq
 *
 */
public class Code_06_IsBalancedTree {

	public static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
	
	
	public static Boolean isBalance(Node head) {
		return isBalanceRec(head) == -1 ? false : true; //或者 return isBalanceRec(head) != -1;
	}
	
	public static int isBalanceRec(Node head) {
		if (head == null) {
			return 0;
		}
		int leftH = isBalanceRec(head.left);
		if (leftH == -1) return -1;			//-1表示非平衡
		int rightH = isBalanceRec(head.right);
		if(rightH == -1) return -1;			//-1表示非平衡
		if (Math.abs(leftH - rightH) > 1) return -1;
		return Math.max(leftH, rightH) + 1;
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}
}
