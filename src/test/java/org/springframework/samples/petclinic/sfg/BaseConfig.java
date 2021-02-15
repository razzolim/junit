/*
 * COPYRIGHT RENAN AZZOLIM 2021 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of Renan Azzolim.
 */
package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Renan
 *
 * @since 15-02-2021
 *
 */
@Configuration
public class BaseConfig {

	@Bean
	HearingInterpreter hearingInterpreter(WordProducer wordProducer) {
		return new HearingInterpreter(wordProducer);
	}
	
}
