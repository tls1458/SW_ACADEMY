
public class MyClass {

	public static void main(String[] args) {
		/* 예제 01
		int ran = (int) (Math.random()*100);
		
		System.out.println(ran);
		
		if(ran%2 == 0 ) { //랜덤하게 생성된 숫자가 짝라면,
			System.out.println("even");
		}
		else {
			System.out.println("odd");
		}
		*/
		
		/* 예제 2 */
		// 로또 번호를 생성하여 출력
		// 로또번호를 만들어서 맅ㄴ하는 함수를 정의하고 호출
		int count;	 // 1. 메모리 공간을 할당 받아서 count라는 이름을 참조하게 한다.
		count = 500;  // 2. 메모리 공간에 500
		
		// 변수 100개 정도 필요한다면?
		// int [] list = new int[100]; // int 배열 타입의 list
		// 배열 : 동일한 타입의 변수 여럿을 하나의 이름으로 참조하게 한다.
		// 선언, 생성, 할당
		// int[10] list; // 잘못 선언했기에, 선언할 때 갯수를 지정하면 안된다.
		int [] list;
		list = new int[10]; // 생성할 때 배열 갯수를 넣어야 한다.
		list[0] = 500;
		list[10] = 1000;
		
		
		
	}

}

// 배열 : 같은 타입의 데이터들을 메모리상의 연속적으로 할당시킨 집합이라고 생각합니다.