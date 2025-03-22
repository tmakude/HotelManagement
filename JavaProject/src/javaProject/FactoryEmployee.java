package javaProject;

public class FactoryEmployee {
	
	public static Employee getEmployee(String empType) {
		
		if(empType.trim().equalsIgnoreCase("Java Developer")) {
			
			return new JavaDeveloper();
		}else if(empType.trim().equalsIgnoreCase("Web Developer")) {
			
			return new WebDeveloper();
		}else {
			return null;
		}
	}

}
