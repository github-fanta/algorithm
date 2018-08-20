package advanced_class03;

public class Code_01_MorrisTraversal {

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	//morris中序遍历
	public static void morrisIn(Node head) {
		if(head == null) return;
		Node cur = head;        //尊贵者先在山坡上等着
		Node mostRight = null;  //工兵为您服务
		while(cur != null) {
			mostRight = cur.left;
			if(mostRight != null) {   //左边有路，工兵去找最右边结点
				while(mostRight.right != null && mostRight.right != cur) {  //右下结点要么右指针是空或者右指针指向高坡上的尊贵者
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {  //说明第一次到达此地
					mostRight.right = cur;   //工兵搭桥
					cur = cur.left;  //尊贵者移驾
					continue;        //进行下一次搭桥
				}else {              //第二次到达此地
					mostRight.right = null; //尊贵者顺利返回高地， 再次差遣工兵找路，拆桥
				}
			} //左边没有路
			System.out.print(cur.value + " ");  //在往右转去之前打印自己。
			cur = cur.right; //尊贵者转头往右去。所以叶子节点只能到达自己一次，因为没有从左边往上返回的那一次了，而是直接转向右边了。
		}
	}
	
	//morris前序遍历
	public static void morrisPre(Node head) {
		if(head == null) return;
		Node cur = head;
		Node mostRight = null;
		while(cur != null) {
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					System.out.print(cur.value + " ");  //在往左转去之前打印自己。
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
				}
			}else {
				System.out.print(cur.value + " ");  //要是压根不往左转，那就没法打印了，于是补上。
			}
			cur = cur.right;
		}
	}
	
	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		//morrisIn(head);
		morrisPre(head);

	}
}
