package com.razzolim.junit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

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
		
		assertThat(controller.index()).isEqualTo("index");
	}

	@DisplayName(value = "Test exception")
	@Test
	void oupsHandler() {
		assertThrows(ValueNotFoundException.class,	() -> {
			controller.oopsHandler();
		});
	}
	
	@Disabled("Demo of timeout")
	@Test
	void testTimeOut() {
		assertTimeout(Duration.ofMillis(1000), () -> {
			Thread.sleep(5000);
			
			System.out.println("I got here");
		});
	}
	
	@Disabled("Demo of timeout")
	@Test
	void testTimePrempt() {
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			Thread.sleep(5000);
			
			System.out.println("lol");
		});
	}
	
	@Test
	void testAssumptionTrue() {
		assumeTrue("TESTE".equalsIgnoreCase(System.getenv("TESTE_RUNTIME")));
	}
	
	@Test
	void testAssumptionIsTrue() {
		assumeTrue("TESTE".equalsIgnoreCase("TESTE"));
	}
	
	@EnabledOnOs(OS.MAC)
	@Test
	void testMeOnMacOS() {
		// 
	}

	@EnabledOnOs(OS.WINDOWS)
	@Test
	void testMeOnWindows() {
		//
	}
	
	@EnabledOnJre(JRE.JAVA_8)
	@Test
	void testMeOnJava8() {
		
	}

	@EnabledOnJre(JRE.JAVA_11)
	@Test
	void testMeOnJava11() {
		
	}
	
	@EnabledIfEnvironmentVariable(named = "USER", matches = "Renan")
	@Test
	void testIfUserRenan() {
		
	}
	
	@EnabledIfEnvironmentVariable(named = "USER", matches = "JT")
	@Test
	void testIfUserJT() {
		
	}

}
