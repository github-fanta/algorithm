package class03;

public class Code10_PrintCommonPart {

	public static class Node{
		int value;
		Node next;
		Node(int value){
			this.value = value;
		}
	}
	
	public static void printCommonPart(Node head1, Node head2) {
		while(head1 != null && head2 != null) {
			if (head1.value > head2.value) {
				head2 = head2.next;
			}else if(head2.value > head1.value) {
				head1 = head1.next;
			}else {
				System.out.print(head1.value+" ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
	}
	
	public static void main(String[] args) {
		Node head1 = new Node(0);
		head1.next = new Node(1);
		head1.next.next = new Node(5);
		head1.next.next.next = new Node(7);
		
		Node head2 = new Node(1);
		head2.next = new Node(5);
		head2.next.next = new Node(6);
		head2.next.next.next = new Node(7);
		
		printCommonPart(head1, head2);
	}
}
