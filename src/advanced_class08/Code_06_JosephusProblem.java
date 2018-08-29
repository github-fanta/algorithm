package advanced_class08;

public class Code_06_JosephusProblem {

	public static int getLive(int i, int m) {
		if(i == 1) {
			return 1; //存活的位置
		}
		return (getLive(i - 1, m) + m - 1) % i + 1; //上一次杀人的位置 getLive(i - 1, m) - 1 加m表示本次杀人的位置，+1表示存活的位置
	}

	public static void main(String[] args) {
		
		
	}
}
