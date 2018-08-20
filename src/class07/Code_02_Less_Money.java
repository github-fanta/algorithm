package class07;

import java.util.PriorityQueue;

/**
 * 分割黄金
 * @author liq
 *
 */
public class Code_02_Less_Money {

	public static int lessMoney(int[] arr) {
		if (arr == null || arr.length <= 0) {
			return -1;
		}
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (Integer i : arr) {
			heap.add(i);
		}
		int sum = 0;
		int cutNum = 0;
		while(heap.size() > 1) {
			sum = heap.poll() + heap.poll();  //把最小的两个拿出来
			heap.add(sum);					  //求和后再放进去
			cutNum ++;
		}
		return cutNum;
	}
	
	public static void main(String[] args) {
		// solution
				int[] arr = { 6, 7, 8, 9 };
				System.out.println(lessMoney(arr));

				int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

				// min heap
				PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
				for (int i = 0; i < arrForHeap.length; i++) {
					minQ1.add(arrForHeap[i]);
				}
				while (!minQ1.isEmpty()) {
					System.out.print(minQ1.poll() + " ");
				}
				System.out.println();

				// min heap use Comparator(with Lambda Expression)
				PriorityQueue<Integer> minQ2 = new PriorityQueue<>((x, y) -> x-y);
				for (int i = 0; i < arrForHeap.length; i++) {
					minQ2.add(arrForHeap[i]);
				}
				while (!minQ2.isEmpty()) {
					System.out.print(minQ2.poll() + " ");
				}
				System.out.println();

				// max heap use Comparator(with Lambda Expression)
				PriorityQueue<Integer> maxQ = new PriorityQueue<>((x, y) -> y-x);
				for (int i = 0; i < arrForHeap.length; i++) {
					maxQ.add(arrForHeap[i]);
				}
				while (!maxQ.isEmpty()) {
					System.out.print(maxQ.poll() + " ");
				}

		}
	
}
