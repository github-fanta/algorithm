package advanced_class03;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * 有一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置；找出所有窗口最大值
 * 例如[4,3,5,4,3,3,6,7] 窗口大小为3结果返回[5,5,5,4,6,7]
 * @author liq
 * 窗口内最大值的更新结构
 */
public class Code_01_SlidingWindowMaxArray {
	
	public static ArrayList<Integer> getMaxWindow(int[] arr, int winSize) {
		if(arr == null || arr.length == 0 || winSize < 1) {
			return null;
		}
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		ArrayList<Integer> resultList = new ArrayList<>();

		for(int i = 0; i < arr.length; i++) {
			//要进来一个
			while(!qMax.isEmpty() && arr[i] >= arr[qMax.peekLast()] ) {  //（新进窗口一个，将比它小的全部挤出去）将队列中所有小于当前要加入点的值，的序号弹出，因为它们再也没有机会成为窗口最大值了
				qMax.pollLast();
			}
			qMax.addLast(i);  //（上位， 有可能挤掉了前一个窗口的最小值，也可能没有，让它过期处理）放入当前节点序号进队列
			//出去一个
			if(qMax.peekFirst() == i - winSize)  qMax.pollFirst();  //若过期，弹出队列首
			//获取解
			if(i >= winSize - 1) resultList.add(arr[qMax.peekFirst()]);  //从形成window的序号开始，每个窗口获取最大值
		}
		return resultList;
	}
	
	public static void main(String[] args) {
		int[] data = {2,3,4,2,6,2,5,1};
		ArrayList<Integer> list = getMaxWindow(data, 3);
		for (int i : list) {
			System.out.print(i+" ");
		}
	}
}
