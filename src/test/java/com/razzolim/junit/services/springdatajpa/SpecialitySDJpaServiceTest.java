package com.razzolim.junit.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
	void findByIdTest() {
		Speciality speciality = new Speciality();
		
		when(specialityRepository.findById(1l)).thenReturn(Optional.of(speciality));
		
		Speciality foundSpeciality = service.findById(1l);
		
		assertThat(foundSpeciality).isNotNull();
		
		verify(specialityRepository).findById(1l);
	}
	
	@Test
	void deleteById() {
		service.deleteById(1l);
		service.deleteById(1l);
		
		verify(specialityRepository, times(2)).deleteById(1l);
	}
	
	@Test
	void deleteByIdAtLeast() {
		service.deleteById(1l);
		service.deleteById(1l);
		
		verify(specialityRepository, atLeastOnce()).deleteById(1l);
	}
	
	@Test
	void deleteByIdAtMost() {
		service.deleteById(1l);
		service.deleteById(1l);
		
		verify(specialityRepository, atMost(5)).deleteById(1l);
	}
	
	@Test
	void deleteByIdNever() {
		service.deleteById(1l);
		service.deleteById(1l);
		
		verify(specialityRepository, atLeastOnce()).deleteById(1l);
		
		verify(specialityRepository, never()).deleteById(5l);
	}
	
	@Test
	void testDelete() {
		service.delete(new Speciality());
	}

}
