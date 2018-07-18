package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用两个队列实现栈
 * @author liq
 *
 */
public class Code03StackAndQueueConvert {

	public static class TwoQueuesStack{
		private Queue<Integer> dataQueue;
		private Queue<Integer> helpQueue;
		public TwoQueuesStack() {
			 this.dataQueue = new LinkedList<Integer>();
			 this.helpQueue = new LinkedList<Integer>();
		}
		
		public void push(int num) {
			dataQueue.add(num);
		}
		
		public Integer pop() {
			if (dataQueue.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			while(dataQueue.size() > 1) {
				helpQueue.add(dataQueue.poll());
			}
			int result = dataQueue.poll();
			swapRole();
			return result;
		}

		public Integer peek() {
			if (dataQueue.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			while(dataQueue.size() > 1) {
				helpQueue.add(dataQueue.poll());
			}
			int result = dataQueue.poll();
			helpQueue.add(result);
			swapRole();
			return result;
		}
		private void swapRole() {
			Queue<Integer> temp = dataQueue;
			dataQueue = helpQueue;
			helpQueue = temp;
		}
		
	}
	
	public static class TwoStacksQueue{
		private Stack<Integer> pushStack;
		private Stack<Integer> popStack;
		public TwoStacksQueue() {
			this.pushStack = new Stack<Integer>();
			this.popStack = new Stack<Integer>();
		}
		
		public void add(int num) {
			pushStack.push(num);
		}
		
		public Integer poll() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				throw new RuntimeException("队列为空");
			}else if(popStack.isEmpty()) {	//popStack必须为空才能倒
				while(!pushStack.isEmpty()) {	//pushStack要倒就倒完
					popStack.push(pushStack.pop());
				}
			}
			
			return popStack.pop();
		}
		
		public Integer peek() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				throw new RuntimeException("队列为空");
			}else if (popStack.isEmpty()) {
				while(!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
			return popStack.peek();
		}
		
	}
	
	public static void main(String[] args) {
		TwoQueuesStack myStack = new TwoQueuesStack();
		myStack.push(-98);
		myStack.push(100042);
		myStack.push(6);
		
		for(int i=0; i<3; i++) {
			System.out.println("peek()"+ myStack.peek());
			System.out.println("pop()"+ myStack.pop());
		}
		
		TwoStacksQueue queue = new TwoStacksQueue();
		queue.add(-9823);
		queue.add(2);
		queue.add(198);
		for(int i=0; i<3; i++) {
			System.out.println("peek()"+ queue.peek());
			System.out.println("pop()"+ queue.poll());
		}
	}
	
}
