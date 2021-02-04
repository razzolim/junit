package com.razzolim.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.razzolim.junit.ModelTest;

class PersonTest implements ModelTest {
	
	@Test
	void groupedAssertions() {
		Person person = new Person(1L, "Renan", "Azzolim");
		
		assertAll("Test properties set",
				() -> assertEquals("Renan", person.getFirstName(), "First name failed"),
				() -> assertEquals("Azzolim", person.getLastName(), "Last name failed"));
	}

	@RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
	@DisplayName("My repeated test")
	@Test
	void myRepeatedTest() {
		
	}

}
