package advanced_class06;
/*	给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和
	左右括号，返回公式的计算结果。
	【举例】
	str="48*((70-65)-43)+8*1"，返回-1816。
	str="3+1*4"，返回7。 str="3+(1*4)"，返回7。
*/

import java.util.LinkedList;

public class Code_07_ExpressionCompute {

	public static int getValue(String str) {
		return compute(str.toCharArray(), 0)[0];
	}

	/**
	 * 返回值为数组，长度一定为2
	 * arr[0] 代表计算结果
	 * arr[1] 代表计算到的位置
	 */
	private static int[] compute(char[] str, int i) {
		LinkedList<String> que = new LinkedList<String>();
		int preNum = 0;
		int[] bracketResult = null;
		while( i < str.length && str[i] != ')' ) {
			if(str[i] >= '0' && str[i] <= '9') {
				preNum = preNum*10 + str[i++] - '0';
			}else if(str[i] != '(') { //不是数字，不是左括号，那肯定就是+-*/了
				addNum(que, preNum);//在压入此次运算符之前，会先把上一次的乘除计算了。
				preNum = 0;
				que.addLast(String.valueOf(str[i++]));//把此次运算符压入栈中
			}else {
				bracketResult = compute(str, i+1);
				preNum = bracketResult[0];
				i = bracketResult[1] + 1;
			}
		}
		addNum(que, preNum);//i推进到头了，或者遇到右括号了，本层括号算完了，把结果再和括号前的元素+-（加减先不管）*/（只计算乘除）如3+3*（6+7*（3+1））
		return new int[] {getResult(que), i };//本层括号计算完了，计算本括号内的栈元素的+-计算结果,和 右括号的位置
	}

	private static void addNum(LinkedList<String> que, int num) {
		
		if(!que.isEmpty()) {
			String top = que.pollLast(); //拿出栈顶上一个元素（肯定是+-*/）
			if(top.equals("+") || top.equals("-")) {
				que.addLast(top); //加减先不管
			}else {
				int preNum = Integer.valueOf(que.pollLast()); //拿出之前的值
				num = top.equals("*") ? (num * preNum) : (preNum / num);//乘除先运算
			}
		}
		que.addLast(String.valueOf(num)); //运算完乘除后，又压入栈中
	}
	
	private static int getResult(LinkedList<String> que) {

		int result = 0;
		boolean add = true;  //默认加号"+"
		String temp = null;
		while(!que.isEmpty()) {
			temp = que.pollFirst();
			if(temp.equals("+")) {
				add = true;
			} else if (temp.equals("-")){
				add = false;
			} else {  //弹出的是数字
				result += add ? Integer.valueOf(temp) : -Integer.valueOf(temp);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String exp = "48*((70-65)-43)+8*1";
		System.out.println(getValue(exp));

		exp = "4*(6+78)+53-9/2+45*8";
		System.out.println(getValue(exp));

		exp = "10-5*3";
		System.out.println(getValue(exp));

		exp = "-3*4";
		System.out.println(getValue(exp));

		exp = "3+1*4";
		System.out.println(getValue(exp));

	}

}
