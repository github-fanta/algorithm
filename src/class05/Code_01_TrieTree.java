package class05;

/**
 * 前缀树
 * @author liq
 *
 */
public class Code_01_TrieTree {

	public static class TrieNode{
		int passNum;				//经过此节点的字符串个数
		int endNum;					//以此节点结尾的字符串个数
		TrieNode[] paths;   			//此节点向下的路径
		public TrieNode() {
			paths = new TrieNode[26];  //26个字母，或者用Map也可以
		}
		
	}
	
	public static class Trie{
		private TrieNode root;
		public Trie() {
			root = new TrieNode();
		}

		//插入一个单词
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chars = word.toCharArray();
			TrieNode node = root;
			int route = 0;
			for (char ch : chars) {
				route = ch - 'a';            	  //此字母要走哪条路
				if (node.paths[route] == null) {	  //之前还没有单词走过
					node.paths[route] = new TrieNode();
				}
				node = node.paths[route];    //node往下跳一个结点
				node.passNum++;          //每一个到达的结点都要+1
			}
			node.endNum++;               //循环完了之后，最后一个结点的endNum++
		}
		
		//某个单词插入过几次
		public int search(String word) {
			if(word == null) {
				return 0;
			}
			char[] chars = word.toCharArray();
			TrieNode node = root;
			int route = 0;
			for (char ch : chars) {
				route = ch - 'a';
				if (node.paths[route] == null) {
					return 0;						//还没走完就到头了，说明这个word没有插进来过
				}
				node = node.paths[route];
			}
			return node.endNum;                     //找到结束字符的结点，返回其数量
		}
		
		//删除某个word
		public void delete(String word) {
			if (search(word) < 1) {     //先找此word是否插入进来过
				return;
			}
			
			char[] chars = word.toCharArray();
			TrieNode node = root;
			int route = 0;
			for (char ch : chars) {
				route = ch - 'a';
				if(--node.paths[route].passNum == 0) {       //某个节点经过值减为0了，下面的就不必再往下了
					node.paths[route] = null;
					return;
				}
				node = node.paths[route];
			}
			node.endNum --;      //走完了，还有，那就将最后结点的endNum减一
		}
		
		//查找某个前缀出现次数
		public int prefixNum(String word) {
			if(word == null) {
				return 0;
			}
			char[] chars = word.toCharArray();
			TrieNode node = root;
			int route = 0;
			for (char ch : chars) {
				route = ch - 'a';                //此字符该走哪条路
				if(node.paths[route] == null) {  //如果这条路没有走过，说明没有这个前缀出现过
					return 0;
				}
				node = node.paths[route];        //向下
			}
			return node.passNum;
		}
		public static void main(String[] args) {
			Trie trie = new Trie();
			System.out.println("cat插入次数:" + trie.search("cat"));
			trie.insert("cat");System.out.println("插入cat");
			System.out.println("cat插入次数:"+trie.search("cat"));
			trie.delete("cat");System.out.println("删除cat");
			System.out.println("cat插入次数:"+trie.search("cat"));
			trie.insert("cat");System.out.println("插入cat");
			trie.insert("cat");System.out.println("插入cat");
			trie.delete("cat");System.out.println("删除cat");
			System.out.println("cat插入次数:"+trie.search("cat"));
			trie.delete("cat");System.out.println("删除cat");
			System.out.println("cat插入次数:"+trie.search("cat"));
			trie.insert("cata");System.out.println("插入cata");
			trie.insert("catac");System.out.println("插入catc");
			trie.insert("catab");System.out.println("插入catab");
			trie.insert("catad");System.out.println("插入catad");
			trie.delete("cata");System.out.println("删除cata");
			System.out.println("cata插入次数:"+trie.search("cata"));
			System.out.println("cat前缀数:"+trie.prefixNum("cat"));

		}
	}
}
