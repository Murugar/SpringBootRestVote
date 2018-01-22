package com.iqmsoft.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.rest.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long restaurantId);
}
