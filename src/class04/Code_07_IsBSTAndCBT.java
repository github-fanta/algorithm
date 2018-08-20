package class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 搜索二叉树和完全二叉树的判定
 * @author liq
 *
 */
public class Code_07_IsBSTAndCBT {


	public static class Node {
		int value;
		Node left;
		Node right;
		Node(int value) {
			this.value = value;
		}
	}
	/**
	 * 判断是否是搜索二叉树非递归
	 */
	public static boolean isBST(Node head) {
		if (head == null || (head.left == null && head.right == null)) {
			return true;
		}
		Long pre = Long.MIN_VALUE;
		Stack<Node> stack = new Stack<Node>();
		Node temp = head;
		while(!stack.isEmpty() || temp != null) {
			if (temp != null) {
				stack.push(temp);
				temp = temp.left;
			}else {
				temp = stack.pop();
				if (temp.value < pre) return false;  //若没有保持中序递增，就返回false
				pre = (long) temp.value;
				temp=temp.right;
			}
		}
		return true;
	}
	
	//判断是否是搜索二叉树 递归方式 
	public static boolean isBSTRecur(Node head) {
		if (head == null) {
			return true;
		}
		return recur(head, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	//递归函数
	private static boolean recur(Node cur, Long min, Long max) {
		boolean leftResult = true;
		boolean rightResult = true;
		if (cur.value <= min || cur.value >= max) return false;
		if (cur.left != null) leftResult = recur(cur.left, min, (long)cur.value); //左子树不应大于当前结点值
		if (cur.right != null) rightResult = recur(cur.right, (long)cur.value, max);//右子树不应大于当前结点值
		return   leftResult && rightResult;
	}

	
	
	
	/**
	 * 判断完全二叉树
	 * @param head
	 */
	public static boolean isCBT(Node head) {
		if (head == null) return true;
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(head);
		boolean leafStatus = false;//叶结点状态，一旦开启，则后面遍历到的结点都必须是叶结点
		Node temp = null;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			if (temp.left != null && temp.right == null) return false; //违反情况一：左孩子为空，右孩子不为空
			if (leafStatus == true && (temp.left != null || temp.right != null)) return false; //违反情况二：开启叶结点状态，temp竟然右孩子
			//不违反，但是出现违反趋势的，开启叶结点状态
			if (temp.right == null) leafStatus = true;   //左孩子不为空，右孩子为空已经被上面排除了，进此判断的只能是左孩子为空，右孩子也为空
			//放心地层次遍历
			if (temp.left != null) queue.offer(temp.left);
			if (temp.right != null) queue.offer(temp.right);
		}
		return true;
	}
	
	
	
	// for test -- print tree
		public static void printTree(Node head) {
			System.out.println("Binary Tree:");
			printInOrder(head, 0, "H", 17);
			System.out.println();
		}

		public static void printInOrder(Node head, int height, String to, int len) {
			if (head == null) {
				return;
			}
			printInOrder(head.right, height + 1, "v", len);
			String val = to + head.value + to;
			int lenM = val.length();
			int lenL = (len - lenM) / 2;
			int lenR = len - lenM - lenL;
			val = getSpace(lenL) + val + getSpace(lenR);
			System.out.println(getSpace(height * len) + val);
			printInOrder(head.left, height + 1, "^", len);
		}

		public static String getSpace(int num) {
			String space = " ";
			StringBuffer buf = new StringBuffer("");
			for (int i = 0; i < num; i++) {
				buf.append(space);
			}
			return buf.toString();
		}

		public static void main(String[] args) {
			Node head = new Node(4);
			head.left = new Node(2);
			head.right = new Node(6);
			head.left.left = new Node(1);
			head.left.right = new Node(3);
			head.right.left = new Node(5);
			
			printTree(head);
			System.out.println(isBST(head));
			System.out.println(isCBT(head));

		}
}
