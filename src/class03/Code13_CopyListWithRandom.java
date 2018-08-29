package class03;

import java.util.HashMap;
import java.util.Map;

public class Code13_CopyListWithRandom {

	public static class Node{
		int value;
		Node next;
		Node rand;
		Node(int value){
			this.value = value;
		}
	}
	//方法1：用hash表实现
	public static Node CopyListWithRandom1(Node head) {
		if (head == null) {
			return head;
		}
		//复制所有节点放入hash表中
		Map<Node, Node> map = new HashMap<Node, Node>();
		Node temp = head;
		while(temp != null) {
			map.put(temp, new Node(temp.value));
			temp = temp.next;
		}
		
		//连接hash表中的新创建的结点之间的关系
		temp = head;
		while(temp != null) {
			map.get(temp).next = map.get(temp.next);
			map.get(temp).rand = map.get(temp.rand);
			temp = temp.next;
		}
		//返回原始head对应的新链表的头结点
		return map.get(head);
	}
	
	//方法2：不用hash表实现
	public static Node CopyListWithRandom2(Node head) {
		if (head == null) {
			return head;
		}
		
		//复制当前结点放入后面
		Node temp = head;
		Node nextHolder = null;
		while(temp != null) {
			nextHolder = temp.next;
			temp.next = new Node(temp.value);
			temp.next.next = nextHolder;
			temp = nextHolder;
		}
		//从头开始，连接rand指针
		temp = head;
		while(temp != null) {
			temp.next.rand = temp.rand != null ? temp.rand.next : null;  //temp的rand有可能没有
			temp = temp.next.next; //一次跳两步  //因为上面复制了，故temp的next是一定存在的
		}
		
		//分离新旧链表
		temp = head;			 	 //原始链表的遍历变量
		Node copyHead = temp.next;   //记录拷贝链表的表头
		Node copyTemp = null;		 //拷贝链表的遍历变量
		while(temp != null) {
			copyTemp = temp.next;    //temp.next一定存在
			temp.next = copyTemp.next;
			//copyTemp.next = copyTemp.next != null ? copyTemp.next.next : null;
			copyTemp.next = temp.next != null ? temp.next.next : null;
			temp = temp.next;
		}
		return copyHead;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4
		
		//Node copyHead = CopyListWithRandom1(head);
		Node copyHead = CopyListWithRandom2(head);
		printRandLinkedList(copyHead);
	}
	
}
