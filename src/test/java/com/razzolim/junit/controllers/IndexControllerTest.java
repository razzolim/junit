package com.razzolim.junit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IndexControllerTest {

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
	}
	
//	@Disabled("Demo of timeout")
	@Test
	void testTimeOut() {
		assertTimeout(Duration.ofMillis(1000), () -> {
			Thread.sleep(5000);
			
			System.out.println("I got here");
		});
	}
	
//	@Disabled("Demo of timeout")
	@Test
	void testTimePrempt() {
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			Thread.sleep(5000);
			
			System.out.println("lol");
		});
	}

}
