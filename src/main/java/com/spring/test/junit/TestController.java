package com.spring.test.junit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class TestController {

	@GetMapping("/test")
	ResponseEntity<?> test() {
		return ResponseEntity.ok().body(new ResponseModel("body"));
	}
	
	static class ResponseModel {
		String body;
	
		ResponseModel() {}
		ResponseModel(String body) { this.body = body; }
		
		public String getBody() { return body; }
	}
}
