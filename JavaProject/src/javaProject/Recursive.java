package javaProject;

public class Recursive {

	public static void main(String[] args) {


		PrintOneToTen(1 , 10);

	}

	private static void PrintOneToTen(int i, int j) {


		if(i < j) {
			
			System.out.println(i);
			PrintOneToTen(i+1, j);
		}else {
			System.out.println(i);
		}
		
	}

}
