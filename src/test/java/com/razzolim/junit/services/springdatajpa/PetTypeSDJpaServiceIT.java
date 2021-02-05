package com.razzolim.junit.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.razzolim.junit.junitextensions.TimingExtension;

/**
 * Naming a class with IT means to Maven that it is an Integration Test
 * 
 * @author Renan
 *
 */
@ExtendWith(TimingExtension.class)
class PetTypeSDJpaServiceIT {
	
	@BeforeEach
	void setup() {
	}
	
	@Test
	void findById() {
		
	}
	
	@Test
	void findAll() {
		
	}
	
	@Test
	void save() {
		
	}
	
	@Test
	void delete() {
		
	}
	
	@Test
	void deleteById() {
		
	}

}
