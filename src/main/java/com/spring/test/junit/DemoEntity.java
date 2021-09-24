package com.spring.test.junit;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
class DemoEntity {
	
	@Id
	String id;
	
	@Column(length = 50)
	String name;
	
	DemoEntity() {}
	DemoEntity(String name) { this.name = name;	}

	@PrePersist
	void prePersist() {
		this.id = UUID.randomUUID().toString();
	}
}
