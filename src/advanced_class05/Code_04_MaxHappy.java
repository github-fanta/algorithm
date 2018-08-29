package advanced_class05;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形DP 最大活跃度
 * @author liq
 *
 */
public class Code_04_MaxHappy {

	
	public static class Node{
		int huo;
		List<Node> nexts;
		public Node(int huo) {
			this.huo = huo;
			nexts = new ArrayList<>();
		}
	}
	public static int maxHappy(Node head) {
		ReturnData data = process(head);
		return Math.max(data.bu_lai_huo, data.lai_huo);
	}
	
	private static ReturnData process(Node head) {

		int lai_huo = head.huo;   //此节点来的活跃度
		int bu_lai_huo = 0;
		for(int i = 0; i < head.nexts.size(); i++) {  //让上级选择下级来或者不来
			ReturnData nextData = process( head.nexts.get(i));
			lai_huo += nextData.bu_lai_huo;  //本节点要是来了，加上下级的不来时的活跃度
			bu_lai_huo += Math.max(nextData.bu_lai_huo, nextData.lai_huo);//本节点要是来了，下级结点可以来，可以不来
		}
		return new ReturnData(lai_huo, bu_lai_huo);  //交给上级去抉择
	}

	static class ReturnData{
		int lai_huo;     //此人要是来的话,总活跃度
		int bu_lai_huo;  //此人不来的话,总活跃度
		public ReturnData(int lai_huo, int bu_lai_huo) {
			this.lai_huo = lai_huo;
			this.bu_lai_huo = bu_lai_huo;
		}
	}
}
