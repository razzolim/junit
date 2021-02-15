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

import org.springframework.stereotype.Service;

/**
 * @author Renan
 *
 * @since 15-02-2021
 *
 */
@Service
public class HearingInterpreter {
	
	private final WordProducer wordProducer;
	
	/**
	 * 
	 */
	public HearingInterpreter(WordProducer wordProducer) {
		this.wordProducer = wordProducer;
	}
	
	public String whatIHeard() {
		String word = wordProducer.getWord();
		System.out.println(word);
		return word;
	}

}
