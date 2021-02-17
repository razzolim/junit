/*
 * COPYRIGHT RENAN AZZOLIM 2021 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of Renan Azzolim.
 */
package org.springframework.samples.petclinic.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Renan
 *
 * @since 16-02-2021
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {
	
	@Autowired
	OwnerController controller;
	
	@Autowired
	ClinicService clinicService;
	
	MockMvc mockMvc;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@AfterEach
	void tearDown() {
		reset(clinicService);
	}
	
	@Test
    void testUpdateOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                    .param("firstName", "Jimmy")
                    .param("lastName", "Buffett")
                    .param("Address", "123 Duval St ")
                    .param("city", "Key West")
                    .param("telephone", "3151231234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }
	
	@Test
    void testUpdateOwnerPostNotValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                    .param("firstName", "Jimmy")
                    .param("lastName", "Buffett")
//                    .param("Address", "123 Duval St ")
//                    .param("city", "Key West")
//                    .param("telephone", "3151231234")
                    )
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
	
	@Test
    void testNewOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Jimmy")
                    .param("lastName", "Buffett")
                    .param("Address", "123 Duval St ")
                    .param("city", "Key West")
                    .param("telephone", "3151231234"))
                .andExpect(status().is3xxRedirection());
    }
	
	@Test
    void testNewOwnerPostNotValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Jimmy")
                    .param("lastName", "Buffett")
//                    .param("Address", "123 Duval St ")
                    .param("city", "Key West")
//                    .param("telephone", "3151231234")
                    )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
	
	@Test
	void testReturnListOfOwners() throws Exception {
		given(clinicService.findOwnerByLastName("")).willReturn(Lists.newArrayList(new Owner(), new Owner()));
		
		mockMvc.perform(get("/owners"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"));
		
		then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("");
	}
	
	@Test
	void testFindOwnerOneResult() throws Exception {
		final String findJustOne = "FindJustOne";
		final Owner justOne = new Owner();
		justOne.setId(1);
		justOne.setLastName(findJustOne);
		
		given(clinicService.findOwnerByLastName(findJustOne)).willReturn(Lists.newArrayList(justOne));
		
		mockMvc.perform(get("/owners")
				.param("lastName", findJustOne))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"));
		
		then(clinicService).should().findOwnerByLastName(anyString());
	}
	
	@Test
	void testFindByNameNotFound() throws Exception {
		mockMvc.perform(get("/owners")
				.param("lastName", "Dont find ME"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"));
	}
	
	@Test
	void initCreationFormTest() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

}
