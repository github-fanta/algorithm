package class04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 * @author liq
 *
 */
public class Code_04_SerializeAndReconstructTree {

	static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
	
	/**
	 * 先序序列化
	 */
	public static String serializeByPre(Node head) {
		if (head == null) {
			return "#!";
		}
		String resultStr = head.value + "!";
		resultStr += serializeByPre(head.left);
		resultStr += serializeByPre(head.right);
		return resultStr;
	}
	
	/**
	 * 先序反序列化
	 */
	public static Node reconByPreString(String preString) {
		if (preString == null || preString.equals("")) {
			return null;
		}
		String[] nodesStr = preString.split("!");
		Queue<String> queue = new LinkedList<>();
		for (String string : nodesStr) {
			queue.add(string);
		}
		return reconByPre(queue);
	}
	private static Node reconByPre(Queue<String> queue) {
		String nodeStr = queue.poll();
		if (nodeStr.equals("#")) {
			return null;
		}
		//怎么序列化的就怎么反序列化
		Node node = new Node(Integer.valueOf(nodeStr));
		node.left = reconByPre(queue);
		node.right = reconByPre(queue);
		return node;
	}
	
	
	/**
	 * 层次序列化
	 */
	public static String serializeByLevel(Node head) {
		if (head == null) {
			return "#!";
		}
		Queue<Node> queue = new LinkedList<>();
		queue.offer(head);
		StringBuffer resultStr = new StringBuffer();
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			//defensive code
			if (temp == null) {
				resultStr.append("#!");
				continue;
			}
			resultStr.append(temp.value+"!");
			//将null节点也添加进队列
			queue.offer(temp.left);
			queue.offer(temp.right);
		}
		return resultStr.toString();
	}
	

	/**
	 * 层次反序列化
	 */
	public static Node reconByLevelString(String levelStr) {

		Queue<Node> valueQ = getQueueByLevelStr(levelStr);
		Queue<Node> queue = new LinkedList<Node>();
		Node head = valueQ.poll();
		if (head == null) return null;
		
		queue.offer(head);
		Node temp = null;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			if (temp == null)continue;
			temp.left = valueQ.poll();
			temp.right = valueQ.poll();
			queue.offer(temp.left);
			queue.offer(temp.right);
		}
		return head;
	}
	//通过层次遍历序列化的字符串获得所有结点的一个队列valueQ
	private static Queue<Node> getQueueByLevelStr(String levelStr){
		
		Queue<Node> valueQ = new LinkedList<Node>();
		String[] nodesStr = levelStr.split("!");
		for (String nodeStr : nodesStr) {
			if (nodeStr.equals("#")) {
				valueQ.offer(null);
			}else {
				valueQ.offer(new Node(Integer.valueOf(nodeStr)));
			}
		}
		return valueQ;
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
			Node head = null;
			printTree(head);

			String pre = serializeByPre(head);
			System.out.println("serialize tree by pre-order: " + pre);
			head = reconByPreString(pre);
			System.out.print("reconstruct tree by pre-order, ");
			printTree(head);

			String level = serializeByLevel(head);
			System.out.println("serialize tree by level: " + level);
			//head = reconByLevelString(level);
			//System.out.print("reconstruct tree by level, ");
			printTree(head);

			System.out.println("====================================");

			head = new Node(1);
			printTree(head);

			pre = serializeByPre(head);
			System.out.println("serialize tree by pre-order: " + pre);
			head = reconByPreString(pre);
			System.out.print("reconstruct tree by pre-order, ");
			printTree(head);

			level = serializeByLevel(head);
			System.out.println("serialize tree by level: " + level);
			//head = reconByLevelString(level);
			//System.out.print("reconstruct tree by level, ");
			printTree(head);

			System.out.println("====================================");

			head = new Node(1);
			head.left = new Node(2);
			head.right = new Node(3);
			head.left.left = new Node(4);
			head.right.right = new Node(5);
			printTree(head);

			pre = serializeByPre(head);
			System.out.println("serialize tree by pre-order: " + pre);
			//head = reconByPreString(pre);
			//System.out.print("reconstruct tree by pre-order, ");
			printTree(head);

			level = serializeByLevel(head);
			System.out.println("serialize tree by level: " + level);
			head = reconByLevelString(level);
			System.out.print("reconstruct tree by level, ");
			printTree(head);

			System.out.println("====================================");
		}

}
