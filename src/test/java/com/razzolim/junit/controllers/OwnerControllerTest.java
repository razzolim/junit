package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.razzolim.junit.fauxspring.BindingResult;
import com.razzolim.junit.fauxspring.Model;
import com.razzolim.junit.model.Owner;
import com.razzolim.junit.services.OwnerService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
	
	@Mock
	Model model;

	@InjectMocks
	OwnerController controller;

	@Mock
	BindingResult bindingRes;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@BeforeEach
	void setUp() {
		given(service.findAllByLastNameLike(stringArgumentCaptor.capture()))
			.willAnswer(invocation -> {
				final List<Owner> owners = new ArrayList<>();
				
				final String name = invocation.getArgument(0);
				if (name.equals("%Buck%")) {
					owners.add(new Owner(1l, "Joe", "Buck"));
					return owners;
				} else if (name.equals("%DontFindMe%")) {
					return owners;
				} else if (name.equals("%FindMe%")) {
					owners.add(new Owner(1l, "Joe", "Buck"));
					owners.add(new Owner(2l, "Joe2", "Buck2"));
					return owners;
				}
				
				throw new RuntimeException("Invalid Argument");
			});
	}
	
	@Test
	void processFindFormWildcardFound() {
		// given
		Owner owner = new Owner(1l, "Joe", "FindMe");
		InOrder inOrder = inOrder(service, model);
		
		// when
		String viewName = controller.processFindForm(owner, bindingRes, model);
		
		// then
		assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
		
		// in order asserts
		inOrder.verify(service).findAllByLastNameLike(anyString());
		inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
		verifyNoMoreInteractions(model);
	}
	
	@Test
	void processFindFormWildcardsStringAnnotation() {
		// given
		Owner owner = new Owner(1l, "Joe", "Buck");
//		List<Owner> ownerList = new ArrayList<>();
//		given(service.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);
		
		// when
		String viewName = controller.processFindForm(owner, bindingRes, null);
		
		// then
		assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
		verifyZeroInteractions(model);
	}
	
	@Test
	void processFindFormWildcardsNotFound() {
		// given
		Owner owner = new Owner(1l, "Joe", "DontFindMe");
		
		// when
		String viewName = controller.processFindForm(owner, bindingRes, null);
		
//		verifyNoMoreInteractions(service); not working
		
		// then
		assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
		assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
		verifyNoMoreInteractions(model);
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
