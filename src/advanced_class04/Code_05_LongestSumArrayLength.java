package advanced_class04;

import java.util.HashMap;
import java.util.Map;

/**
 * 【数组三联问题】其1：数组可正，可负，可0.求最长子数组和为aim的长度
 * @author liq
 *
 */


public class Code_05_LongestSumArrayLength {

	public static int maxLength(int[] arr, int aim) {
		if(arr == null || arr.length == 0) return 0;
		Map<Integer, Integer> sumMap = new HashMap<Integer,Integer>();
		sumMap.put(0, -1);  //前i项目和  与  位置i的映射
		int maxLen = 0;
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(sumMap.get(sum) == null) sumMap.put(sum, i);  //此长度没有记录过
			if(sumMap.containsKey(sum - aim)) { 
				maxLen = Math.max(maxLen, i - sumMap.get(sum - aim)); //更新最长
			}
		}
		return maxLen;
	}
	
	public static void main(String[] args) {
		int[] data = {7, 7, -7, 7, 7};
		System.out.println(maxLength(data, 14));
	}
}
