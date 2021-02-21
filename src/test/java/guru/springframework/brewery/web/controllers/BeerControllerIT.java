/*
 * COPYRIGHT RENAN AZZOLIM 2021 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of Renan Azzolim.
 */
package guru.springframework.brewery.web.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import guru.springframework.brewery.web.model.BeerPagedList;

/**
 * @author Renan
 *
 * @since 19-02-2021
 *
 */
@Disabled
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BeerControllerIT {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testListBeers() {
		BeerPagedList beerPagedList = restTemplate.getForObject("/api/v1/beer", BeerPagedList.class);
		
		assertThat(beerPagedList.getContent()).hasSize(3);
	}

}
