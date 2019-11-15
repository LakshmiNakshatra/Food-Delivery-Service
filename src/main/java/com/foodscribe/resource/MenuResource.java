package com.foodscribe.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.domain.MenuItem;
import com.foodscribe.service.MenuService;
@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/menu")
public class MenuResource {
	
	@Autowired
	private MenuService menuService;	
	
	@RequestMapping("/{id}")
	public List<MenuItem> getMenu(@PathVariable("id") Long id){
		List<MenuItem> menuForRest = menuService.findByRestaurantId(id);
		return menuForRest;
	}

}


