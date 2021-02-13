package com.razzolim.junit.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
class VisitSDJpaServiceTest {
	
	@Mock
	VisitRepository repository;
	
	@InjectMocks
	VisitSDJpaService service;
	
	@DisplayName("Test Find All")
	@Test
	void findAll() {
		Visit visit = new Visit();
		
		Set<Visit> visits = new HashSet<>();
		visits.add(visit);
		
		/* método responsável por mockar um valor no método da service
		 * quando ele for chamado pela service.
		 * Perceba que estou mochando o Set visits no método.
		 */
		when(repository.findAll()).thenReturn(visits);
		
		/* aqui receberei o visits setado no método when() */
		Set<Visit> foundVisits = service.findAll();

		/* Verify in Mockito simply means that you want to check if a certain method
		 * of a mock object has been called by specific number of times.
		 */
		verify(repository).findAll();
		
		assertThat(foundVisits).hasSize(1);
	}

	@DisplayName("Test Find By Id")
	@Test
	void findById() {
		Visit visit = new Visit();
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(visit));
		
		Visit foundVisit = service.findById(1l);
		
		verify(repository, times(1)).findById(anyLong());
		
		assertThat(foundVisit).isNotNull();
	}


	@DisplayName("Test Save")
	@Test
	void save() {
		Visit visit = new Visit();
		
		when(repository.save(any(Visit.class))).thenReturn(visit);
		
		Visit savedVisit = service.save(new Visit());
		
		verify(repository).save(any(Visit.class));
		
		assertThat(savedVisit).isNotNull();
	}

	@DisplayName("Test Delete")
	@Test
	void delete() {
		Visit visit = new Visit();
		
		service.delete(visit);
		
		/* Verify in Mockito simply means that you want to check if a certain method
		 * of a mock object has been called by specific number of times.
		 */
		verify(repository).delete(any(Visit.class));
		
		/* como esse método não tem retorno, não precisaria fazer um assert */
	}

	@DisplayName("Test Delete By Id")
	@Test
	void deleteById() {
		
		service.deleteById(1l);
		
		verify(repository).deleteById(anyLong());
		
	}
}
