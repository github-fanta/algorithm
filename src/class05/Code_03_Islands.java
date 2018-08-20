package class05;
/*
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右
 * 四个位置相连，如果有一片1连在一起，这个部分叫做一个岛，求一个
 * 矩阵中有多少个岛？ （LeetCode 200  Number of Islands）
 */
public class Code_03_Islands {
	
	public static int countIslands(int[][] matrix) {
		if (matrix == null || matrix[0] == null) {
			return 0;
		}
		int ROWS = matrix.length;
		int COLS = matrix[0].length;
		int islandNum = 0;
		for(int i=0; i < ROWS; i++) {
			for(int j=0; j < COLS; j++) {
				if (matrix[i][j] == 1) {
					islandNum++;
					infect(matrix, i, j, ROWS, COLS);
				}
			}
		}
		return islandNum;
	}

	/**
	 * 感染函数
	 * @param matrix
	 * @param i
	 * @param j
	 * @param rOWS
	 * @param cOLS
	 */
	private static void infect(int[][] matrix, int row, int col, int ROWS, int COLS) {
		if ( row < 0 || col < 0 || row >= ROWS || col >= COLS || matrix[row][col] != 1 ) { //matrix[row][col]判断要放在后面，谨防数组越界
			return;
		}
		matrix[row][col] = 2; //将其感染
		infect(matrix, row, col-1, ROWS, COLS); 	//向左感染
		infect(matrix, row, col+1, ROWS, COLS);  	//向右感染
		infect(matrix, row-1, col, ROWS, COLS);  	//向上感染
		infect(matrix, row+1, col, ROWS, COLS);     //向下感染
	}
	
	public static void main(String[] args) {
		int[][] m1 = {  
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
			        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
			        { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
			        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
			        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
			        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		        };
		System.out.println(countIslands(m1));

		int[][] m2 = {
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
						{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
						{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
					};
		System.out.println(countIslands(m2));
	}
	
	
}
