package advanced_class03;


/**
 * 空间复杂度O(1) 时间复杂度O(N)
 * @author liq
 *
 */
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
				while(mostRight.right != null && mostRight.right != cur) {  //右下结点要么为null 要么右指针是空或者右指针指向高坡上的尊贵者
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
			cur = cur.right; //尊贵者转头往右去。所以叶子节点只能到达自己一次，因为没有从右边往上返回的那一次了，而是直接转向右边了。
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
					System.out.print(cur.value + " ");  //有左孩子，在往左转去之前打印自己。
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
				}
			}else {  //没有左孩子，在往右窜之前打印自己
				System.out.print(cur.value + " ");  //要是压根不往左转，那就没法打印了，于是补上。
			}
			cur = cur.right;
		}
	}
	
	//后续遍历
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
					printEdge(cur1.left);
				}
			}
			cur1 = cur1.right;
		}
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}
	
	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		morrisIn(head);System.out.println();
		morrisPre(head);System.out.println();
		morrisPos(head);System.out.println();
	}
}
