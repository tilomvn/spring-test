package com.spring.test.junit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//Upgrade to SpringBoot v2.5.5 will face missing library ???
interface DemoRepository extends JpaRepository<DemoEntity, String> {

	List<DemoEntity> findByName(String name);
}
