package javaProject;

import java.util.Scanner;

public class DeveloperClient {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Developer");
		Employee emp = FactoryEmployee.getEmployee(sc.nextLine());
		
		System.out.println(emp);
		
		System.out.println(emp.salary());

	}

}
