package class03;

import java.util.Stack;

public class Code11_IsPalindromList {

	public static class Node{
		int value;
		Node next;
		Node(int value){
			this.value = value;
		}
	}
	
	//方法一： 消耗额外空间O(n)
	public static boolean isPalindromList1(Node head) {
		
		Stack<Node> stack = new Stack<Node>();
		if (head == null || head.next == null) {
			return true;
		}
		//遍历第一遍全部压入栈
		Node cur = head;
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		
		//遍历第二遍，挨个弹出栈比较
		while(!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	//方法2 快慢指针： 消耗O(n/2)额外空间
	public static boolean isPalindromList2(Node head) {
		
		if (head == null && head.next == null) {
			return true;
		}
		Node slow = head;//慢指针
		Node fast = head;//快指针
		//找中点
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		//后半段放入栈中		
		Stack<Node> stack = new Stack<>();
		while(slow != null) {
			stack.push(slow);
			slow = slow.next;
		}
		
		//弹栈比较
		while(!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	//方法3 快慢指针： O(1)额外空间
	public static boolean isPalindromList3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		//找中点（计数是中间，偶数是中间两数的前一个,如1231找到2为中点）
		Node slow = head;
		Node fast = head;
		while(fast.next != null && fast.next.next != null) {//fast用来判断循环的，结果只要slow这个中点值就行了
			slow = slow.next;
			fast = fast.next.next;
		}

		Node rightHead = reverseList(slow);//反转右半部分链表

		//对比前后结点值是否一样
		boolean result = true;
		Node endNode = rightHead; //保存右边链表表头，以备恢复链表
		while(head != null) {
			if (head.value != rightHead.value) {
				result = false;
				break;
			}
			head = head.next;
			rightHead = rightHead.next;
		}
		
		//右半部分恢复，反转回来
		reverseList(endNode);
		
		return result;
	}
	
	//反转链表
	private static Node reverseList(Node head) {
		Node next = null;
		Node pre = null;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(1);

		//判断之前输出链表
		Node cur = head;
		System.out.println("链表判定前：");
		while(cur != null) {
			System.out.print(cur.value+" ");
			cur = cur.next;
		}
		System.out.println();
		System.out.println("方法三："+isPalindromList3(head));
		//判断之后输出链表
		cur = head;
		System.out.println("链表判定后：");
		while(cur != null) {
			System.out.print(cur.value+" ");
			cur = cur.next;
		}
		System.out.println();
		
		//System.out.println("方法一："+isPalindromList1(head));
		//System.out.println("方法二："+isPalindromList2(head));
	}
}
