package com.iqmsoft.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.User;
import com.iqmsoft.rest.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<List<Vote>> findByUser(User user);
    Optional<List<Vote>> findByRestaurant(Restaurant res);
}
