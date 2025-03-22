package AbstractDesignPattern;

public class JavaDeveloper implements Employee {

	@Override
	public int salary() {

		return 60000;
	}

	@Override
	public String name() {

		System.out.println("I am ");

		return "Java Developer";
	}

}
