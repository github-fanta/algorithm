package class03;

import java.util.ArrayList;

public class Code12_ListPartition {

	public static class Node{
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
		}
	}
	
	//方法一：放入数组中变成荷兰国旗问题
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		
		//获取链表长度
		Node temp = head;
		int length = 0;
		while(temp != null) {
			temp = temp.next;
			length++;
		}
		Node[] arr = new Node[length];
		//链表放入数组中
		temp = head;
		length = 0;
		while(temp != null) {
			arr[length++] = temp;
			temp = temp.next;
		}
		//划分，荷兰国旗问题
		arrPartition(arr, pivot);
		//重组链表
		int i = 1;
		for(i=1; i< arr.length; i++) {
			arr[i-1].next = arr[i];
		}
		arr[i-1].next = null; 
		return arr[0];
	}

	private static void arrPartition(Node[] arr, int pivot) {

		int left = -1;
		int right = arr.length;
		int cur = 0;
		while(cur != right) {
			if (arr[cur].value < pivot) {
				swap(arr, ++left, cur++);
			}else if(arr[cur].value > pivot){
				swap(arr, --right, cur);
			}else {
				cur ++;
			}
		}
	}

	private static void swap(Node[] arr, int i, int j) {
		Node temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	//方法2：将链表装入三个筒之后重连
	public static Node listPartition2(Node head, int pivot) {
		//small筒之中的链表头和尾
		Node sHead = null;
		Node sTail = null;
		//equal筒之中的链表头和尾
		Node eHead = null;
		Node eTail = null;
		//larger筒之中的链表头和尾
		Node lHead = null;
		Node lTail = null;
		Node next = null; //保存下一个结点
		while(head != null) {
			next = head.next;
			head.next = null;
			if (head.value < pivot) {  //放入small筒中
				if (sHead == null) {   //若是放入筒中的第一个元素
					sHead = head;
					sTail = head;
				}else {
					sTail.next = head;
					sTail = sTail.next;
				}
			}else if(head.value == pivot) {  //equal筒
				if (eHead == null) {		//若是放入筒中的第一个元素
					eHead = head;
					eTail = head;
				}else {
					eTail.next = head;
					eTail = eTail.next;
				}
			}else {						//larger筒	
				if (lHead == null) {   //若是放入筒中的第一个元素
					lHead = head;
					lTail = head;
				}else {
					lTail.next = head;
					lTail = lTail.next;
				}
			}
			head = next;
		}
		//small筒和equal筒连接
		if (sHead != null) {
			sTail.next = eHead;
			if (eTail == null) eTail = sTail; //防止第二个筒为空
		}
		
		//equal筒和larger筒连接
		if (eHead != null) {
			eTail.next = lHead;
		}
		
		return sHead != null ? sHead : eHead != null ? eHead : lHead;
	}
	
	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		
		//Node temp = head1;
		//head1 = listPartition1(head1, 5);
		head1 = listPartition2(head1, 5);
		Node temp = head1;
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
	}
}
