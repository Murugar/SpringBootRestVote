package com.iqmsoft.domain;

import org.junit.Before;
import org.junit.Test;


import com.iqmsoft.rest.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

   
    private User testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new User();
        testedObject.setLogin("Test1");
        testedObject.setPassword("Test2");
       
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getLogin()).isEqualTo("Test1");
        assertThat(testedObject.getPassword()).isEqualTo("Test2");
      
    }
}
