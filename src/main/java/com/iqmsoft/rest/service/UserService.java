package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.controller.RestaurantRestController;
import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.Role;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.repository.RestaurantRepository;
import com.iqmsoft.rest.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {

	
	@Autowired
	UserRepository test;
	
	
	@Transactional(readOnly = true)
	public List<User> getAll() {
		log.info("User Service Getting All");
		return test.findAll();
	}
	
	
	public User reg(User user)
	{
		log.info("Reg User Service Getting All");
		return test.save(new User(user.getLogin(), user.getPassword()));
	}

}
