package com.iqmsoft.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.rest.entity.Restaurant;
import com.iqmsoft.rest.entity.VoteResult;

import java.util.Optional;

public interface VoteResultRepository extends JpaRepository<VoteResult, Long> {
    Optional<VoteResult> findByRestaurant(Restaurant restaurant);
}
