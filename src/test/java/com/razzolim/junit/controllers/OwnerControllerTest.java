package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
