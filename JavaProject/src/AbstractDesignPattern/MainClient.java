package AbstractDesignPattern;

public class MainClient {

	public static void main(String[] args) {


		Employee employe = EmployeeFactory.getEmploe(new JavaDeveloperFactory());

	}

}
