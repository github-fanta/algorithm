package class08;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印一个字符串的全部排列
 * 若需要字典序排序 可使用 Collections.sort(List);后再输出
 * @author liq
 *
 */
public class Code_04_Print_All_Permutations {

	public static void process(char[] str, int i) {
		if(i == str.length) {
			System.out.println(String.valueOf(str));
			return;
		}
		Set<Character> set = new HashSet<>();
		for(int j = i; j < str.length; j++) {
			if(!set.contains(str[j])) {
				//set.add(str[j]);  	     //a bb (b ab  -> b ba) -> a bb(-> b ba 所以省掉)
				swap(str, i, j);   			 //每步推进中实质做的就是循环把当前字符和后面字符交换，j位置上可以出现的所有字符的组合
				process(str, i+1);
				swap(str, i, j);			//一次交换就可以生成一个不一样的序列，不需要保存此次交换序列去影响下一次交换后的序列
			}
		}
	}

	private static void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	public static void main(String[] args) {
		process("abb".toCharArray(), 0);
	}
}
