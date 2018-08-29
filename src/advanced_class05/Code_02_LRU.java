package advanced_class05;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现LRU结构
 * @author liq
 *
 */
public class Code_02_LRU {
	
	
	public static class Node<K, V>{
		public K key;
		public V value;
		public Node<K, V> pre;
		public Node<K, V> next;
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public static class LRULinkedList<K, V>{
		private Node<K, V> head;
		private Node<K, V> tail; 
		
		public LRULinkedList() {
		    this.head = null;
		    this.tail = null;
		}
		
		public void addNode(Node<K, V> node){
			if(node == null) return;
			if(head == null) {
				this.head = node;
				this.tail = node;
			}else {
				this.tail.next = node;
				node.pre = this.tail;
				this.tail = node;
			}
		}
		
		public void moveNodeToTail(Node<K, V> node) {
			if(this.tail == node) return;
			if(node == this.head) { //移动的是首结点,pre是null
				this.head = node.next;
				this.head.pre = null;
			}else {//移动的是其他节点
				node.next = null;
				this.tail.next = node;
				node.pre = this.tail;
				this.tail = node;
			}
		}
		
		public Node<K, V> removeHead() {
			if(this.head == null) return null;
			Node<K, V> result = this.head;
			if(this.head == this.tail) {  //就省了一个了
				this.head = null;
				this.tail = null;
			}else {
				this.head = this.head.next;
				this.head.pre = null;
				result.next = null;
			}
			return result;
		}
	}
	
	
	public static class LRUCache<K, V> {
		
		private Map<K, Node<K, V>> keyNodeMap;
		private LRULinkedList<K, V> linkedList;
		private int capcity;
		public LRUCache(int initSize) {
			this.keyNodeMap = new HashMap<>();
			this.linkedList = new LRULinkedList<>();
			this.capcity = initSize;
		}

		public void set(K key, V value) {
			if(keyNodeMap.containsKey(key)) {          //包含此key
				Node<K, V> node = keyNodeMap.get(key); //map中取出Node，修改值
				node.value = value;
				linkedList.moveNodeToTail(node);
			}else {//不包含此key
				Node<K, V> newNode = new Node<>(key, value);
				keyNodeMap.put(key, newNode);
				this.linkedList.addNode(newNode);
				if(keyNodeMap.size() == this.capcity + 1 ) {
					removeMostUnusedCache();
				}
				
			}
		}
		
		public Node<K, V> get(K key) {
			if(keyNodeMap.containsKey(key)) {
				Node<K, V> resultNode = keyNodeMap.get(key);
				linkedList.moveNodeToTail(resultNode);  //命中，调整位置到尾部
				return resultNode;
			}
			return null;
		}
		private void removeMostUnusedCache() {
			Node<K, V> head = this.linkedList.removeHead();
			keyNodeMap.remove(head.key);
		}
	}
	
	public static void main(String[] args) {
		LRUCache<String, Integer> testCache = new LRUCache<String, Integer>(3);
		testCache.set("A", 1);
		testCache.set("B", 2);
		testCache.set("C", 3);
		System.out.println(testCache.get("B").value);
		System.out.println(testCache.get("A").value);
		testCache.set("D", 4);
		System.out.println(testCache.get("D").value);
		System.out.println(testCache.get("C").value);

	}
}
