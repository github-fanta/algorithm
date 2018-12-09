package class08;
/**
 * 打印一个字符串的全部子序列(子串是必须连在一起的，子序列不要求连在一起。 但都要求相对先后顺序)，包括空字符串
 * @author liq
 *
 */
public class Code_03_Print_All_Subsquences {

	public static void printAllSubsquence(char[] str, int i, String curStr){
		if(i == str.length) {  //i表示向前推进到最后就停止
			System.out.println(curStr);
			return;
		}
		printAllSubsquence(str, i+1, curStr+"   ");    //沿途收集信息
		printAllSubsquence(str, i+1, curStr+str[i]);
	}
	
	
	public static void main(String[] args) {
		printAllSubsquence("你好啊".toCharArray(), 0, "");
	}
}
