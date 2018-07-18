package class03;

import java.util.Stack;
/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作，pop、push、getMin操作的时间复杂度都是O（1）
 * @author liq
 *
 */
public class Code02GetMinStack {

	public static class MyStack{
		private Stack<Integer> dataStack;
		private Stack<Integer> minStack;
		
		public MyStack(Stack<Integer> dataStack, Stack<Integer> minStack) {
			this.dataStack = dataStack;
			this.minStack = minStack;
		}

		public void push(int newNum) {
			
			dataStack.push(newNum);
			if (minStack.isEmpty()) {
				minStack.push(newNum);
			}else if (newNum < minStack.peek()) {
				minStack.push(newNum);
			}else {
				minStack.push(minStack.peek());
			}
		}
		
		public Integer pop() {
			if (dataStack.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			minStack.pop(); //跟随dataStack一起弹栈
			return dataStack.pop();
		}
		
		public Integer getMin() {
			if (minStack.isEmpty()) {
				throw new RuntimeException("栈为空");
			}
			return minStack.peek();
		}
	}
	public static void main(String[] args) {
		Stack<Integer> dataStack = new Stack<>();
		Stack<Integer> minStack = new Stack<>();
		MyStack myStack = new MyStack(dataStack, minStack);
		myStack.push(3);
		System.out.println(myStack.getMin());
		myStack.push(5);
		System.out.println(myStack.getMin());
		myStack.push(4);
		System.out.println(myStack.getMin());
		myStack.push(1);
		System.out.println(myStack.getMin());
	}
}
