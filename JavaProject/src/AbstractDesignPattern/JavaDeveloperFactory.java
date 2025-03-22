package AbstractDesignPattern;

public class JavaDeveloperFactory extends EmployeAbstractFactory {

	public Employee getEmployee() {
		
		return new JavaDeveloper();
	}

}
