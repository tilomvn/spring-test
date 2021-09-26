package com.spring.test.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring.test.junit.config.TestOrder;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@TestOrder(5)
class JpaTests {

	@Autowired
	DemoRepository demoRepository;
	
	@BeforeAll
	void init() {
		demoRepository.save(new DemoEntity("name"));
	}
	
	@Test
	void test() {
		List<DemoEntity> e = demoRepository.findByName("name");
		assertTrue(e.size() > 0);
	}
}
