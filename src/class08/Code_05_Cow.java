package class08;
/**
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只
 * 母牛，假设不会死。求N年后，母牛的数量。
 * @author liq
 */
public class Code_05_Cow {

	/**
	 * 递归方式
	 * @param nYear
	 * @return
	 */
	public static int cowNumRecr(int nYear) {
		if(nYear < 1) {
			return 0;
		}
		if(nYear <= 3) {
			return 1;
		}
		return cowNumRecr(nYear - 1) + cowNumRecr(nYear - 3);
	}
	
	/**
	 * 非递归
	 * @param args
	 */
	public static int cowNumUnRecr(int nYear) {
		if(nYear < 1) {
			return 0;
		}
		if(nYear <= 3) {
			return 1;
		}
		int cur = 0;
		int pre = 1;		//去年
		int prepre = 1;		//前年
		int preprepre = 1;	//两年前
		for(int i=4; i<=nYear; i++) {   //从第四年开始
			cur = pre + preprepre;      //今年的数量等于去年的加上两年前的
			//全部前移
			preprepre = prepre;
			prepre = pre;
			pre = cur;
		}
		return cur;
	}
	public static void main(String[] args) {
		System.out.println(cowNumRecr(8));
		System.out.println(cowNumUnRecr(8));
	}
}
