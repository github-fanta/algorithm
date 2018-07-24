package class04;

import java.awt.HeadlessException;
import java.util.Stack;

/**
 * 二叉树的前中后序遍历
 * @author liq
 *
 */
public class Code_01_PreInPosTraversal {

	public static class Node{
		int value;
		Node left;
		Node right;
		Node(int value){
			this.value = value;
		}
	}
	
	/**
	 * 方法1：递归方式
	 */
	
	//1.1前序递归遍历
	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}
	//1.2中序递归遍历
	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.println(head.value);
		inOrderRecur(head.right);
	}
	
	//1.3后续递归遍历
	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.println(head.value);
	}
	
	/**
	 * 方法2：非递归
	 */
	//2.1 前序非递归遍历
	public static void preOrderUnRecur(Node head) {
		
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(head);
		Node temp = null;
		while (!stack.isEmpty()) {
			temp = stack.pop();
			System.out.println(temp.value);
			if (temp.right != null) stack.add(temp.right);
			if (temp.left  != null) stack.add(temp.left);
		}
	}
	
	//2.2中序非递归遍历
	public static void inOrderUnRecur(Node head) {
		
		if(head == null) {
			return;
		}
		
		Stack<Node> stack = new Stack<Node>();
		Node temp = head;
		while(!stack.isEmpty() || temp != null) {
			
			if (temp != null) {
				stack.push(temp);
				temp = temp.left;
			}else {
				temp = stack.pop();
				System.out.println(temp.value);
				temp = temp.right;
			}
		}
	}
	
	//2.3后续非递归遍历
	public static void posOrderUnRecur(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		stack1.push(head);
		Node temp = null;
		while(!stack1.isEmpty()) {
			temp = stack1.pop();
			if (temp.left != null)stack1.push(temp.left);
			if (temp.right != null)stack1.push(temp.right);
			stack2.push(temp);  //该出栈的时候不出栈而是压入另一个栈中
		}
		while(!stack2.isEmpty()) {
			System.out.println(stack2.pop().value);
		}
	}
	
	
	/**
	 * 方法3：终极方法——面向对象思维封装操作
	 */
	//封装操作类
	public static class NodeWithOpe{
		Node node;
		int ope; //0：visti 1:print
		public NodeWithOpe(Node node, int ope) {
			this.node = node;
			this.ope = ope;
		}
	}
	
	//3.1 :带操作的先序遍历
	public static void preOrderWithOperation(Node head) {
		if (head == null) {
			return;
		}
		
		Stack<NodeWithOpe> stack = new Stack<NodeWithOpe>();
		stack.push(new NodeWithOpe(head, 0));
		NodeWithOpe temp = null;
		while(!stack.isEmpty()) {
			temp = stack.pop();
			if (temp.ope == 1) {
				System.out.println(temp.node.value);
			}else {
				if (temp.node.right != null) stack.push(new NodeWithOpe(temp.node.right, 0));
				if (temp.node.left != null) stack.push(new NodeWithOpe(temp.node.left, 0));
				stack.push(new NodeWithOpe(temp.node, 1));
			}
		}
	}
	
	//3.2 :带操作的中序遍历
	public static void inOrderWithOperation(Node head) {
		if (head == null) {
			return;
		}
		
		Stack<NodeWithOpe> stack = new Stack<NodeWithOpe>();
		stack.push(new NodeWithOpe(head, 0));
		NodeWithOpe temp = null;
		while(!stack.isEmpty()) {
			temp = stack.pop();
			if (temp.ope == 1) {
				System.out.println(temp.node.value);
			}else {
				if (temp.node.right != null) stack.push(new NodeWithOpe(temp.node.right, 0));
				stack.push(new NodeWithOpe(temp.node, 1));
				if (temp.node.left != null) stack.push(new NodeWithOpe(temp.node.left, 0));
			}
		}
	}
	
	//3.2 :带操作的后序遍历
	public static void posOrderWithOperation(Node head) {
		if (head == null) {
			return;
		}
		
		Stack<NodeWithOpe> stack = new Stack<NodeWithOpe>();
		stack.push(new NodeWithOpe(head, 0));
		NodeWithOpe temp = null;
		while(!stack.isEmpty()) {
			temp = stack.pop();
			if (temp.ope == 1) {
				System.out.println(temp.node.value);
			}else {
				stack.push(new NodeWithOpe(temp.node, 1));
				if (temp.node.right != null) stack.push(new NodeWithOpe(temp.node.right, 0));
				if (temp.node.left != null) stack.push(new NodeWithOpe(temp.node.left, 0));
			}
		}
	}
	
	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();
		
		// unrecursive
			System.out.println("============unrecursive=============");
			System.out.println("先序："); preOrderUnRecur(head);System.out.println(); 
			System.out.println("中序："); inOrderUnRecur(head); System.out.println(); 
			System.out.println("后序："); posOrderUnRecur(head);System.out.println(); 
			
			System.out.println("============ooMethod=============");
			System.out.println("先序："); preOrderWithOperation(head); System.out.println();
			System.out.println("中序："); inOrderWithOperation(head);  System.out.println();
			System.out.println("后序："); posOrderWithOperation(head); System.out.println();
	}
}
