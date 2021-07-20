import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ex04Solution {
	public static void main(String[] args) {
		/*
		* 1부터 999까지의 100개의 임의의 수로 이루어진 배열을 생성하는 함수를
		* 이용하여 배열을 생성한다.
		- 2.인덱스가 홀수인 수들을 출력하는 함수
		- 3.배열의 수들의 합을 반환하는 함수
		- 4.가장 작은 수의 위치(인덱스)를 반환하는 함수
		- 5.3의 배수만을 새로운 배열에 복사하여 리턴하는 함수
		
		추가
		- 배열의 수들을 한 줄에 10개씩 출력하는 함수, 이때 각 수들은 줄이 맞춰져야 함
		*/
		
		// 1번
		int[] list = makeList();
		
		// 2번 
		makeOdd(list);
		
		// 3번 
		int sum = makeSum(list); // 3번 
		System.out.println("배열의 수들의 합 : " + sum);
		
		// 4번
		int min_value_index = find_min_index(list); // 첫번째 방법 
		System.out.printf("%d번째 위치 합니다.", min_value_index);
		
		// 5번
		ArrayList three_list = makeThree(list);
		for(int i=0; i<three_list.size(); i++) {
			System.out.printf("three_list[%d] : %d\n", i, three_list.get(i));
		}
		
		// 추가 
		ten_print(list);
		
	}


	// 추가) 배열의 수들을 한 줄에 10개씩 출력하는 함수, 이때 각 수들은 줄이 맞춰져야 함
	private static void ten_print(int[] list) {
		for(int i=0; i<list.length; i++) {
			System.out.printf("%d ", list[i]);
			if(i%10==9) System.out.println();
		}
		
	}


	// 5) 3의 배수만을 새로운 배열에 복사하여 리턴하는 함수
	private static ArrayList makeThree(int[] list) {
		ArrayList arr = new ArrayList();
		for(int i=0; i<list.length; i++) {
			if(list[i]%3==0) {
				arr.add(list[i]);
			}
		}
		return arr;
	}

	// 4) 가장 작은 수의 위치(인덱스)를 반환하는 함수
	private static int find_min_index(int[] list) {
		int index=0;
		int min_value=list[0];
		
		for(int i=0; i<list.length; i++) {
			if(min_value>list[i]) {
				min_value = list[i];
				index = i;
			}
		}
		
		return index;
	}

	// 3) 배열의 수들의 합을 반환하는 함수
	private static int makeSum(int []arr) {
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	// 2) 인덱스가 홀수인 수들을 출력하는 함수
	private static void makeOdd(int[] list) {
		System.out.println("인덱스가 홀수인 수들 출력!!!!!!!");
		for(int i=0; i<list.length; i++) {
			if(i%2==1) System.out.printf("list[%d] : %d\n", i, list[i]);
		}
	}
	
	// 1) 1부터 999까지의 100개의 임의의 수로 이루어진 배열을 생성하는 함수
	private static int[] makeList() {
		int [] result = new int[100];
		for(int i=0; i<result.length; i++) {
			int temp = (int)(Math.random()*999);
			result[i] = temp;
		}
		// TODO Auto-generated method stub
		return result;
	}
}
