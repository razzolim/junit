package com.razzolim.junit.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import com.razzolim.junit.ModelRepeatedTest;

@DisplayName("Person Repeated Tests")
class PersonRepeatedTest implements ModelRepeatedTest {

	@Disabled // renan
	@RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
	@DisplayName("My repeated test")
	void myRepeatedTest() {
		
	}
	
	@Disabled // renan
	@RepeatedTest(5)
	void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
	}
	
	@RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
	@DisplayName("My Assignment Repeated Test")
	void myAssignmentRepeated() {
		System.out.println("lol");
	}

}
