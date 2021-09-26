package com.spring.test.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.junit.config.TestOrder;

@RestClientTest(ServiceWithRestTemplate.class)
@TestOrder(4)
class RestTestWithVirtualRestServer {

	@Autowired
	MockRestServiceServer virtualServer;
	
	@Autowired
	ServiceWithRestTemplate serviceWithRestTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void test() throws Exception {
		virtualServer.expect(requestTo("http://example.com/"))
					.andRespond(withSuccess(objectMapper.writeValueAsString(new ServiceWithRestTemplate.ResponseModel("name")), 
							MediaType.APPLICATION_JSON));
		
		assertEquals("name", serviceWithRestTemplate.test());
	}
	
}
