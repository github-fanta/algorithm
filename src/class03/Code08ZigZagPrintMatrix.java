package class03;
/**
 * 之字形打印矩阵
 * @author liq
 *
 */
public class Code08ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		//上面的点坐标
		int tRow = 0;
		int tCol = 0;
		//下面的点坐标
		int dRow = 0;
		int dCol = 0;
		//结束位置的点（右下角的点）坐标
		int endRow = matrix.length - 1;
		int endCol = matrix[0].length- 1;
		boolean fromUp = true; //默认开始打印方向从右上到左下
		//下面/上面的点还没到终点
		while(dCol <= endCol) {
			printLine(matrix, tRow, tCol, dRow, dCol, fromUp);
			if (tCol == endCol) {//上面点碰到右边界， 就往下走
				tRow++;
			}else{
				tCol++; 
			}
			if (dRow == endRow) { //下面点碰到下边界， 就往右走
				dCol++;
			}else {
				dRow++;
			}
			fromUp = !fromUp;
		}
	}

	private static void printLine(int[][] m, int tRow, int tCol, int dRow, int dCol, boolean fromUp) {
		if (fromUp) {
			//从右上到左下打印
			while(tCol >= dCol) {
				System.out.print(m[tRow++][tCol--]+" ");
			}
		}else {
			//从左下到右上打印
			while(dCol <= tCol) {
				System.out.print(m[dRow--][dCol++]+" ");
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}
}
