package advanced_class08;
/**
 * 【数组三联问题】其3：
 * 给定一个数组arr，值可正，可负，可0；一个整数aim，求累加
 * 和小于等于aim的，最长子数组的长度，要求时间复杂度O(N)
 * @author liq
 *
 */
public class Code_05_LongestSubarrayLessSumAwesomeSolution {

	public static int maxLengthAwesome(int[] arr, int aim) {
		if(arr == null || arr.length == 0) return 0;
		
		int[] sums = new int[arr.length];
		int[] ends = new int[arr.length];
		sums[arr.length - 1] = arr[arr.length - 1];
		ends[arr.length - 1] = arr.length - 1;
		
		//生成两个辅助数组的信息
		for(int i = arr.length - 2; i >= 0; i--) {
			if(sums[i + 1] < 0) {               //后一个的最小累加和有利可图
				sums[i] = arr[i] + sums[i + 1]; //那就加上
				ends[i] = ends[i + 1];          //此时我的右边界是后一个的右边界
			}else { //无利可图
				sums[i] = arr[i];
				ends[i] = i;  					//此时我的右边界是我自己
			}
		}
		
		int R = 0;   //扩到的右边界
		int sum = 0; //start~end窗口内的累加和是多少
		int maxLen = 0; //全局最大值
		for(int start = 0; start < arr.length; start ++) {
			while(R < arr.length && sum + sums[R] <= aim) {  //没有撑死，继续往右扩
				sum += sums[R];
				R = ends[R] + 1; //R放在下一个要扩出去的块的第一个元素上
			}
			maxLen = Math.max(maxLen, R - start); //更新最大跨度  （R-1） - start +　１
			if(R > start) sum -= arr[start]; //如果开始L==R，窗口此时为L->R-1位置，L位置的数根本就没有进入窗口，不能随便减 start位置的数字   R > start说明R没有被绊倒(向右扩出去了)，说明以此start开头的符合条件的子区间存在， 将第一个元素(start位置)移出窗口。
			//defensive——如果没有扩动咋办
			R = Math.max(R, start + 1);  //保持R在start右边
		}
		return maxLen;
	}
	
	public static int maxLength(int[] arr, int k) {
		int[] h = new int[arr.length + 1];
		int sum = 0;
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		int res = 0;
		int pre = 0;
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			pre = getLessIndex(h, sum - k);
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	public static int getLessIndex(int[] arr, int num) {
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		int res = -1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] >= num) {
				res = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			int[] arr = generateRandomArray(10, 20);
			int k = (int) (Math.random() * 20) - 5;
			if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
				System.out.println("oops!");
			}
		}

	}

}
