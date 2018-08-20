package class05;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现并查集结构
 * @author liq
 *
 */
public class Code_04_UnionFind {

	
	public static class Node{
		//whatever what
	}
	
	public static class UnionFindSet{
		Map<Node, Node> fatherMap;	 //集合的当前节点和父结点对应关系
		Map<Node, Integer> sizeMap;  //集合的代表结点和集合大小
		public UnionFindSet(List<Node> nodes) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			for (Node node : nodes) {   //各自成集合
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}
		
		//找到本集合的代表结点。
		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (node != father) { //一直向上找
				father = findHead(father); //委托给下一层递归
			}
			fatherMap.put(node, father);
			return father;
		}
		
		//向外提供方法1/2 ：判断两个节点是否属于同一个集合
		public boolean isSameSet(Node aNode, Node bNode) {
			return findHead(aNode) == findHead(bNode);
		}
		
		//向外提供方法2/2: 两个节点所属的两个集合合并操作
		public void union(Node aNode, Node bNode) {
			if (aNode == null || bNode == null) {  //为空，没有必要合并
				return;
			}
			Node aHead = findHead(aNode);
			Node bHead = findHead(aNode);
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(aHead);
				if (aSetSize >= bSetSize) {   //把少的挂到多的集合上
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}else {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				}
			}
		}
		
		public static void main(String[] args) {
			Double d1 = 1.23;
			Double d2 = 1.2;
			BigDecimal b1 = new BigDecimal(1.23);
			BigDecimal b2 = new BigDecimal(1.2);
			System.out.println(d1-d2);
			System.out.println(b1.subtract(b2));
			
			BigDecimal bb1 = new BigDecimal("1.23");
			BigDecimal bb2 = new BigDecimal("1.2");
			System.out.println(bb1.subtract(bb2));
		}
		
		
	}

}
