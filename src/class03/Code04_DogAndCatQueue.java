package class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列
 * @author liq
 *
 */
public class Code04_DogAndCatQueue {

	public static class Pet{
		private String type;
		public Pet(String type) {
			this.type = type;
		}
		public String getPetType() {
			return this.type;
		}
	}
	
	public static class Dog extends Pet{
		public Dog() {
			super("dog");
		}
	}
	public static class Cat extends Pet{
		public Cat() {
			super("cat");
		}
		
	}
	
	//对狗或猫实例进行封装
	public static class InPet{
		Pet pet;
		int NO;
		public InPet(Pet pet, int count) {
			this.pet = pet;
			this.NO = count;
		}
		public Pet getPet() {
			return pet;
		}
	}
	
	public static class DogAndCatQueue{
		
		private Queue<InPet> DogQ;
		private Queue<InPet> CatQ;
		int NO = 0; //进队排序号码
		
		public DogAndCatQueue() {
			this.DogQ = new LinkedList<InPet>();
			this.CatQ = new LinkedList<InPet>();
		}
		
		//Pet添加到队列
		public void add(Pet pet) {
			if (pet == null) {
				return;
			}
			if (pet.getPetType().equals("dog")) {
				DogQ.add(new InPet(pet, NO++));
			}else if(pet.getPetType().equals("cat")) {
				CatQ.add(new InPet(pet, NO++));
			}
		}
		
		//按照顺序依次弹出
		public Pet pollAll() {
			if (this.isEmpty()) {
				throw new RuntimeException("队列为空");
			}
			if (!DogQ.isEmpty() && !CatQ.isEmpty()) {
				return DogQ.peek().NO < CatQ.peek().NO ? DogQ.poll().getPet() : CatQ.poll().getPet();
			}else if(!DogQ.isEmpty()) {
				return DogQ.poll().getPet();
			}else {
				return CatQ.poll().getPet();
			}
		}
		
		//将Dog类实例按照进队顺序依次弹出
		public Pet pollDog() {
			if (DogQ.isEmpty()) {
				throw new RuntimeException("队列为空");
			}
			System.out.println("pollDog NO:"+DogQ.peek().NO);
			return DogQ.poll().getPet();
		}
		
		//将Cat类实例按照进队顺序依次弹出
		public Pet pollCat() {
			if (CatQ.isEmpty()) {
				throw new RuntimeException("队列为空");
			}
			System.out.println("pollCat NO:"+CatQ.peek().NO);
			return CatQ.poll().getPet();
		}
		
		//检查队列是否为空
		public boolean isEmpty() {
			return isDogEmpty() && isCatEmpty();
		}
		
		//检查Dog实例
		public boolean isDogEmpty() {
			return DogQ.isEmpty() ? true : false;
		}
		
		//检查Cat实例
		public boolean isCatEmpty() {
			return CatQ.isEmpty() ? true : false;
		}
		
	}
	
	public static void main(String[] args) {
		
		DogAndCatQueue test = new DogAndCatQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		while (!test.isDogEmpty()) {
			System.out.println(test.pollDog().getPetType());
		}
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getPetType());
		}
	}
	
}
