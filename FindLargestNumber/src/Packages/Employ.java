package Packages;

public class Employ {
	
	private int id ;
	private int salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Employ(int id, int salary) {
		super();
		this.id = id;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employ [id=" + id + ", salary=" + salary + "]";
	}

}
