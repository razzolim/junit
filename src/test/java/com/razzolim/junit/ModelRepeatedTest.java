package com.razzolim.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTest {
	
	@BeforeEach
	default void beforeEachMethod(TestInfo testInfo, RepetitionInfo repetitionInfo) {
//		System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
		System.out.println("Running Test - " + testInfo.getDisplayName() + " | " + repetitionInfo.getTotalRepetitions());
	}

}
