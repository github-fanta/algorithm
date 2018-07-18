package class03;

/**
 * 将一个正方形的矩阵顺时针旋转90° 空间复杂度O（1）
 * @author liq
 *
 */
public class Code06RotateMatrix {
	
	public static void rotateMatrix(int[][] matrix) {
		int tRow = 0;
		int tCol = 0;
		int dRow = matrix.length - 1;
		int dCol = matrix[0].length - 1;
		
		while(tRow < dRow) {
			rotateEdge(matrix, tRow++, tCol++, dRow--, dCol--);
		}
	}

	private static void rotateEdge(int[][] m, int tRow, int tCol, int dRow, int dCol) {

		int edgeLen = dCol - tCol;
		int temp = 0;
		for(int i = 0; i < edgeLen; i++) {
			temp = m[tRow][tCol+i]; //暂存第一条边上的点
			m[tRow][tCol+i] = m[dRow - i][tCol]; //第四边到第一条边
			m[dRow - i][tCol] = m[dRow][dCol - i]; //第三条边到第四条边
			m[dRow][dCol - i] = m[tRow + i][dCol]; //第二条边到第三条边
			m[tRow + i][dCol] = temp; //第一条边到第二条边
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		rotateMatrix(matrix);
		for (int i=0; i != matrix.length; i++) {
			for (int j=0; j != matrix.length; j++) {
				System.out.print(matrix[i][j]+"    ");
			}
			System.out.println();
		}
	}
}
