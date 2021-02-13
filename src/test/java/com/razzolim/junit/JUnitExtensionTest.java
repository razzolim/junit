package com.razzolim.junit;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JUnitExtensionTest {
	
	@Mock
	Map<String, Object> mapMock;
	
	@Test
	void testMock() {
		
		mapMock.put("keyValue", "foo");
		
	}

}
