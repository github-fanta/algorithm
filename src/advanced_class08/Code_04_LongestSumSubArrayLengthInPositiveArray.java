package advanced_class08;
/**
 * 【数组三联问题】其2：
 * 给定一个数组arr，全是正数；一个整数aim，求累加和等
         于aim的，最长子数组的长度，要求额外空间复杂度O(1)(所以不能用map了，只能用双指针窗口结构)，时间
         复杂度O(N)
 * @author liq
 *
 */
public class Code_04_LongestSumSubArrayLengthInPositiveArray {

	public static int getMaxLength(int[] arr, int aim) {
		if(arr == null || arr.length == 0 || aim <= 0) {
			return 0;
		}
		
		int L = 0;
		int R = 0;
		int winSum = arr[0]; //窗口内子数组元素的和
		int maxLen = 0;  //要求的最大子数组长度
		while(R < arr.length) {
			if(winSum == aim) {
				maxLen = Math.max(maxLen, R - L + 1 );
				winSum -= arr[L++];  //L往右走，找以下一个元素开头的子数组元素和为aim的子数组
			}else if(winSum < aim){
				R ++;
				if(R == arr.length) {
					break;
				}
				winSum += arr[R];
			}else { //winSum > aim
				winSum -= arr[L++];  //winSum 已经大于了aim了，说明以L开头的子数组的和再也找不到aim了
			}
		}
		return maxLen;
	}
	
	public static int[] generatePositiveArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 10) + 1;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 20;
		int k = 4;
		int[] arr = generatePositiveArray(len);
		printArray(arr);
		System.out.println(getMaxLength(arr, k));
	}

}
