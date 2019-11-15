package com.foodscribe.service;

import java.util.List;


import com.foodscribe.domain.MenuItem;
import com.foodscribe.domain.ShoppingCart;

public interface MenuService {
	
	List<MenuItem> findByRestaurantId(Long id);

	List<MenuItem> findByShoppingCart(ShoppingCart shoppingCart);
	
//	List<CartItem> findByOrder(Order order);
	
	MenuItem updateItem(MenuItem menuItem);
	
	void removeCartItem(MenuItem menuItem);
	
	MenuItem findById(Long id);
	
	MenuItem save(MenuItem menuItem);
}
