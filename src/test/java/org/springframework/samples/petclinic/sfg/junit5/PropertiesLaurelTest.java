/*
 * COPYRIGHT RENAN AZZOLIM 2021 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of Renan Azzolim.
 */
package org.springframework.samples.petclinic.sfg.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Renan
 *
 * @since 15-02-2021
 *
 */
@TestPropertySource("classpath:laurel.properties")
@ActiveProfiles("laurel-properties-profile")
@SpringJUnitConfig(classes = PropertiesLaurelTest.TestConfig.class)
class PropertiesLaurelTest {

	@Configuration
	@ComponentScan("org.springframework.samples.petclinic.sfg")
	static class TestConfig {

	}

	@Autowired
	HearingInterpreter hearingInterpreter;

	@Test
	void whatIHeard() {
		String word = hearingInterpreter.whatIHeard();

		assertEquals("LAUREL_LOL", word);
	}

}
