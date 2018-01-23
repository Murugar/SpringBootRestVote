package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.repository.RestaurantRepository;

import lombok.extern.slf4j.Slf4j;

import com.iqmsoft.rest.controller.RestaurantRestController;
import com.iqmsoft.rest.entity.*;

@Slf4j
@Service
@Transactional
public class RestaurantService {

	@Autowired
	RestaurantRepository test;
	
	
	@Transactional(readOnly = true)
	public List<Restaurant> getAll() {
		log.info("Restaurant Service Getting All");
		return test.findAll();
	}
	
}
