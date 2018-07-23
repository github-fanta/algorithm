package class03;

/**
 *从排好序的矩阵中找数，每一行和每一列都排好序 
 * @author liq
 *
 */
public class Code09_FindNumInSortedMatrix {

	public static boolean isContains(int[][] matrix, int num) {
		//开始位置:右上角
		int row = 0;
		int col = matrix[0].length - 1;
		while (row <= matrix.length-1 && col >= 0) {//不超过边界
			if (num == matrix[row][col]) {	//直接命中
				return true;
			}else if (num < matrix[row][col]) { //自己颜色比当前浅，向左走，因为向下更黑
				col --;
			}else {	//自己颜色比当前颜色深，向下走向黑暗的无底洞
				row ++;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
	int[][] matrix = new int[][] { 
			{ 0, 1, 2, 3, 4, 5, 6 },// 0
			{ 10, 12, 13, 15, 16, 17, 18 },// 1
			{ 23, 24, 25, 26, 27, 28, 29 },// 2
			{ 44, 45, 46, 47, 48, 49, 50 },// 3
			{ 65, 66, 67, 68, 69, 70, 71 },// 4
			{ 96, 97, 98, 99, 100, 111, 122 },// 5
			{ 166, 176, 186, 187, 190, 195, 200 },// 6
			{ 233, 243, 321, 341, 356, 370, 380 } // 7
	};
	int K = 233;
	System.out.println(isContains(matrix, K));
	}
}
