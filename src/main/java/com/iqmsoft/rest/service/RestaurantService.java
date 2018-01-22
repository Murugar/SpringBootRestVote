package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.repository.RestaurantRepository;

import com.iqmsoft.rest.entity.*;

public class RestaurantService {

	@Autowired
	RestaurantRepository test;
	
	
	@Transactional(readOnly = true)
	public List<Restaurant> getAllToDo() {
		return test.findAll();
	}
	
}
