package com.iqmsoft.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.iqmsoft.rest.entity.*;
import com.iqmsoft.rest.repository.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

	private final RestaurantRepository restaurantRepository;

	private final VoteRepository voteRepository;
	private final UserRepository userRepository;
	private final VoteResultRepository voteResultRepository;

	@Autowired
	public RestaurantRestController(RestaurantRepository restaurantRepository, 
			VoteRepository voteRepository, UserRepository userRepository,
			VoteResultRepository voteResultRepository) {
		
		this.restaurantRepository = restaurantRepository;
		this.voteRepository = voteRepository;
		this.userRepository = userRepository;
		this.voteResultRepository = voteResultRepository;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Restaurant> readAll() {
		return restaurantRepository.findAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{restaurantId}")
	public Restaurant readOne(@Valid @PathVariable Long restaurantId) {
		return restaurantRepository.findById(restaurantId).orElseThrow(ResourceNotFoundException::new);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public Restaurant add(@Valid @RequestBody Restaurant newRestaurant) {
		return restaurantRepository.save(new Restaurant(newRestaurant.getName()));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{restaurantId}/vote/{userId}")
	public Vote updateVote(@Valid @PathVariable Long restaurantId, @Valid @PathVariable Long userId) 
			throws Exception {

		Vote vote = null;

		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null)
		{
			throw new Exception("User not Found");
		}

		Restaurant restaurant = restaurantRepository.findOne(restaurantId);

		if (user != null) {

			if (restaurant == null) {
				
				throw new Exception("Restaurant not Found");
			}

			List<Vote> test = voteRepository.findByUser(user).orElse(null);

			if ((test != null)) {

				
				for(Vote v : test) 
				{
					if (v.getRestaurant().getId() == restaurant.getId()) {
					    return v;
				    }
				}
				
				
			}

			if ((vote == null)) {
				vote = new Vote(user, restaurant);

				VoteResult voteResult = voteResultRepository.findByRestaurant(restaurant).orElse(null);

				if (voteResult == null) {
					voteResult = new VoteResult(restaurant, 1L);
				} else {
					voteResult.setCounter(voteResult.getCounter() + 1);
				}

				voteResultRepository.save(voteResult);

			} else {
				
				vote = new Vote(user, restaurant);

				VoteResult voteResult = voteResultRepository.findByRestaurant(restaurant).orElse(null);

				if (voteResult == null) {
					voteResult = new VoteResult(restaurant, 1L);
				} else {
					voteResult.setCounter(voteResult.getCounter() + 1);
				}

				voteResultRepository.save(voteResult);
			}

			voteRepository.save(vote);

		}

		return vote;

	}

	

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/{restaurantId}")
	public Restaurant update(@Valid @PathVariable Long restaurantId, @Valid @RequestBody Restaurant newRestaurant) {
		return restaurantRepository.findById(restaurantId).map(restaurant -> {
			restaurant.setName(newRestaurant.getName());
			restaurantRepository.save(restaurant);
			return restaurant;
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{restaurantId}")
	public ResponseEntity<?> del(@Valid @PathVariable Long restaurantId) {

		return restaurantRepository.findById(restaurantId).map(restaurant -> {
			restaurantRepository.delete(restaurant);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	



	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/votes")
	public ResponseEntity<?> votesDel() {
		voteRepository.deleteAll();
		voteResultRepository.deleteAll();
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/votes")
	public Collection<Vote> votes() {
		return voteRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/votes/results")
	public Collection<VoteResult> votesResult() {
		return voteResultRepository.findAll();
	}

}
