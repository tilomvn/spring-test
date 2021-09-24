package com.spring.test.junit;

import org.springframework.stereotype.Service;

@Service
class DemoService {

	final DemoComponent demoComponent;
	
	DemoService(DemoComponent demoComponent) {
		this.demoComponent = demoComponent;
	}
	
	String test() {
		return demoComponent.getString();
	}
}
