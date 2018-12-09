package class07;
/**
 * 做项目（贪心） LeetCode 502 IPO
 * 输入：
 *  参数1，正数数组costs 
 *  参数2，正数数组profits 
 *  参数3，正数k，表示你不能并行、只能串行的最多做k个项目
 *  参数4，正数m，m表示你初始的资金
	costs[i]表示i号项目的花费 
	profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
	说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个 项目。
    输出： 你最后获得的最大钱数。
 * @author liq
 *
 */
import java.util.PriorityQueue;

public class Code_03_IPO {
	public static class Node{
		int cost;
		int profit;
		Node(int cost, int profit){
			this.cost = cost;
			this.profit = profit;
		}
	}
	
	public static int findMaximizedCapital(int money, int maxProjectsNum, int[] cost, int[] profit) {
		
		PriorityQueue<Node> minCostHeap = new PriorityQueue<>((x,y) -> x.cost - y.cost);
		PriorityQueue<Node> maxProfitHeap = new PriorityQueue<>((x,y) -> y.profit - x.profit);
		for(int i=0; i<cost.length; i++) {
			minCostHeap.add(new Node(cost[i], profit[i]));
		}
		
		for(int i=0; i<maxProjectsNum; i++) {   //开始做maxProjectsNum个项目
			//解锁新项目放入大根堆
			while(!minCostHeap.isEmpty() && minCostHeap.peek().cost < money) {
				maxProfitHeap.add(minCostHeap.poll());
			}
			if (maxProfitHeap.isEmpty()) {    //没有项目添加到大根堆，说明钱不够解锁新的项目了，不得不停
				return money;
			}
			money += maxProfitHeap.poll().profit;
		}
		
		return money;
	}
	
	public static void main(String[] args) {
		int[] cost = new int[] {12,44, 89, 99};
		int[] profit = new int[] {5,-10, 23, 400};
		
		System.out.println("最大资本："+findMaximizedCapital(100, 4, cost, profit));
	}
}
