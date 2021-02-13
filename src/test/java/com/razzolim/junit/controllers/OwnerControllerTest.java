package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.razzolim.junit.fauxspring.BindingResult;
import com.razzolim.junit.model.Owner;
import com.razzolim.junit.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	/**
	 * String - REDIRECT_OWNERS_5.
	 */
	private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

	/**
	 * String - OWNERS_CREATE_OR_UPDATE_OWNER_FORM.
	 */
	private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";

	@Mock
	OwnerService service;

	@InjectMocks
	OwnerController controller;

	@Mock
	BindingResult bindingRes;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Test
	void processFindFormWildcardsString() {
		// given
		Owner owner = new Owner(1l, "Joe", "Buck");
		List<Owner> ownerList = new ArrayList<>();
		final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		given(service.findAllByLastNameLike(captor.capture())).willReturn(ownerList);
		
		// when
		String viewName = controller.processFindForm(owner, bindingRes, null);
		
		// then
		assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());
	}
	
	@Test
	void processFindFormWildcardsStringAnnotation() {
		// given
		Owner owner = new Owner(1l, "Joe", "Buck");
		List<Owner> ownerList = new ArrayList<>();
		given(service.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);
		
		// when
		String viewName = controller.processFindForm(owner, bindingRes, null);
		
		// then
		assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
	}

	@Test
	void processCreationFormHasErrors() {
		// given
		Owner owner = new Owner(1l, "Jim", "Bob");
		given(bindingRes.hasErrors()).willReturn(true);

		// when
		String viewName = controller.processCreationForm(owner, bindingRes);

		// then
		assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
	}

	@Test
	void processCreationFormNoErrors() {
		// given
		Owner owner = new Owner(5l, "Jim", "Bob");
		given(bindingRes.hasErrors()).willReturn(false);
		given(service.save(any())).willReturn(owner);

		// when
		String viewName = controller.processCreationForm(owner, bindingRes);

		// then
		assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
	}

}
