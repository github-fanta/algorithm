package class08;
/*
 *  给你一个二维数组，二维数组中的每个数都是正数，要求从左上
 *	角走到右下角，每一步只能向右或者向下。沿途经过的数字要累
 *  加起来。返回最小的路径和。
 * 
 */
public class Code_07_MinPath {

	//暴力递归
	public static int minPathRec(int[][] m, int curRow, int curCol) {
		if(curRow == m.length - 1 && curCol == m[0].length - 1) {
			return m[m.length - 1][m[0].length - 1]; //返回有下角值
		}
		if(curRow == m.length - 1) {
			return m[curRow][curCol] + minPathRec(m, curRow, curCol + 1);
		}
		if(curCol == m[0].length - 1) {
			return m[curRow][curCol] + minPathRec(m, curRow + 1, curCol);
		}
		
		int toRightCost = minPathRec(m, curRow, curCol + 1);
		int toDownCost = minPathRec(m, curRow + 1, curCol);
		return m[curRow][curCol] + Math.min(toRightCost, toDownCost);
	}
	//改动态规划
	public static int minPathDp(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length-1;     //行号
		int col = m[0].length-1;  //列号
		int[][] dp = new int[m.length][m[0].length];
		dp[row][col] = m[row][col];
		//最右列从下到上填写dp表
		for(int i = row-1; i >= 0; i--) {
			dp[i][col] = m[i][col] + dp[i + 1][col];
		}
		//最下行从右到左填写dp表
		for(int j = col-1; j >= 0; j--) {
			dp[row][j] = m[row][j] + dp[row][j+1];
		}
		//填写其他地方
		for(int i = row - 1; i >= 0 ; i--) {
			for(int j = col - 1; j >= 0; j--) {
				dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) + m[i][j];
			}
		}
		return dp[0][0];
		
	}
	public static void main(String[] args) {
		int[][] m = { { 10, 0, 0 }, { 8, 11, 10 }, { 5, 100, 20 }, { 8, 99, 44} };
		System.out.println(minPathRec(m, 0, 0));
		System.out.println(minPathDp(m));
	}
}
