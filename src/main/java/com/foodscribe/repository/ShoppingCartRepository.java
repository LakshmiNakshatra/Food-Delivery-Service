package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.ShoppingCart;


public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
