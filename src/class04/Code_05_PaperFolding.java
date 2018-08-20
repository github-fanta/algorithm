package class04;
/**
 * 打印折痕
 * @author liq
 *
 */
public class Code_05_PaperFolding {
	
	public static void printAllFolds(int Times) {
		printProcess(1, Times, true);
	}
	private static void printProcess(int curFold, int TIMES, boolean isDown) {
		if (curFold > TIMES) return;
		printProcess(curFold+1, TIMES, true);
		System.out.println(isDown ? "down" : "up");
		printProcess(curFold+1, TIMES, false);
	}
	
	public static void main(String[] args) {
		int N = 10;
		printAllFolds(N);
	}
}
