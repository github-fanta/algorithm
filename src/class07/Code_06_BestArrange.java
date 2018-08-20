package class07;

import java.util.Arrays;

/**
 * 一段时间安排最多的活动
 * @author liq
 *
 */
public class Code_06_BestArrange {
	
	//活动
	public static class Program{
		int startTime;
		int endTime;
		public Program(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
	
	public static int bestArrange(Program[] programs, int startTime) {
		
		Arrays.sort(programs, (x,y) -> x.endTime-y.endTime); //以结束时间排序为策略
		int count = 0;
		for (Program program : programs) {
			if(program.startTime >= startTime) {
				startTime = program.endTime;
				count ++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Program[] programs = new Program[] {
				new Program(8, 9),
				new Program(7, 10),
				new Program(9, 11),
				new Program(13, 20),
				new Program(16, 23)
		};
		System.out.println("最多安排活动场次："+bestArrange(programs, 6));
	}
}
