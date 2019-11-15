package com.foodscribe.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.Restaurant;
import com.foodscribe.repository.RestaurantRepository;
import com.foodscribe.service.RestaurantService;


@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> blurrySearch(String keyword) {
		List<Restaurant> restaurantList = restaurantRepository.findByRestaurantNameContaining(keyword);
		
		List<Restaurant> activeRestaurantList = new ArrayList<>();
		
		for (Restaurant restaurant : restaurantList) {
			if(restaurant.isActive()) {
				activeRestaurantList.add(restaurant);
			}
		}
		
		return activeRestaurantList;
	}

	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
		List<Restaurant> activeRestaurantList = new ArrayList<Restaurant>();
		
		for(Restaurant restaurant: restaurantList){
			if(restaurant.isActive() == true){
				activeRestaurantList.add(restaurant);
			}
		}
		return activeRestaurantList;
	}

	@Override
	public List<Restaurant> findByZip(String zip) {
		return restaurantRepository.findByZip(zip);
	}

	@Override
	public Restaurant findOne(Long id) {
		
		return restaurantRepository.findOne(id);
	}
}
