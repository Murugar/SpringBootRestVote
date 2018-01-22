package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.repository.RestaurantRepository;
import com.iqmsoft.rest.repository.UserRepository;

public class UserService {

	
	@Autowired
	UserRepository test;
	
	
	@Transactional(readOnly = true)
	public List<User> getAllToDo() {
		return test.findAll();
	}
}
