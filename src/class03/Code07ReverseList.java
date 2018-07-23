package class03;

public class Code07ReverseList {

	public static class Node{
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
		}
	}
	
	public static Node reverseList(Node head) {
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
		Node node1 = new Node(1);
		Node node2 = new Node(2);node2.next = node1;
		Node node3 = new Node(3);node3.next = node2;
		Node reverseList = reverseList(node3);
		while(reverseList != null) {
			System.out.println(reverseList.value);
			reverseList = reverseList.next;
		}
	}
}
