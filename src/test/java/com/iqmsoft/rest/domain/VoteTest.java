package com.iqmsoft.rest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;

public class VoteTest {

	   
    private Restaurant testedObject;
    
    private User tested1Object;
    
    private Vote tested2Object;

    @Before
    public void setUp() throws Exception {
        testedObject = new Restaurant();
        testedObject.setName("Test1");
        testedObject.setType("Indian");
        testedObject.setCounter(2l);
        
        tested1Object = new User();
        tested1Object.setLogin("Test1");
        tested1Object.setPassword("Test2");
        
        tested2Object = new Vote(tested1Object, testedObject);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getName()).isEqualTo("Test1");
        assertThat(testedObject.getType()).isEqualTo("Indian");
        assertThat(testedObject.getCounter()).isEqualTo(2l);
        
        assertThat(tested1Object).isNotNull();
        assertThat(tested1Object.getLogin()).isEqualTo("Test1");
        assertThat(tested1Object.getPassword()).isEqualTo("Test2");
        
        assertThat(tested2Object).isNotNull();
        assertThat(tested2Object.getRestaurant()).isNotNull();
        assertThat(tested2Object.getUser()).isNotNull();
        
        assertThat(tested2Object.getUser().getLogin()).isEqualTo("Test1");
        assertThat(tested2Object.getUser().getPassword()).isEqualTo("Test2");
        
        assertThat(tested2Object.getRestaurant().getName()).isEqualTo("Test1");
        assertThat(tested2Object.getRestaurant().getType()).isEqualTo("Indian");
        
    }
}

