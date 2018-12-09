package advanced_class03;

import java.util.LinkedList;

/*
 * 最大值减去最小值小于或等于num的子数组数量 复杂度O（N）
 */
public class Code_03_AllLessNumSubArray {

	public static int getNum(int[] arr, int num) {
		if(arr == null || arr.length == 0) return 0;
		LinkedList<Integer> qMin = new LinkedList<Integer>(); 
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		int L = 0;
		int R = 0;
		int count = 0;
		while(L < arr.length) {
			while(R < arr.length) {
				while(!qMin.isEmpty() && arr[R] <= arr[qMin.peekLast()]) {
					qMin.pollLast();
				}
				qMin.addLast(R);  //更新最小值结构
				while(!qMax.isEmpty() && arr[R] >= arr[qMax.peekLast()]) {
					qMax.pollLast();
				}
				qMax.addLast(R); //更新最大值结构
				if(arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) break; //不达标，停止扩张。此时已经大了，R再往后也还是不满足，跳出，让L往右边走
				
				R ++; //继续扩张
			}
			count += ((R-1) - L + 1); //while中R多加了一。L到R-1的所有子数组数量
			if(qMin.peekFirst() == L) qMin.pollFirst();  //弹出过期最小值
			if(qMax.peekFirst() == L) qMax.pollFirst();  //弹出过期旧最大值
			L ++;  //换一个子数组的开头
			
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] data = {6,21,27,999999,-12, 5,1};
		System.out.println(getNum(data, 4));
	}
}
