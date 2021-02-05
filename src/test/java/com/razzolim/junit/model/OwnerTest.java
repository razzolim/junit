package com.razzolim.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.razzolim.junit.ModelTest;

class OwnerTest implements ModelTest {
	
	@DisplayName("dependent assertions test")
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
	
	@DisplayName("Value Source Test")
	@ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
	@ValueSource(strings = {"Spring", "Framework", "Guru"})
	void testValueSource(String val) {
		System.out.println(val);
	}
	
	@DisplayName("Enum Source Test")
	@ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
	@EnumSource(OwnerType.class)
	void enumtest(OwnerType ownerType) {
		// iterates over the enumeration values (it's gonna be an individual test for each value)
		System.out.println(ownerType);
	}
	
	@DisplayName("CSV Input Test")
	@ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
	@CsvSource({
		"FL, 1, 1",
		"OH, 2, 2",
		"MI, 1, 1",
	})
	void csvInputTest(String stateName, int val1, int val2) {
		System.out.println(stateName + " = " + val1 + " : " + val2);
	}
	
	@DisplayName("CSV From File Test")
	@ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
	@CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
	void csvFromFileTest(String stateName, int val1, int val2) {
		// numLinesToSkip = 1 because there's a header line 
		System.out.println(stateName + " = " + val1 + " : " + val2);
	}

}
