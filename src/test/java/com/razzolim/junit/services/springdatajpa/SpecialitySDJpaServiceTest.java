package com.razzolim.junit.services.springdatajpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.razzolim.junit.model.Speciality;
import com.razzolim.junit.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {
	
	@Mock
	SpecialtyRepository specialityRepository;
	
	@InjectMocks
	SpecialitySDJpaService service;
	
	@Test
	void deleteById() {
		service.deleteById(1l);
	}
	
	@Test
	void testDelete() {
		service.delete(new Speciality());
	}

}
