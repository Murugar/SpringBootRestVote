package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.repository.UserRepository;
import com.iqmsoft.rest.repository.VoteRepository;

public class VoteService {

	
	@Autowired
	VoteRepository test;
	
	
	@Transactional(readOnly = true)
	public List<Vote> getAllToDo() {
		return test.findAll();
	}
}
