package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.razzolim.junit.model.Pet;
import com.razzolim.junit.model.Visit;
import com.razzolim.junit.services.PetService;
import com.razzolim.junit.services.VisitService;
import com.razzolim.junit.services.map.PetMapService;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
	
	@Mock
	VisitService visitService;
	
	@Spy // @Mock
	PetMapService petService;
	
	@InjectMocks
	VisitController visitController;
	
	@Test
	void loadPetWithVisit() {
		// given
		Map<String, Object> model = new HashMap<>();
		Pet pet = new Pet(12l);
		Pet pet3 = new Pet(3l);
		
		petService.save(pet);
		petService.save(pet3);
		
		given(petService.findById(anyLong())).willCallRealMethod();//.willReturn(pet);
		
		// when
		Visit visit = visitController.loadPetWithVisit(12l, model);
		
		assertThat(visit).isNotNull();
		assertThat(visit.getPet()).isNotNull();
		assertThat(visit.getPet().getId()).isEqualTo(12l);
		
		verify(petService, times(1)).findById(anyLong());
	}
	
	@Test
	void loadPetWithVisitWithStubbing() {
		// given
		Map<String, Object> model = new HashMap<>();
		Pet pet = new Pet(12l);
		Pet pet3 = new Pet(3l);
		
		petService.save(pet);
		petService.save(pet3);
		
		given(petService.findById(anyLong())).willReturn(pet3);
		
		// when
		Visit visit = visitController.loadPetWithVisit(12l, model);
		
		assertThat(visit).isNotNull();
		assertThat(visit.getPet()).isNotNull();
		assertThat(visit.getPet().getId()).isEqualTo(3l);
		
		verify(petService, times(1)).findById(anyLong());
	}

}
