package advanced_class01;

public class Code_01_KMP {
	public static int getIndexOf(String s, String m) {
		if(s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int i1 = 0;
		int i2 = 0;
		int[] next = getNextArray(str2);
		while(i1 < str1.length && i2 < str2.length) {
			if(str1[i1] == str2[i2]) {
				i1 ++;
				i2 ++;
			}else if(next[i2] == -1) {  //已经来到str2的0位置了，不能再往前跳了。说明第一个字符都没匹配，
				i1 ++;
			}else {  //没有跳到0位置，那就根据next数组
				i2 = next[i2];
			}
		}
		return i2 == str2.length ? i1 - i2 : -1;  //i2划过了所有的str2，匹配成功。
	}
	
	public static int[] getNextArray(char[] str2) {
		if(str2.length == 1) {
			return new int[] {-1};
		}
		int[] next = new int[str2.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while(i < next.length) {
			if(str2[i - 1] == str2[cn]) { //当前位置和跳到的位置相同
				next[i++] = ++cn;
			}else if(cn > 0) {   //不等，能往前跳就往前跳
				cn = next[cn];
			}else {
				next[i++] = 0;   //跳无可跳了，说明当前位置前后缀最长是0
			}
		}
		return next;
	}
	
	public static void main(String[] args) {
		int result = getIndexOf("abcdefg", "g");
		System.out.println(result == -1 ? "不存在" : result);
	}
}
