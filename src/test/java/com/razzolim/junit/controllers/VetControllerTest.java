package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.razzolim.junit.fauxspring.Model;
import com.razzolim.junit.fauxspring.ModelMapImpl;
import com.razzolim.junit.model.Vet;
import com.razzolim.junit.services.SpecialityService;
import com.razzolim.junit.services.VetService;
import com.razzolim.junit.services.map.SpecialityMapService;
import com.razzolim.junit.services.map.VetMapService;

public class VetControllerTest {
	
	VetService vetService;
	SpecialityService specialityService;
	VetController vetController;
	
	@BeforeEach
	void setUp() {
		specialityService = new SpecialityMapService();
		vetService = new VetMapService(specialityService);
		vetController = new VetController(vetService);
		
		Vet vet1 = new Vet(1l,"renan", "azzolim", null);
		Vet vet2 = new Vet(2l,"teste", "validares", null);
		
		vetService.save(vet1);
		vetService.save(vet2);
	}
	
	@Test
	void listVets() {
		Model model = new ModelMapImpl();
		String view = vetController.listVets(model);
		
		assertThat("vets/index").isEqualTo(view);
		
		Set modelAttribute = (Set) ((ModelMapImpl) model).getMap().get("vets");
		
		assertThat(modelAttribute.size()).isEqualTo(2);
	}

}
