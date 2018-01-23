package com.iqmsoft.rest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.entity.VoteResult;
import com.iqmsoft.rest.repository.RestaurantRepository;
import com.iqmsoft.rest.repository.UserRepository;
import com.iqmsoft.rest.repository.VoteRepository;
import com.iqmsoft.rest.repository.VoteResultRepository;



@RunWith(SpringRunner.class)
public class AllServiceTest {
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private VoteRepository voteRepository;
	
	@Mock
	private VoteResultRepository voteresultRepository;
	
	@InjectMocks
	private RestaurantService restaurantService;
	
	@InjectMocks
	private UserService userService;
	
	@InjectMocks
	private VoteService voteService;
	
	@InjectMocks
	private VoteResultService voteresultService;
	
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getAllRestaurantsTest() {
		
		List<Restaurant> list = new ArrayList<>();
		list.add(new Restaurant("test1"));
		list.add(new Restaurant("test2"));
		
		when(restaurantRepository.findAll()).thenReturn(list);
		
		List<Restaurant> result = restaurantService.getAll();
		
		assertEquals(result, list);
		
	}
	
	@Test
	public void getAllUsersTest() {
		
		List<User> list = new ArrayList<>();
		list.add(new User("test1", "test1"));
		list.add(new User("test2", "test2"));
		
		when(userRepository.findAll()).thenReturn(list);
		
	    List<User> result = userService.getAll();
		
		assertEquals(result, list);
		
	}
	
	@Test
	public void getAllVotesTest() {
		
		List<Vote> list = new ArrayList<>();
		list.add(new Vote(new User("test1", "test1"), new Restaurant("test1")));
		list.add(new Vote(new User("test2", "test2"), new Restaurant("test2")));
		
		when(voteRepository.findAll()).thenReturn(list);
		
		List<Vote> result = voteService.getAll();
			
		assertEquals(result, list);
		
	}
	
	@Test
	public void getAllVotesResultTest() {
		
		List<VoteResult> list = new ArrayList<>();
		list.add(new VoteResult(new Restaurant("test1"), 1l));
		list.add(new VoteResult(new Restaurant("test2"), 2l));
		
		when(voteresultRepository.findAll()).thenReturn(list);
		
		List<VoteResult> result = voteresultService.getAll();
		
		assertEquals(result, list);
		
	}
	
	
	
	
}
