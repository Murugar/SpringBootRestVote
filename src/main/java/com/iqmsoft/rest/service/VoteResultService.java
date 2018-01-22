package com.iqmsoft.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.rest.entity.Vote;
import com.iqmsoft.rest.entity.VoteResult;
import com.iqmsoft.rest.repository.VoteRepository;
import com.iqmsoft.rest.repository.VoteResultRepository;

public class VoteResultService {

	
	@Autowired
	VoteResultRepository test;
	
	
	@Transactional(readOnly = true)
	public List<VoteResult> getAllToDo() {
		return test.findAll();
	}
}
