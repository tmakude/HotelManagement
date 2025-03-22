package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	@PostMapping("/calCulate/{num}")

	public ResponseEntity<String> calCulate(@PathVariable("num") int num){
		
		int result =0;
		
		if(num == 1) {
			result = result+2;
			ResponseEntity.ok("The Result is :" + result);
		}
		else if(num==2) {
			result = result-2;
			ResponseEntity.ok("The Result is :" + result);
		}else {
			
			return ResponseEntity.ok("Number 1 or 2 are required");
		}
		
		return ResponseEntity.ok("The Result is :" + result);
		
	}

}
