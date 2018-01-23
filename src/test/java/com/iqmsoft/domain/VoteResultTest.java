package com.iqmsoft.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.entity.VoteResult;

public class VoteResultTest {

	private Restaurant testedObject;
	
	private VoteResult test2;

	@Before
	public void setUp() throws Exception {
		testedObject = new Restaurant();
		testedObject.setName("Test1");
		testedObject.setType("Indian");
		testedObject.setCounter(2l);

		test2 = new VoteResult(testedObject, 1l);
	}

	@Test
	public void shouldCreateObject() throws Exception {
		assertThat(testedObject).isNotNull();
		assertThat(testedObject.getName()).isEqualTo("Test1");
		assertThat(testedObject.getType()).isEqualTo("Indian");
		
		assertThat(testedObject.getCounter()).isEqualTo(2l);
		assertThat(test2).isNotNull();
		assertThat(test2.getRestaurant()).isNotNull();
		
		assertThat(test2.getRestaurant().getName()).isEqualTo("Test1");
		assertThat(test2.getRestaurant().getType()).isEqualTo("Indian");

		assertThat(test2.getCounter()).isEqualTo(1l);
	}

}
