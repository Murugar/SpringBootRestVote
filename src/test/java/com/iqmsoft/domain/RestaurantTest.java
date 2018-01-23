package com.iqmsoft.domain;

import org.junit.Before;
import org.junit.Test;

import com.iqmsoft.rest.entity.Restaurant;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTest {

   
    private Restaurant testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new Restaurant();
        testedObject.setName("Test1");
        testedObject.setType("Indian");
        testedObject.setCounter(2l);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getName()).isEqualTo("Test1");
        assertThat(testedObject.getType()).isEqualTo("Indian");
        assertThat(testedObject.getCounter()).isEqualTo(2l);
    }
}
