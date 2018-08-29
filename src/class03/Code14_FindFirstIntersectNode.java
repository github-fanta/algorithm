package class03;

public class Code14_FindFirstIntersectNode {

	public static class Node{
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 主函数，获取两个单链表的相交部分
	 */
	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		//试图获取入环结点
		Node inLoop1 = getInLoopNode(head1);
		Node inLoop2 = getInLoopNode(head2);
		if (inLoop1 == null && inLoop2 == null) { 	//都没有找到入环点，说明两者都是无环链表
			return noLoop(head1, head2);  			//返回无环链表的相交点
		}
		if (inLoop1 != null && inLoop2 != null) { //两者都是有环链表
			return bothLoop(head1, inLoop1, head2, inLoop2);
		}
		
		return null;  //不存在一个有环，一个无环的两个链表相交。
	}

	/**
	 * 获取单链表的入环结点，无环返回null
	 */
	private static Node getInLoopNode(Node head) {
		
		if (head == null || head.next == null || head.next.next == null) { //保证单链表至少有三个结点
			return null;
		}
		//快慢指针
		Node fast = head.next.next;   //一次走两步
		Node slow = head.next;	//一次走一步
		while(fast != slow) {   //固定结论1：如果有环快慢指针一定会在环上相遇
			if (fast.next == null || fast.next.next == null) { //快慢指针只判断快指针 只要在遍历中遇到null说明无环
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head; //结论2：此时快指针从头开始，两个指针一次走一步。一定会和slow指针相遇
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast; //返回入环点
	}

	/**
	 * 返回无环链表的首个相交点
	 */
	private static Node noLoop(Node head1, Node head2) {

		if (head1 == null || head2 == null) {
			return null;
		}
		int distance = 0;
		Node cur1 = head1;
		Node cur2 = head2;
		while(cur1 != null) {
			distance ++;
			cur1 = cur1.next;
		}
		while(cur2 != null) {
			distance --;
			cur2 = cur2.next;
		}
		
		if (cur1 != cur2) {  //遍历到最后一个结点，不是一个结点，则一定没有相交
			return null;
		}
		cur1 = distance > 0 ? head1 : head2;  //cur1始终指向长链表
		cur2 = cur1 == head2 ? head1 : head2; //cur1指向长链表了，cur2就指向短链表。否则不变
		distance = Math.abs(distance);//两者相差距离绝对值
		
		while(distance != 0) {	//长链表先走这么多步
			cur1 = cur1.next;
			distance --;
		}
		while(cur1 != cur2) {	//再一起走
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}
	
	
	//两链表都有环，返回首个相交点
	private static Node bothLoop(Node head1, Node inLoop1, Node head2, Node inLoop2) {

		Node cur1 = null;
		Node cur2 = null;
		if (inLoop1 == inLoop2) {  //入环点相等：第二种情况：合并成一个单链表后结成一个环。和无环链表处理一样
			int distance = 0;
			cur1 = head1;
			while(cur1 != inLoop1) {
				distance ++;
				cur1 = cur1.next;
			}
			cur2 = head2;
			while(cur2 != inLoop1) {
				distance --;
				cur2 = cur2.next;
			}
			if (cur1 != cur2) {
				return null;
			}
			cur1 = distance > 0 ? head1 : head2;
			cur2 = cur1 == head2 ? head1 : head2;
			distance = Math.abs(distance);
			while(distance != 0) {//长链表先走这么多步
				cur1 = cur1.next;
				distance --;
			}
			while(cur1 != cur2) {//再一起走
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {//第一(两链表各自成环，不相交)或者第三种情况(两个链表共享一个环)
			cur1 = inLoop1.next;
			while(cur1 != inLoop1) {
				if (cur1 == inLoop2) {//环上遇见了第二个入环结点，说明是第三种情况
					return inLoop1;   //或者inLoop1都可以算入环的初始结点
				}
				cur1 = cur1.next;
			}
			return null;  //第一种情况，两个链表不相交
		}
	}
	
	
	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
				Node head1 = new Node(1);
				/*head1.next = new Node(2);
				head1.next.next = new Node(3);
				head1.next.next.next = new Node(4);
				head1.next.next.next.next = new Node(5);
				head1.next.next.next.next.next = new Node(6);
				head1.next.next.next.next.next.next = new Node(7);*/

				// 0->9->8->6->7->null
				Node head2 = new Node(0);
				/*head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next.next.next.next.next; // 8->6
				System.out.println(getIntersectNode(head1, head2).value);*/

				// 1->2->3->4->5->6->7->4...
				head1 = new Node(1);
				head1.next = new Node(2);
				head1.next.next = new Node(3);
				head1.next.next.next = new Node(4);
				head1.next.next.next.next = new Node(5);
				head1.next.next.next.next.next = new Node(6);
				head1.next.next.next.next.next.next = new Node(7);
				head1.next.next.next.next.next.next.next = head1.next.next.next; // 7->4

				// 0->9->8->2...
				/*head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next; // 8->2
				System.out.println(getIntersectNode(head1, head2).value);*/

				/*// 0->9->8->6->4->5->6..
				head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = head1.next.next.next.next.next; // 8->6
				System.out.println(getIntersectNode(head1, head2).value);*/
				
				//各自成环
			/*	head1 = new Node(1);
				head1.next = new Node(2);
				head1.next.next = new Node(3);
				head1.next.next.next = new Node(4);
				head1.next.next.next.next = new Node(5);
				head1.next.next.next.next.next = new Node(6);
				head1.next.next.next.next.next.next = new Node(7);
				head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
				
				head2 = new Node(0);
				head2.next = new Node(9);
				head2.next.next = new Node(8);
				head2.next.next.next = new Node(88); 
				head2.next.next.next.next = head2.next; // 88->9
			
*/			
				System.out.println(getInLoopNode(head1).value);
				if (getIntersectNode(head1, head2) == null) {
					System.out.println("无相交部分");
				}else {
					System.out.println(getIntersectNode(head1, head2).value);
				}
				
	}
	
}
