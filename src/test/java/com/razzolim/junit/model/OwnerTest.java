package com.razzolim.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OwnerTest {
	
	@Test
	void dependentAssertions() {
		Owner owner = new Owner(1l, "Joe", "Buck");
		owner.setCity("Curitiba");
		owner.setTelephone("23984728934");
		
		assertAll("Properties Test",
				() -> assertAll("Person properties",
						() -> assertEquals("Joe", owner.getFirstName(), "First name did not match"),
						() -> assertEquals("Buck", owner.getLastName(), "Last name did not match"),
				() -> assertAll("Owner properties",
						() -> assertEquals("Curitiba", owner.getCity(), "City did not match"),
						() -> assertEquals("23984728934", owner.getTelephone()))
				));
	}

}
