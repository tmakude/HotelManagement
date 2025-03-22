package AbstractDesignPattern;

public class EmployeeFactory {
	
	public static Employee getEmploe(EmployeAbstractFactory em) {
		
		return em.getEmployee();
	}
	

}
