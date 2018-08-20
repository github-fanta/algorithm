package advanced_class04;

import java.util.HashMap;

/*
 * 异或和最大的子数组个数
 */
public class Code_06_Most_EOR {
	
	public static int mostEOR(int[] arr) {
		if(arr == null || arr.length == 0) return 0;
		int xorSum = 0;
		int[] dp = new int[arr.length];
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for(int i = 0; i < arr.length; i++) {
			xorSum ^= arr[i];
			if(map.containsKey(xorSum)) {  //处于最后一个异或和为0的块的最后一个位置
				int pre = map.get(xorSum);
				dp[i] = pre == -1 ? 1 : dp[pre] + 1; //照顾到arr[0]是0的情况
			}
			if(i >= 1) {
				dp[i] = Math.max(dp[i - 1], dp[i]);  //和 不处于异或和为0的块作比较，取较大值为此处的dp值
			}
			map.put(xorSum, i);
		}
		return dp[dp.length - 1];
	}
	/*public static void main(String[] args) {
		int[] data = {0,3,2,1,0,0};
		System.out.println(mostEOR(data));
	}*/
	// for test
		public static int comparator(int[] arr) {
			if (arr == null || arr.length == 0) {
				return 0;
			}
			int[] eors = new int[arr.length];
			int eor = 0;
			for (int i = 0; i < arr.length; i++) {
				eor ^= arr[i];
				eors[i] = eor;
			}
			int[] mosts = new int[arr.length];
			mosts[0] = arr[0] == 0 ? 1 : 0;
			for (int i = 1; i < arr.length; i++) {
				mosts[i] = eors[i] == 0 ? 1 : 0;
				for (int j = 0; j < i; j++) {
					if ((eors[i] ^ eors[j]) == 0) {
						mosts[i] = Math.max(mosts[i], mosts[j] + 1);
					}
				}
				mosts[i] = Math.max(mosts[i], mosts[i - 1]);
			}
			return mosts[mosts.length - 1];
		}

		// for test
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) ((maxSize + 1) * Math.random())];

			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) ((maxValue + 1) * Math.random());
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
			int maxSize = 300;
			int maxValue = 100;
			boolean succeed = true;
			for (int i = 0; i < testTime; i++) {
				int[] arr = generateRandomArray(maxSize, maxValue);
				//int[] arr = {0,3,2,1,3,2,1};
				int res = mostEOR(arr);
				int comp = comparator(arr);
				if (res != comp) {
					succeed = false;
					printArray(arr);
					System.out.println("your answer:"+res);
					System.out.println("right answer:"+comp);
					break;
				}
			}
			System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		}
}
