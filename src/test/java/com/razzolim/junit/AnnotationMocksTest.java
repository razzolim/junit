package com.razzolim.junit;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AnnotationMocksTest {
	
	@Mock
	Map<String, Object> mapMock;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testMock() {
		
		mapMock.put("keyValue", "foo");
		
	}
	
}
