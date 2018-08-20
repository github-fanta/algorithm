package advanced_class03;

import java.util.Stack;

public class Code_04_maximalRectangle {
	
	public static int maxRecSize(int[][] map) {
		if(map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];   //辅助数组，和列数相等。表示此列的高度
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;  //第i行的所有列的连续高度
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}
	//以某层为底边的直方图的最大面积
	private static int maxRecFromBottom(int[] height) {

		if(height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		//遍历数组中的每一个栈
		Stack<Integer> stack = new Stack<Integer>();  //单调栈（栈底到栈顶：由小到大）
		for(int i = 0; i < height.length; i++) {
			while(!stack.isEmpty() && height[i] <= height[stack.peek()]) { //当前数（列高）小于等于栈顶
				int j = stack.pop();   //弹出时收集信息
				int k = stack.isEmpty() ? -1 : stack.peek();  //k:左边界的列号
				int curArea = (i-k-1) * height[j];  //当前扫过范围*当前行高
				maxArea = Math.max(curArea, maxArea);
			}
			stack.push(i);
		}
		
		//结算栈中剩下的元素
		while(!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	} 
	
	public static void main(String[] args) {
		int[][] data = {
				{1,0,1,1},
				{1,1,1,1},
				{1,1,1,0}
		};
		System.out.println(maxRecSize(data));
	}
}
