package class07;

import java.util.Arrays;

/**
 * 一段时间安排最多的活动
 * @author liq
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数组，里面 是一个个具体的项目)，
 * 你来安排宣讲的日程，要求会议室进行 的宣讲的场次最多。返回这个最多的宣讲场次。
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
