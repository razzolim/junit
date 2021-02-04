package com.razzolim.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("model")
public interface ModelTest {
	
	@BeforeEach
	default void beforeEachMethod(TestInfo testInfo) {
		System.out.println("Running Test - " + testInfo.getDisplayName());
	}

}
