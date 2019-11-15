package com.foodscribe.service;

import java.util.List;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.MenuItem;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;


public interface CartItemService {
	
	CartItem addMenuItemToCartItem(MenuItem item, User user, int qty);
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
//	List<CartItem> findByOrder(Order order);
	
	CartItem updateItem(CartItem cartItem);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem findById(Long id);
	
	CartItem save(CartItem cartItem);
}
