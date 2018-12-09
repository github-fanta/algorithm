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
		
		
		/*
		 * while(i2 < str1.length){
		 *     if(str1[i1] == str2[i2]){  //匹配
		 *         i1++; i2++;
		 *     }else{					  //不匹配
		 *         i2 = next[i2];
		 *         if(i2 == -1){          //跳无可跳了
		 *             i1++; i2++;
		 *         }
		 *     }
		 *     
		 * }
		 */
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
			if(str2[i - 1] == str2[cn]) { //当前位置和跳到的位置的值相同
				next[i++] = ++cn;
			}else if(cn > 0) {   //不等，能往前跳就往前跳
				cn = next[cn];
			}else {
				next[i++] = 0;   //跳无可跳了，说明当前位置前后缀最长是0
			}
		}
		
		/**
		 * if(pattern[i] == pattern[len]){
		 *     len ++;
		 *     prefix[i] = len;
		 *     i++;
		 * }
		 * else{
		 *     if(len > 0){
		 *         len = prefix[len - 1];
		 *     }
		 *     else{
		 *         prefix[i] = len;
		 *         i++;
		 *     }
		 * }
		 */
		return next;
	}
	
	public static void main(String[] args) {
		int result = getIndexOf("abcdefg", "g");
		System.out.println(result == -1 ? "不存在" : result);
	/*	int[] result = getNextArr("abcabcd".toCharArray());
		for (int i : result) {
			System.out.print(i+" ");
		}*/
		
		int idx = getInxOf("abcdefg", "g");
		System.out.println(idx == -1 ? "不存在" : idx);
	}
	
	
	public static int getInxOf(String text, String pattern) {
		if(text == null || pattern == null || pattern.length() < 1 || text.length() < pattern.length()) {
			return -1;
		}
		char[] textCh = text.toCharArray();
		char[] patCh = pattern.toCharArray();
		int textIdx = 0;
		int patIdx = 0;
		int[] next = getNextArr(patCh);
		while(textIdx < textCh.length && patIdx < patCh.length) {
			if(textCh[textIdx] == patCh[patIdx]) {
				textIdx ++; patIdx ++;
			}else {
				if(patIdx == 0) {  //跳到头了 pattern的第一个字符
					textIdx ++;   //每次textIdx不动，从pattern里面找到匹配的字符，所以pattern一直在往前加速中移动
				}else {   //往前跳
					patIdx = next[patIdx];
				}
			}
		}
		return patIdx == pattern.length() ? textIdx - patIdx : -1;
	}
	//自己练习 求next数组
	public static int[] getNextArr(char[] chars) {
		if(chars.length == 1) {
			return new int[] {-1};
		}
		int[] next = new int[chars.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while(i < chars.length) {
			if(chars[i-1] == chars[cn]) {  //相等，续上了
				next[i++] = ++cn;
			}else {   //不相等，向前跳
				if(cn > 0) cn = next[cn];
				else next[i++] = 0;
			}
		}
		return next;
	}
	
	
}
