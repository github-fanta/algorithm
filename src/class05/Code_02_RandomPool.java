package class05;
/**
 * O(1)实现insert(key)、delete(key)、getRandom()随机返回结构中的任何一个key
 * @author liq
 *
 */

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class Code_02_RandomPool{

	public static class Pool<E>{
		Map<E, Integer> map1;
		Map<Integer, E> map2;
		int size;
		public Pool() {
			map1 = new HashMap<>();
			map2 = new HashMap<>();
			size = 0;
		}
		
		//添加元素
		public void insert(E element) {
			if (!map1.containsKey(element)) {
				map1.put(element, size);
				map2.put(size, element);
				size++;
			}
		}
		
		//随机返回结构中的任何一个key
		public E getRandom() {
			if (size == 0) {
				return null;
			}
			return map2.get((int)  (Math.random() * size));  // 0 ~ size -1共size个
		}
		
		//删除delete()
		public void delete(E element) {
			if (map1.containsKey(element)) {
				int lastIndex = --size;        //获取最后一个序号  =size - 1; size--;
				E lastE = map2.get(lastIndex); //获取最后一个元素
				
				Integer deleteIndex = map1.get(element);//获取删除元素对应索引
				//用最后一个值覆盖此值
				map1.put(lastE, deleteIndex);
				map2.put(deleteIndex, lastE);
				
				map1.remove(lastE);
				map2.remove(lastIndex); //删除最后一个元素
			}
		}
		
		
		public static void main(String[] args) {
			Pool<String> pool = new Pool<String>();
			pool.insert("str1");
			pool.insert("str2");
			pool.insert("str3");
			pool.insert("str3");
			pool.insert("str5");
			pool.insert("str6");
			System.out.println("pool.size:"+pool.size);
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());

		}
	}
}
