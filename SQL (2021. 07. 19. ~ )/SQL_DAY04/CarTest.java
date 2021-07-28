
public class CarTest {

	public static void main(String[] args) {
		Car hudson = new Car();
		hudson.setName("Doc Hudson");
		hudson.setSpeed(300);
		System.out.println("hudson 객체 정보 : " + "이름[" + hudson.name + "], "+ "스피드[" + hudson.getSpeed() +"]");
		
		Car carrera = new Car();
		carrera.setName("Saffy Carrera");
		carrera.setSpeed(200);
		System.out.println("carrera 객체 정보 : " + "이름[" + carrera.name + "], " + "스피드[" + carrera.getSpeed() + "]");
		
		
		Car mater = new Car();
		mater.setName("Mater");
		mater.setSpeed(80);
		System.out.println("mater 객체 정보 : " + "이름[" + mater.name + "], " + "스피드[" + mater.getSpeed() + "]");
		
		Car mcqueen = new Car();
		mcqueen.setName("Lightning Mcqueen");
		mcqueen.setSpeed(300);
		System.out.println("mcqueen 객체 정보 : " + "이름[" + mcqueen.name + "], " + "스피드[" + mcqueen.getSpeed() + "]");
	}

}


class Car {
	String name;
	int speed;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	
}