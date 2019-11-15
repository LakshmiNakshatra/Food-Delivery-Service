package com.foodscribe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.Order;
import com.foodscribe.domain.ShoppingCart;


@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByOrder(Order order);

}
