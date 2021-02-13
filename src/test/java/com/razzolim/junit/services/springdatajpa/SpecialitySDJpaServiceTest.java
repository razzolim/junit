package com.razzolim.junit.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

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
	SpecialtyRepository repository;
	
	@InjectMocks
	SpecialitySDJpaService service;
	
	@Test
	void testDeleteByObject() {
		
		// given
		Speciality speciality = new Speciality();
		
		// when
		service.delete(speciality);
		
		// then
		then(repository).should().delete(any(Speciality.class));
//		verify(repository).delete(any(Speciality.class));
	}
	
	@Test
	void findByIdTest() {
		
		// given
		Speciality speciality = new Speciality();
		given(repository.findById(1l)).willReturn(Optional.of(speciality));
		
		// when
		Speciality foundSpeciality = service.findById(1l);
		
		//then
		assertThat(foundSpeciality).isNotNull();
		then(repository).should(times(1)).findById(anyLong());
		then(repository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void deleteById() {
		// given - none
		
		// when
		service.deleteById(1l);
		service.deleteById(1l);
		
		// then
		then(repository).should(times(2)).deleteById(1l);
//		verify(repository, times(2)).deleteById(1l);
	}
	
	@Test
	void deleteByIdAtLeast() {
		// given - none
		
		// when
		service.deleteById(1l);
		service.deleteById(1l);
		
		// then
		then(repository).should(atLeastOnce()).deleteById(1l);
//		verify(repository, atLeastOnce()).deleteById(1l);
	}
	
	@Test
	void deleteByIdAtMost() {
		
		// when
		service.deleteById(1l);
		service.deleteById(1l);
		
		// then
		then(repository).should(atMost(5)).deleteById(1l);
//		verify(repository, atMost(5)).deleteById(1l);
	}
	
	@Test
	void deleteByIdNever() {
		
		// when
		service.deleteById(1l);
		service.deleteById(1l);
		
		// then
		then(repository).should(atLeastOnce()).deleteById(1l);
		then(repository).should(never()).deleteById(5l);
		
//		verify(repository, atLeastOnce()).deleteById(1l);
//		verify(repository, never()).deleteById(5l);
	}
	
	@Test
	void testDelete() {
		
		// when
		service.delete(new Speciality());
		
		// then
		then(repository).should().delete(any());
	}
	
	@Test
	void testDoThrow() {
		doThrow(new RuntimeException("boom")).when(repository).delete(any());
		
		assertThrows(RuntimeException.class, () -> repository.delete(new Speciality()));
		
		verify(repository).delete(any());
	}
	
	@Test
	void testFindByIdThrows() {
		
		given(repository.findById(1l)).willThrow(new RuntimeException("Boom"));
		
		assertThrows(RuntimeException.class, () -> service.findById(1l));
		
		then(repository).should().findById(1l);
	}
	
	@Test
	void testDeleteBDD() {
		
		willThrow(new RuntimeException("boom")).given(repository).delete(any());
		
		assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));
		
		then(repository).should().delete(any());
	}
	
	/*
	 * Removed as it was refactored. 
	 *
	@Test
	void findByIdTest() {
		Speciality speciality = new Speciality();
		
		when(repository.findById(1l)).thenReturn(Optional.of(speciality));
		
		Speciality foundSpeciality = service.findById(1l);
		
		assertThat(foundSpeciality).isNotNull();
		
		verify(repository).findById(anyLong());
	}*/

}
