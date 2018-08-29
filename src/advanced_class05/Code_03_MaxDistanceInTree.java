package advanced_class05;
/**
 * 求一棵二叉树上的最远距离
 * @author liq
 *
 */
public class Code_03_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		if(head == null) return 0;
		return process(head).maxDistance;
	}
	
	public static ReturnType process(Node head) {
		if(head == null) return new ReturnType(0, 0);
		ReturnType leftInfo = process(head.left);
		ReturnType rightInfo = process(head.right);
		int case1 = leftInfo.maxDistance;
		int case2 = rightInfo.maxDistance;
		int includeHeadDist = leftInfo.depth + rightInfo.depth + 1;
		int resultDist = Math.max(Math.max(case1, case2), includeHeadDist);
		return new ReturnType(resultDist, Math.max(leftInfo.depth, rightInfo.depth) + 1);
	}
	
	public static class ReturnType{
		int maxDistance;  //子树内收集的最大距离
		int depth;        //子树的最大深度
		public ReturnType(int maxDistance, int depth) {
			this.maxDistance = maxDistance;
			this.depth = depth;
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
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));
	}
}
