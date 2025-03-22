package javaProject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.rowset.serial.SQLOutputImpl;

public class CountDuplicateString {

	public static void main(String[] args) {
		
		String str = "geeksforgeeks";
		printDiplicates(str);
		
	}
	
	public static void printDiplicates(String str) {
		
		String result = "";
		
		Map<Character , Integer> count = new LinkedHashMap<>();
		
		for(char c : str.toCharArray()) {
			
			count.put(c, count.getOrDefault(c, 0)+1);
		}
		
		
		for(Map.Entry<Character , Integer> entry : count.entrySet()) {
			
			result +=entry.getKey()+""+entry.getValue();
			
		}
		
		System.out.println(result);
	}
	

}
