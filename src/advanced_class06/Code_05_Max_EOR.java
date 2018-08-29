package advanced_class06;
/**
 * 给定一个数组，求子数组的最大异或和。
 * 一个数组的异或和为，数组中所有的数异或起来的结果。
 * @author liq
 *
 */
public class Code_05_Max_EOR {

	public static class Node{
		Node[] paths = new Node[2];
	}
	
	//前缀树
	public static class NumTrie{
		Node head = new Node();
		
		//前缀树添加所有0~i的异或和的二进制位路径
		public void add(int num) {
			Node cur = head;
			for(int i = 31; i >= 0; i--) {
				int path = ((num >> i) & 1);
				if(cur.paths[path] == null) cur.paths[path] = new Node();
				cur = cur.paths[path];
			}
		}
	
		//已知：0~i的异或和eor ，返回：0~i的最大异或和
		public int maxXor(int eor) {
			Node cur = head;
			int result = 0;
			for(int i = 31; i >= 0; i--) {
				int path = (eor >> i) & 1; //取出每一位的值
				int bestPath = i == 31 ? path : path^1;  //期待要选的路，符号位期待相同，非符号位期待相反
				if(cur.paths[bestPath] == null) bestPath ^= 1; //没有这条路只能选另外一条
				result |= (path ^ bestPath) << i; //异或的结果，此位正好是原0~x的异或和 eor 跟 找到的路径之间的异或值（异或掉头部）
				cur = cur.paths[bestPath];
			}
			return result;
		}
	}
	
	public static int maxXorSubarray(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int eor = 0;
		NumTrie numTrie = new NumTrie();
		numTrie.add(0);
		for(int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			max = Math.max(max, numTrie.maxXor(eor));//前缀树作用：我保存着你之前所有的dp结果0~1,0~2..0~i-1，你只要扔给我个异或和，我能给你选择一个0~i内最大的异或和
			numTrie.add(eor);
		}
		return max;
	}
	
	
	// for test
		public static int comparator(int[] arr) {
			if (arr == null || arr.length == 0) {
				return 0;
			}
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < arr.length; i++) {
				int eor = 0;
				for (int j = i; j < arr.length; j++) {
					eor ^= arr[j];
					max = Math.max(max, eor);
				}
			}
			return max;
		}

		// for test
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
			}
			return arr;
		}

		// for test
		public static void printArray(int[] arr) {
			if (arr == null) {
				return;
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}

		// for test
		public static void main(String[] args) {
			int testTime = 500000;
			int maxSize = 30;
			int maxValue = 50;
			boolean succeed = true;
			for (int i = 0; i < testTime; i++) {
				int[] arr = generateRandomArray(maxSize, maxValue);
				int res = maxXorSubarray(arr);
				int comp = comparator(arr);
				if (res != comp) {
					succeed = false;
					printArray(arr);
					System.out.println(res);
					System.out.println(comp);
					break;
				}
			}
			System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		}
}
