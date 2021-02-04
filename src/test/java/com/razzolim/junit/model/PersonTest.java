package com.razzolim.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model")
class PersonTest {
	
	@Test
	void groupedAssertions() {
		Person person = new Person(1L, "Renan", "Azzolim");
		
		assertAll("Test properties set",
				() -> assertEquals("Renan", person.getFirstName(), "First name failed"),
				() -> assertEquals("Azzolim", person.getLastName(), "Last name failed"));
	}

}
