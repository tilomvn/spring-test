package com.spring.test.junit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class TestController {

	ServiceWithRestTemplate serviceWithRestTemplate;

	TestController(ServiceWithRestTemplate serviceWithRestTemplate) {
		this.serviceWithRestTemplate = serviceWithRestTemplate;
	}

	@GetMapping("/test")
	ResponseEntity<?> test() {
		return ResponseEntity.ok().body(new ResponseModel("body"));
	}

	@GetMapping("/test1")
	ResponseEntity<?> test1() {
		return ResponseEntity.ok().body(new ResponseModel(serviceWithRestTemplate.test()));
	}
	
	static class ResponseModel {
		String body;
	
		ResponseModel() {}
		ResponseModel(String body) { this.body = body; }
		
		public String getBody() { return body; }
	}
}
