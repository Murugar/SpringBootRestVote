package com.iqmsoft.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.iqmsoft.rest.entity.*;
import com.iqmsoft.rest.repository.*;
import com.iqmsoft.rest.service.RestaurantService;
import com.iqmsoft.rest.service.VoteResultService;
import com.iqmsoft.rest.service.VoteService;
import com.iqmsoft.rest.util.RestaurantNotFoundException;
import com.iqmsoft.rest.util.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

	private final RestaurantRepository restaurantRepository;

	private final VoteRepository voteRepository;
	private final UserRepository userRepository;
	private final VoteResultRepository voteResultRepository;

	@Autowired
	private RestaurantService rs;

	@Autowired
	private VoteService vs;

	@Autowired
	private VoteResultService vss;

	@Autowired
	public RestaurantRestController(RestaurantRepository restaurantRepository, VoteRepository voteRepository,
			UserRepository userRepository, VoteResultRepository voteResultRepository) {

		this.restaurantRepository = restaurantRepository;
		this.voteRepository = voteRepository;
		this.userRepository = userRepository;
		this.voteResultRepository = voteResultRepository;
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Collection<Restaurant>> readAll() {
		log.info("Getting All Restaurants");
		return new ResponseEntity<>(rs.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/sortbyname/{type}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Restaurant>> readAllSortByAge(@PathVariable String type) {
		log.info("Getting All Restaurants");
		return new ResponseEntity<>(restaurantRepository.findByTypeOrderByNameDesc(type), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/sortbyvote/{type}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Restaurant>> readAllSortByVote(@PathVariable String type) {
		log.info("Getting All Restaurants");
		return new ResponseEntity<>(restaurantRepository.findByTypeOrderByCounterDesc(type), HttpStatus.OK);
	}
	
	
	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/find/{restaurantId}")
	public ResponseEntity<Restaurant> readOne(@Valid @PathVariable Long restaurantId) {
		
		log.info("Test ReadOne Restaurant");
		
		Optional<Restaurant> r = restaurantRepository.findById(restaurantId);
		
		if(r.get() == null) throw new RestaurantNotFoundException("Restaurant Not Found");
		
		return new ResponseEntity<>(r.get(), HttpStatus.OK);
				
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public Restaurant add(@Valid @RequestBody Restaurant newRestaurant) {
		log.info("New Restaurant Post");
		return restaurantRepository.save(new Restaurant(newRestaurant.getName(),
					newRestaurant.getType(),
					newRestaurant.getPicUrl()
				));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/givevote/{restaurantId}/vote/{userId}")
	public ResponseEntity<Vote> updateVote(@Valid @PathVariable Long restaurantId, @Valid @PathVariable Long userId) {

		Vote vote = null;

		log.info("Update Vote");

		User user = userRepository.findById(userId).orElse(null);

		if (user == null) {
			throw new UserNotFoundException("User not Found");
		}

		Restaurant restaurant = restaurantRepository.findOne(restaurantId);

		if (user != null) {

			if (restaurant == null) {

				throw new RestaurantNotFoundException("Restaurant not Found");
			}

			List<Vote> test = voteRepository.findByUser(user).orElse(null);

			if ((test != null)) {

				for (Vote v : test) {
					if (v.getRestaurant().getId() == restaurant.getId()) {
						return new ResponseEntity<>(vote, HttpStatus.FOUND);
					}
				}

			}

			if ((vote == null)) {
				vote = new Vote(user, restaurant);

				VoteResult voteResult = voteResultRepository.findByRestaurant(restaurant).orElse(null);

				if (voteResult == null) {
					voteResult = new VoteResult(restaurant, 1L);
					restaurant.setCounter(1L);
				} else {
					voteResult.setCounter(voteResult.getCounter() + 1);
					restaurant.setCounter(voteResult.getCounter() + 1);
				}

				voteResultRepository.save(voteResult);
				restaurantRepository.save(restaurant);

			} else {

				vote = new Vote(user, restaurant);

				VoteResult voteResult = voteResultRepository.findByRestaurant(restaurant).orElse(null);

				if (voteResult == null) {
					voteResult = new VoteResult(restaurant, 1L);
					restaurant.setCounter(1L);
				} else {
					voteResult.setCounter(voteResult.getCounter() + 1);
					restaurant.setCounter(voteResult.getCounter() + 1);
				}

				voteResultRepository.save(voteResult);
				restaurantRepository.save(restaurant);
			}

			voteRepository.save(vote);

		}

		return new ResponseEntity<>(vote, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{type}")
	public ResponseEntity<List<Restaurant>> GetByType(@Valid @PathVariable String type){

		List<Restaurant> r = restaurantRepository.findAll();
		
		if(r == null){
			throw new RestaurantNotFoundException("Restaurants not found");
		}

		log.info("Restaurant GetByType");

		Map<String, List<Restaurant>> m = r.stream().collect(Collectors.groupingBy(Restaurant::getType));

		if (m.containsKey(type)) {
			return new ResponseEntity<>(m.get(type), HttpStatus.OK);
		}

		else {
			throw new RestaurantNotFoundException("Restaurant Not Found");

		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/groupbytype")
	public ResponseEntity<Map<String, List<Restaurant>>> GroupByType() {

		List<Restaurant> r = restaurantRepository.findAll();
		
		if(r == null)
		{
			throw new RestaurantNotFoundException("Votes Not Found");
		}

		log.info("Restaurants GroupedByType");

		Map<String, List<Restaurant>> m = r.stream().collect(Collectors.groupingBy(Restaurant::getType));

		return new ResponseEntity<>(m, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{restaurantId}")
	public Restaurant update(@Valid @PathVariable Long restaurantId, @Valid @RequestBody Restaurant newRestaurant) {
		
		log.info("Updating All Restaurants");
		
		return restaurantRepository.findById(restaurantId).map(restaurant -> {
			restaurant.setName(newRestaurant.getName());
			restaurant.setType(newRestaurant.getType());
			restaurant.setPicUrl(newRestaurant.getPicUrl());
			restaurantRepository.save(restaurant);
			return restaurant;
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/remove/{restaurantId}")
	public ResponseEntity<?> del(@Valid @PathVariable Long restaurantId) {

		log.info("Deleting Restaurant "  +  restaurantId);
		
		return restaurantRepository.findById(restaurantId).map(restaurant -> {
			restaurantRepository.delete(restaurant);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/votes")
	public ResponseEntity<?> votesDel() {
		
		log.info("Deleting All Votes and Vote Results ");
		
		voteRepository.deleteAll();
		voteResultRepository.deleteAll();
		return ResponseEntity.ok().build();
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/votes")
	public ResponseEntity<Collection<Vote>> votes() {
		
		log.info("Restaurant All Votes ");
		
		Collection<Vote> c = vs.getAll();
		
		if(c == null)
		{
			throw new RestaurantNotFoundException("Votes Not Found");
		}
		
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/votes/results")
	public ResponseEntity<Collection<VoteResult>> votesResult() {
		
		log.info("Restaurant All VotesResults");
		
		Collection<VoteResult> c = vss.getAll();
		
		if(c == null)
		{
			throw new RestaurantNotFoundException("Votes Not Found");
		}
		
		return new ResponseEntity<>(c, HttpStatus.OK);
	}

}
