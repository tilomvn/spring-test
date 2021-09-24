package com.spring.test.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = TestController.class)
class TestControllerTests {

	@Autowired
    MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void test() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk()).andReturn();
		
		TestController.ResponseModel response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), 
				TestController.ResponseModel.class);
		
		assertEquals("body", response.body);
	}
}
