package advanced_class05;

public class Code_04_BiggestSubBSTInTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static int getBiggestSubBSTSize(Node head) {
		if(head == null) return 0;
		return process(head).maxBSTSize;
	}
	
	private static ReturnType process(Node head) {
		if(head == null) {
			return new ReturnType(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}

		ReturnType leftInfo = process( head.left);
		ReturnType rightInfo = process(head.right);
		
		int maxBSTSize = 0;
		Node subBSTHead = null;
		if(head.left == leftInfo.subBSTHead &&    //左子树不含水分
		   head.right == rightInfo.subBSTHead &&  //右子树不含水分
		   leftInfo.allSubTreeMax < head.value && //满足搜索二叉树定义，左子树的最大值 严格小于 当前节点值
		   rightInfo.allSubTreeMin > head.value) { //右子树的最小值 严格小于 当前节点值
		   maxBSTSize = leftInfo.maxBSTSize + rightInfo.maxBSTSize + 1;
		   subBSTHead = head;
		}
		if(maxBSTSize == 0) {  //没有进入上面判断体内，说明子树有水分（搜索二叉树不是整个子树）
			maxBSTSize = Math.max(leftInfo.maxBSTSize, rightInfo.maxBSTSize);
			subBSTHead = maxBSTSize == leftInfo.maxBSTSize ? leftInfo.subBSTHead : rightInfo.subBSTHead;//选多的子树的头结点		
		} 

		return new ReturnType(maxBSTSize, subBSTHead, 
				Math.max(Math.max(leftInfo.allSubTreeMax, rightInfo.allSubTreeMax), head.value),
				Math.min(Math.min(leftInfo.allSubTreeMin, rightInfo.allSubTreeMin), head.value));
	}

	//消息体
	public static class ReturnType{
		int maxBSTSize;  //子树包含的最大搜索二叉子树大小
		Node subBSTHead; //子树包含的最大搜索二叉子树的头结点
		int allSubTreeMax;  //子树的最大值
		int allSubTreeMin;  //子树的最小值
		public ReturnType(int maxBSTSize, Node subBSTHead, int allSubTreeMax, int allSubTreeMin) {
			this.maxBSTSize = maxBSTSize;
			this.subBSTHead = subBSTHead;
			this.allSubTreeMax = allSubTreeMax;
			this.allSubTreeMin = allSubTreeMin;
		}
	}
	
	
	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(1);
		head.left.left = new Node(0);
		head.left.right = new Node(3);
		head.right = new Node(12);
		head.right.left = new Node(10);
		head.right.left.left = new Node(4);
		head.right.left.left.left = new Node(2);
		head.right.left.left.right = new Node(5);
		head.right.left.right = new Node(14);
		head.right.left.right.left = new Node(11);
		head.right.left.right.right = new Node(15);
		head.right.right = new Node(13);
		head.right.right.left = new Node(20);
		head.right.right.right = new Node(16);
		System.out.println(getBiggestSubBSTSize(head));
	}
}
