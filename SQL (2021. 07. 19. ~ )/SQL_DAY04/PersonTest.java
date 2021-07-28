
public class PersonTest {

	public static void main(String[] args) {
		Person person1 = new Person();
		person1.selfIntroduce();
		System.out.printf("현재 인구수 : %d명\n",person1.getPopulation());
		
		Person person2 = new Person(3, "철수");
		person2.selfIntroduce();
		System.out.printf("현재 인구수 : %d명\n",person1.getPopulation());
	}

}

class Person {
	static int numberOfPersons;	
	int age;
	String name;
	
	public Person() {
		name = "Annoymous";
		age = 12;
		numberOfPersons++;
	}
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
		numberOfPersons++;
	}
	
	void selfIntroduce() {
		System.out.printf("내 이름은 %s 이며, 나이는 %d살 입니다.\n", name, age);
	}
	
	int getPopulation() {
		return numberOfPersons;
	}
	
}