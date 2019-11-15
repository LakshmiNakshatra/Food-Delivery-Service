package com.foodscribe.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.domain.Restaurant;
import com.foodscribe.service.RestaurantService;


@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/restaurant")
public class RestaurantResource {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping("/restaurantList")
	public List<Restaurant> getRestaurantList(){
		return restaurantService.findAll();
	}
	
	@RequestMapping("/{zip}")
	public List<Restaurant> getRestaurant(@PathVariable("zip") String zip){
		List<Restaurant> restaurantList = restaurantService.findByZip(zip);
		return restaurantList;
	}
	
	@RequestMapping("/restid/{id}")
	public Restaurant getRestaurantById(@PathVariable("id") Long id){
		Restaurant restaurantList = restaurantService.findOne(id);
		return restaurantList;
	}
	
	@RequestMapping(value="/searchRestaurant", method=RequestMethod.POST)
	public List<Restaurant> searchRestaurant(@RequestBody String keyword){
		List<Restaurant> restaurantList = restaurantService.blurrySearch(keyword);
		return restaurantList;
	}

}
