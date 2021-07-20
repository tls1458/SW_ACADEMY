import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JAVA_DAY03 {

	public static void main(String[] args) {
		// 과제  : 2x + y = 10 일 때, x와 y의 해를 구하시오.
		/*
		System.out.println("2x + y = 10의 해를 구합니다. ");
		for(int x=0; x<=10; x++) {
			for(int y=0; y<=10; y++) {
				if(2*x+y == 10) {
					System.out.printf("2x * y = 10의 해, x=%d, y=%d입니다. \n", x, y);
				}
			}
		}
		*/
		
		/*
		// 파일 입출력
		try {
			// 파일 객체 생성
			File file = new File("/Users/ssj/eclipse-workspace/SQL_DAY1/src/list.txt");
			// 입력 스트림 생
			FileReader fr = new FileReader(file);
			int ch = 0;
			while((ch = fr.read()) != -1) {
				System.out.print((char)ch);
			}
			fr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		/* class : 변수들과 함수들의 집합 */
		AAA my = new AAA(10); // Stack영역의 생성된 객체의 주소를  저장(메모리 저장) , 객체
				 					// new MyClass() 는 Heap영역(자유롭게 선언 해지가능, 그러나 메모리 어디에 저장되어있는지 알 수 없음) 에 저장 
									// new MyClass() : 생성자를 호출한 것이며, 힙에 객체가 생성된다.
									// = 이라는 할당 연산자로 인해 힙에 생성 객체의 주소가 my변수에 저장된다.
		
		int count = 500; 			// count는 Stack영역에 할당 함 
		my.count = 1000; 			// 힙 영역에서 생성된 객체 내부에 할당된 메모리 공간에 주소를 저장하고 100의 값을 저자
	}
}

class AAA{
	int count; // 인스턴스 변수 (=객체변수 ) 객체화하여 참조
	static String name; // static(클래스 변수)
	
	AAA() {
		System.out.println("MyClass()");
	}
	
	AAA(int count) {
		this.count = count;
		System.out.println("MyClass(COUNT)");
	}
	void print() {
		int amount; // 지역 변수 (= 로컬 변수)
	}
	
	void write() {
		count = 500;
		// amount = 1000;  <= 서로 다른 함수라서 참조불
	}
}


