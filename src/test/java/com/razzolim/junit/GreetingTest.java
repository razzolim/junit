package com.razzolim.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Renan
 *
 *	@see <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests">JUnit DOC</a>
 *
 */
class GreetingTest {
	
	private Greeting greeting;
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println("Before - I am only called once...");
	}
	
	@BeforeEach
	void setUp() {
		System.out.println("In before each...");
		greeting = new Greeting();
	}
	
	@Test
	void helloWorld() {
		System.out.println(greeting.helloWorld());
	}
	
	@Test
	void helloWorld1() {
		System.out.println(greeting.helloWorld("Renan"));
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("In after each...");
	}
	
	@AfterAll
	public static void afterClass() {
		System.out.println("After - I am only called once...");
	}

}
