package com.iqmsoft.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.Role;
import com.iqmsoft.rest.entity.User;

import com.iqmsoft.rest.repository.RestaurantRepository;
import com.iqmsoft.rest.repository.UserRepository;

import java.util.*;

@SpringBootApplication
public class MainRest {

	public static void main(String[] args) {
		SpringApplication.run(MainRest.class, args);
	}

	
	@Bean
	CommandLineRunner initUser(UserRepository userRepository) {
		List<String[]> list = new ArrayList<>();
		
		list.add(new String[]{"admin", "admin", "ROLE_ADMIN"});
		list.add(new String[]{"user", "user", "ROLE_USER"});

		return (args) -> list
				.forEach(
						entry -> userRepository.save(new User(
								entry[0],
								entry[1],
								Role.valueOf(entry[2])
						))
				);
	}

	@Bean
	CommandLineRunner initRestaurant(RestaurantRepository restaurantRepository) {
		return (args) -> Arrays.asList(
				"restaurant1, restaurant2, restaurant3, restaurant4".split(", "))
				.forEach(
						name -> restaurantRepository.save(new Restaurant(name))
				);
	}

	
}
