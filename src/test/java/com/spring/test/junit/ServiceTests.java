package com.spring.test.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ServiceTests {

	@Mock
	DemoComponent demoComponent;
	
	@InjectMocks
	DemoService demoService;
	
	@Test
	void test() {
		when(demoComponent.getString()).thenReturn("test");
		assertEquals("test", demoService.test());
		verify(demoComponent, times(1)).getString();
	}
}
