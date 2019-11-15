package com.foodscribe.service;

import java.util.List;

import com.foodscribe.domain.Restaurant;

public interface RestaurantService {

	List<Restaurant> findAll();
	
	List<Restaurant> findByZip(String zip);
	
	List<Restaurant> blurrySearch(String title);

	Restaurant findOne(Long id);
}
