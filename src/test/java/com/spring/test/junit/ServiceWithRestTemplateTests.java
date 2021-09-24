package com.spring.test.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = AdditionalBean.class)
class ServiceWithRestTemplateTests {

	@Mock
    RestTemplateBuilder restTemplateBuilder;
	
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	ServiceWithRestTemplate serviceWithRestTemplate;
	
	@BeforeAll
    void init() {
        ReflectionTestUtils.setField(serviceWithRestTemplate, "restTemplate", restTemplate);
    }
	
	@Test
	void test() {
		serviceWithRestTemplate.test();
	}
}
