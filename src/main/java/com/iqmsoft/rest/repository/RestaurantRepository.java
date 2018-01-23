package com.iqmsoft.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.rest.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long restaurantId);
    Optional<List<Restaurant>> findByType(String type);
    Optional<List<Restaurant>> countByType(String type);
   
    List<Restaurant> findByTypeOrderByCounterDesc(String type);
    List<Restaurant> findByTypeOrderByNameDesc(String type);
    
    List<Restaurant> findAllByOrderByNameDesc();
    List<Restaurant> findAllByOrderByCounterDesc();
    
    Page<Restaurant> findAll(Pageable pageable);
 
}
