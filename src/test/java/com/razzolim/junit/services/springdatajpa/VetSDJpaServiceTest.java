package com.razzolim.junit.services.springdatajpa;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.razzolim.junit.repositories.VetRepository;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {
	
	@Mock
	VetRepository repository;
	
	@InjectMocks
	VetSDJpaService service;
	
	@Test
	void deleteById() {
		service.deleteById(1l);
		
		verify(repository, times(1)).deleteById(1l);
		
	}

}
