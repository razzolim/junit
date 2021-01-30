package com.razzolim.junit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexControllerTest {
	
	IndexController controller;
	
	@BeforeEach
	void setUp() {
		controller = new IndexController();
	}
	
	@Test
	void index() {
		assertEquals("index", controller.index());
	}
	
	@Test
	void oupsHandler() {
		
	}

}
