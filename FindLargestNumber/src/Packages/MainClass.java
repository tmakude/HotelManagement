package Packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {

		JavaDeveloper javaDeveloper = new JavaDeveloper();
		javaDeveloper.demo();

		Developer developer = new Developer();
		developer.demo();
		
		
		
		Developer developer1 = new JavaDeveloper();
		developer1.demo();
		
		

	}

}
