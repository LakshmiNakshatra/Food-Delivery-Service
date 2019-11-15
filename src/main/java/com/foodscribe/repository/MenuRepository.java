 package com.foodscribe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.MenuItem;

public interface MenuRepository extends CrudRepository<MenuItem, Long>{
	
	List<MenuItem> findByRestaurantIdOrderByItemCategory(Long id);

}
