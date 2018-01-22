package com.iqmsoft.rest.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqmsoft.rest.entity.Role;
import com.iqmsoft.rest.entity.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MainControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	private RestTemplate restTemplate;

	@Autowired
	private Filter springSecurityFilterChain;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(springSecurityFilterChain).build();
	
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
	
		restTemplate = new RestTemplate();
	}

	
	
	
	
	@Test
	public void verifyRestaurants() throws Exception {
		mockMvc.perform(get("/restaurants").with(httpBasic("admin", "admin")))
		.andExpect(status().is4xxClientError())
		.andExpect(authenticated().withUsername("admin"));

	}

	@Test
	@WithMockUser(username="admin", password="admin", roles={"USER","ADMIN"})
	public void verifyVotesResults() throws Exception {
		mockMvc.perform(get("/restaurants/votes/results").with(httpBasic("admin", "admin")))
				.andExpect(status().is4xxClientError())
				.andExpect(authenticated().withUsername("admin"));

	}
	
	@Test
	@WithMockUser(username="admin", password="admin", roles={"USER","ADMIN"})
	public void verifyDeleteResults() throws Exception {
		mockMvc.perform(delete("/restaurants/1").with(httpBasic("admin", "admin")))
				.andExpect(status().is4xxClientError())
				.andExpect(authenticated().withUsername("admin"));

	}
	
	
	
	
	 @Test
	// @WithMockUser(username="admin", password="admin", roles={"USER","ADMIN"})
	 public void testJWTFilter() throws Exception{
	        // perform login
	        User user = new User();
	        user.setLogin("aaa");
	        user.setPassword("aaa");
	        MvcResult loginResult = mockMvc.perform(post("/users/reg")
	                .content(mapper.writeValueAsString(user))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().is4xxClientError())
	                .andReturn();
	        
	       // User computer = mapper.readValue(loginResult.getResponse().getContentAsString(), 
	        		//User.class);
	        
	        MockHttpServletResponse response = loginResult.getResponse();

			
	        
	     
	 }

	@SuppressWarnings("deprecation")
	@Test
	public void requiresAuthentication() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isMovedTemporarily());
	}

	@Test
	public void httpBasicAuthenticationSuccess() throws Exception {
		mockMvc.perform(get("/secure/something").with(httpBasic("admin", "admin"))).andExpect(status().isNotFound())
				.andExpect(authenticated().withUsername("admin"));
	}
	
	
	
	@Test
	@WithMockUser(username="admin", password="admin", roles={"USER","ADMIN"})
	public void testUnauthRequest() throws Exception{
		        mockMvc.perform(post("/restaurants/1/vote/1").accept(MediaType.APPLICATION_JSON))
		                .andExpect(status().is4xxClientError());
	}	
		
	
	 @Test
	 @WithMockUser(username="admin", password="admin", roles={"USER","ADMIN"})
	 public void testUnauthorizedRequest() throws Exception{
	        mockMvc.perform(post("/restaurants").accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().is4xxClientError());
	 }


	@SuppressWarnings("deprecation")
	@Configuration
	@EnableWebMvcSecurity
	@EnableWebMvc
	static class Config extends WebSecurityConfigurerAdapter {
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
		}
	}

}
