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
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.entity.VoteResult;

import java.util.ArrayList;
import java.util.List;


public class RepositoryTest extends MainTests {

    @Autowired
    private RestaurantRepository rrepos;
    
    @Autowired
    private UserRepository urepos;
    
    @Autowired
    private VoteRepository vrepos;
    
    @Autowired
    private VoteResultRepository vresrepos;
     

    @Before
    public void setUp(){
       
    }


    @Test
    public void testQuery(){
    	
        List<Restaurant> l = rrepos.findAll();
        Assert.assertNotNull(l);

    }
    
    @Test
    public void testUserSaveAndQuery(){
    
       User l = urepos.findOne(1l);
       
       Assert.assertNotNull(l);
       
       User uk = new User("test8", "test8") ;
       
       User uk2 = urepos.save(uk);
       
     
       
       Assert.assertNotNull(urepos.findById(uk2.getId()));
       
    
    }
    
    @Test
    public void testVotesSaveAndQuery(){
    
       User l = urepos.findOne(1l);
       
       Assert.assertNotNull(l);
       
       User uk = new User("test7", "test7") ;
       
       User uk2 = urepos.save(uk);
       
       Restaurant lk = new Restaurant("test40", "Chinese", "http://test.com/one.png", 12);
       
       Restaurant lk2 = rrepos.save(lk);
       
       Vote v = new Vote(uk, lk);
       
       
       Vote v2 = vrepos.save(v);
       
       Assert.assertNotNull(uk2);
       Assert.assertNotNull(lk2);
       Assert.assertNotNull(v2);
    
    }

    @Test
    public void testVoteResultSaveAndQuery(){
    
       Restaurant l = rrepos.findOne(1l);
       Assert.assertNotNull(l);
       
       Restaurant lk = new Restaurant("test40", "Chinese", "http://test.com/one.png", 12);
       
       Restaurant lk2 = rrepos.save(lk);
       
       VoteResult vr1 = new VoteResult(lk, 4l);
       
       VoteResult vr = vresrepos.save(vr1);
       
      
       
       Assert.assertNotNull(vr);
       
    
    }
    
    @Test
    public void testRestaurantSaveAndQuery(){
    
       Restaurant l = rrepos.findOne(1l);
       Assert.assertNotNull(l);
       
       Restaurant lk = new Restaurant("test40", "Chinese", "http://test.com/one.png", 12);
       
       Restaurant lk2 = rrepos.save(lk);
       
       Assert.assertNotNull(rrepos.findByType("Chinese"));
       
       Assert.assertNotNull(rrepos.findById(lk2.getId()));
       
    
    }

    
}