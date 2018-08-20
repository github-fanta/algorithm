package class04;

/**
 * 完全二叉树节点个数，小于O（n）（小于遍历的复杂度O（n））
 * @author liq
 *
 */
public class Code_08_CompleteTreeNodeNumber {
	
	static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
	
	public static int nodeNum(Node head) {
		if (head == null) return 0;
		return countNode(head, 1, getCBTDepth(head));
	}

	private static int countNode(Node head, int curDepth, int DEPTH) {

		if(curDepth == DEPTH) return 1; //触底返回1
		int rTreeHeight = getCBTDepth(head.right); //右子树的深度等于右子树的高度
		if (curDepth+rTreeHeight == DEPTH) { //右子树触底，说明左子树肯定是满二叉数
			return (1 << rTreeHeight) + countNode(head.right, curDepth+1, DEPTH); //此时右子树的高度等于左子树的高度
		}else { //右子树没有触底，但是此时右子树一定是满二叉树
			return (1 << rTreeHeight) + countNode(head.left, curDepth+1, DEPTH); //右子树满二叉树计算公式 （2的rTreeHeight次方-1）加上当前节点个数1加上左完全二叉树结点个数
		}
	}
	//完全二叉树的高度等于最左边节点的深度
	private static int getCBTDepth(Node head) {
		int depth = 1;
		while(head != null) {
			depth ++;
			head = head.left;
		}
		return depth - 1;  //为了兼容head为null时的情况
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}
}
