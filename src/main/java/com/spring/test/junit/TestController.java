package com.spring.test.junit;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(method = { RequestMethod.PUT },
					path = "/v1/stores",
					consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	ResponseEntity<?> create(@RequestPart("obj") TestRequestPart obj,
							 @RequestPart(value = "file", required = false) MultipartFile file) {
		return ResponseEntity.ok().body(obj.getBody() + "-" + file.getName());
	}
}
