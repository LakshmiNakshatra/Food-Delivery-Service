package com.foodscribe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.Restaurant;

public interface RestaurantRepository  extends CrudRepository<Restaurant, Long>{

	List<Restaurant> findByRestaurantNameContaining(String keyword);
	
	List<Restaurant> findByZip(String zip);
	
	Restaurant findOne(Long id);
	 
}
