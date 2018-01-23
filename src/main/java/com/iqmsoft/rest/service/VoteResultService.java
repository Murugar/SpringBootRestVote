package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.controller.RestaurantRestController;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.entity.VoteResult;
import com.iqmsoft.rest.repository.VoteRepository;
import com.iqmsoft.rest.repository.VoteResultRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class VoteResultService {

	
	@Autowired
	VoteResultRepository test;
	
	
	@Transactional(readOnly = true)
	public List<VoteResult> getAll() {
		log.info("VoteResult Getting All");
		return test.findAll();
	}
}
