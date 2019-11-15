package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.MenuToCartItem;


public interface MenuToCartItemRepository extends CrudRepository<MenuToCartItem, Long>{
	
	void deleteByCartItem(CartItem cartItem);

}
