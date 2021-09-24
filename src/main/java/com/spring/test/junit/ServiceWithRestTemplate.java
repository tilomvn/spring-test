package com.spring.test.junit;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class ServiceWithRestTemplate {

	Logger log = LoggerFactory.getLogger(getClass());
	
	final RestTemplate restTemplate;
	
	/**
	 * Use RestTemplateBuilder for adding some operations such as BasicAuthentication, Exception Handler,...
	 */
	ServiceWithRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	void test() {
		ResponseEntity<ResponseModel> response = restTemplate.getForEntity("http://example.com/", ResponseModel.class);
		//log.info("" + response.getStatusCode());
	}
	
	class ResponseModel {
		
	}
}
