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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Renan
 *
 * @since 15-02-2021
 *
 */
@Profile({"externalized", "laurel-properties-profile"})
@Component
@Primary
public class PropertiesWordProducer implements WordProducer {

	private String word;
	
	@Value("${say.word}")
	public void setWord(String word) {
		this.word = word;
	}
	
	@Override
	public String getWord() {
		return word;
	}

}