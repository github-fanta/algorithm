package class08;
/**
 * 汉诺塔
 * @author liq
 *
 */
public class Code_02_Hanoi {

	public static void hanoi(int N, String from, String to, String help) {
		if(N == 1) {
			System.out.println("1：从"+from+"移到"+to);//解决问题思路和打印刚好是相反的，解决问题时候1,是指最底下一个，打印出来刚好是顶上一个
			return;
		}
		hanoi(N-1, from, help, to); //要把最后一个挪到to上去，先把上面n-1个从from 挪到 help，可以借助to
		System.out.println(N+"：从"+from+"移到"+to);
		hanoi(N-1, help, to, from);//把这n-1个开始从help挪到to上去，可以借助from
	}
	
	public static void main(String[] args) {
		hanoi(3,"左","右","中");
	}
	
}
