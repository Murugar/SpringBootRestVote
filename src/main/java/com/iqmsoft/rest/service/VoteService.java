package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.controller.RestaurantRestController;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.repository.UserRepository;
import com.iqmsoft.rest.repository.VoteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class VoteService {

	
	@Autowired
	VoteRepository test;
	
	
	@Transactional(readOnly = true)
	public List<Vote> getAll() {
		log.info("Vote Getting All");
		return test.findAll();
	}
}
