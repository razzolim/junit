package com.razzolim.junit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IndexControllerTest {

	IndexController controller;

	@BeforeEach
	void setUp() {
		controller = new IndexController();
	}

	@DisplayName(value = "Test proper view name is returned for index page")
	@Test
	void index() {
		assertEquals("index", controller.index());
	}

	@DisplayName(value = "Test exception")
	@Test
	void oupsHandler() {
		assertThrows(ValueNotFoundException.class,	() -> {
			controller.oopsHandler();
		});
		
//		assertTrue("notimplemented".equals(controller.oopsHandler()),
//				() -> "this is some expensive message to build for my test");
	}

}
