package com.razzolim.junit.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.razzolim.junit.model.Owner;

@Disabled(value = "Disable until we learn mocking...")
class OwnerSDJpaServiceTest {
	
	OwnerSDJpaService service;
	
	@BeforeEach
	void setUp() {
		service = new OwnerSDJpaService(null, null, null);
	}
	
	@Disabled
	@Test
	void findByLastName() {
		Owner foundOwner = service.findByLastName("Buck");
	}

	@Test
	void findAllByLastNameLike() {
		
	}

	@Test
	void findAll() {
		
	}

	@Test
	void findById() {
		
	}

	@Test
	void save() {
		
	}

	@Test
	void deleteById() {
		
	}
}
