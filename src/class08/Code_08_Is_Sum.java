package class08;
/**
 * 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的
 * 数字，能不能累加得到aim，返回true或者false
 * @author liq
 *
 */
public class Code_08_Is_Sum {

	//暴力递归来试
	public static boolean isSum(int[] arr, int i, int sum, int aim) {
		if(i == arr.length) {
			return sum == aim;
		}
		return isSum(arr, i + 1, sum, aim) || isSum(arr, i + 1, sum + arr[i], aim);//每个位置有两种选择：加自己或者不加自己。之后推进到i+1
	}
	
	//改成动态规划版本
	public static boolean isSumDp(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		if (aim > sum) {
			return false;
		}
		
		boolean [][] dp = new boolean[arr.length+1][sum+1]; //多加一行用于表示终止状态作为base case
		dp[arr.length][aim] = true;
		
		for(int i = arr.length - 1; i >= 0; i--) {
			for(int j = 0; j <= sum; j++) {
				dp[i][j] = dp[i+1][j];
				if(j + arr[i] <= sum) {
					dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
				}
			}
		}
		return dp[0][0];
	}
	
	//优化
	public static boolean isSumDpOptimized(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		boolean[][] dp = new boolean[arr.length + 1][aim + 1];
		for(int i = arr.length; i >= 0; i --) {
			dp[i][aim] = true;
		}
		
		for(int i = arr.length - 1; i >= 0; i--) {
			for(int j = 0; j <= aim; j++) {
				dp[i][j] = j + arr[i] > aim ? dp[i+1][j] : dp[i+1][j] || dp[i + 1][j + arr[i]];
			}
		}
		
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		int[] arr = { 1, 4, 8, 20};
		int aim = 31;
		System.out.println(isSum(arr, 0, 0, aim));
		System.out.println(isSumDp(arr, aim));
		System.out.println(isSumDpOptimized(arr, aim));
	}
	
	
}
