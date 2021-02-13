package com.razzolim.junit.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.razzolim.junit.model.Visit;
import com.razzolim.junit.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceBDDTest {
	
	@Mock
	VisitRepository repository;
	
	@InjectMocks
	VisitSDJpaService service;
	
	@DisplayName("Test Find All")
	@Test
	void findAll() {
		
		// given
		Visit visit = new Visit();
		Set<Visit> visits = new HashSet<>();
		visits.add(visit);
		
		given(repository.findAll()).willReturn(visits);
//		when(repository.findAll()).thenReturn(visits);
		
		// when
		Set<Visit> foundVisits = service.findAll();

		// then
		then(repository).should().findAll();
//		verify(repository).findAll();
		assertThat(foundVisits).hasSize(1);
	}

	@DisplayName("Test Find By Id")
	@Test
	void findById() {
		// given
		Visit visit = new Visit();
		given(repository.findById(anyLong())).willReturn(Optional.of(visit));
		
//		when(repository.findById(anyLong())).thenReturn(Optional.of(visit));
		
		// when
		Visit foundVisit = service.findById(1l);
		
		// then
//		verify(repository, times(1)).findById(anyLong());
		then(repository).should().findById(anyLong());
		assertThat(foundVisit).isNotNull();
	}


	@DisplayName("Test Save")
	@Test
	void save() {
		// given
		Visit visit = new Visit();
		given(repository.save(any(Visit.class))).willReturn(visit);
		
//		when(repository.save(any(Visit.class))).thenReturn(visit);
		
		// when
		Visit savedVisit = service.save(new Visit());
		
		// then
//		verify(repository).save(any(Visit.class));
		then(repository).should().save(any(Visit.class));
		assertThat(savedVisit).isNotNull();
	}

	@DisplayName("Test Delete")
	@Test
	void delete() {
		// given
		Visit visit = new Visit();
		
		// when
		service.delete(visit);
		
		// then
		then(repository).should().delete(any(Visit.class));
//		verify(repository).delete(any(Visit.class));
		
	}

	@DisplayName("Test Delete By Id")
	@Test
	void deleteById() {
		// when
		service.deleteById(1l);
		
		// then
		then(repository).should().deleteById(anyLong());
//		verify(repository).deleteById(anyLong());
	}
}
