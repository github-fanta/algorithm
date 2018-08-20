package class03;
/**
 * 用数组结构实现大小固定的队列和栈
 * @author liq
 *
 */
public class Code01ArrayToStackQueue {

	/*
	 * 数组栈
	 */
	public static class ArrayStack<T>{
		private int[] arr;
		private int size;
		
		public ArrayStack(int initSize) {
			if (initSize < 1) {
				throw new IllegalArgumentException("初始值小于0");
			}
			this.arr = new int[initSize];
			this.size = 0;
		}
		
		public void push(int obj) {
			if (size == arr.length) {
				throw new IndexOutOfBoundsException("超出容量");
			}
			arr[size++] = obj;
		}
		
		public Integer pop() {
			if (0 == size) {
				throw new IndexOutOfBoundsException("栈为空");
			}
			return arr[--size];
		}
		
		public Integer peek() {
			if (0 == size) {
				return null;
			}
			return arr[size-1];
		}
	}
	
	
	
	/*
	 * 数组队列
	 */
	public static class ArrayQueue{
		private int[] arr;
		private int size;
		private int head;
		private int tail;
		
		public ArrayQueue(int initSize) {
			if (initSize < 1) {
				throw new IllegalArgumentException("初始值小于1");
			}
			arr = new int[initSize];
			size = 0;
			head = 0;
			tail = 0;
		}
		
		public void push(int obj) {
			if (size == arr.length) {
				throw new IndexOutOfBoundsException("队列已满");
			}
			size++;
			arr[tail] = obj;
			tail = tail == arr.length-1 ? 0 : tail+1;
		}
		
		public Integer poll() {
			if (0 == size) {
				throw new IndexOutOfBoundsException("队列为空");
			}
			size--;
			int temp = head;
			head = head == arr.length-1? 0 : head+1;
			return arr[temp];
		}
		
		public Integer peek() {
			if (0 == size) {
				return null;
			}
			return arr[head];
		}
		
		
	}
	
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(5);
		stack.push(0);stack.push(1);stack.push(2);stack.push(3);stack.push(4);//stack.push(5);
		for (int i=0; i<5; i++) {
			System.out.println("STACK-----peek():"+stack.peek());
			System.out.println("STACK-----stack():"+stack.pop());
		}
		
		
		ArrayQueue queue = new ArrayQueue(5);
		queue.push(0);queue.push(1);queue.push(2);queue.push(3);queue.push(4);//queue.push(5);
		for(int i=0; i<5; i++) {
			System.out.println("QUEUE-----peek()"+queue.peek());
			System.out.println("QUEUE-----stack()"+queue.poll());
		}
	}
}
