package com.iqmsoft.rest.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iqmsoft.rest.MainTests;
import com.iqmsoft.rest.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;


public class RepositoryTest extends MainTests {

    @Autowired
    private RestaurantRepository rrepos;
     

    @Before
    public void setUp(){
       
    }


    @Test
    public void testQuery(){
    	
        List<Restaurant> l = rrepos.findAll();
        Assert.assertNotNull(l);

    }
    
    @Test
    public void testSaveAndQuery(){
    
       Restaurant l = rrepos.findOne(1l);
       Assert.assertNotNull(l);
       
       Restaurant lk = new Restaurant("test40", "Chinese", "http://test.com/one.png", 12);
       
       Restaurant lk2 = rrepos.save(lk);
       
       Assert.assertNotNull(rrepos.findByType("Chinese"));
       
       Assert.assertNotNull(rrepos.findById(lk2.getId()));
       
    
    }

    
}