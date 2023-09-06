package com.spring.test.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.junit.config.TestOrder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = TestController.class)
@TestOrder(1)
class TestControllerTests {

	@MockBean
	ServiceWithRestTemplate serviceWithRestTemplate;

	@Autowired
    MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void test() throws Exception {
		var mvcResult = mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk()).andReturn();
		
		var response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				TestController.ResponseModel.class);
		
		assertEquals("body", response.body);
	}

	@Test
	void test1() throws Exception {
		var mvcResult = mockMvc.perform(get("/api/test1"))
				.andExpect(status().isOk()).andReturn();

		var response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				TestController.ResponseModel.class);

		assertEquals(null, response.body);
	}

	@Test
	void testMultipartPutForm() throws Exception {
		var requestBody = "{\"code\": \"code\"}";
		var token = "Bearer asldahskjdha.sdakjhdkjahsd";
		var builder = MockMvcRequestBuilders.multipart("/v1/stores/");
		builder.with(request -> { request.setMethod(HttpMethod.PUT.name()); return request; });

		var result = mockMvc.perform(builder
						.file(new MockMultipartFile("logo", null, null,
								new ClassPathResource("logo.png").getInputStream().readAllBytes()))
						.file(new MockMultipartFile("store", null, MediaType.APPLICATION_JSON_VALUE, requestBody.getBytes()))
						.header(HttpHeaders.AUTHORIZATION, token))
				.andExpect(status().isOk()).andReturn();
	}
}
