package com.foodscribe.service;

import com.foodscribe.domain.Order;
import com.foodscribe.domain.Payment;
import com.foodscribe.domain.DeliveryAddress;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;

public interface OrderService {
	
	Order createOrder(ShoppingCart shoppingCart, DeliveryAddress deliveryAddress,  Payment payment);

	Order createOrder(ShoppingCart shoppingCart, DeliveryAddress deliveryAddress,
			Payment payment, User user);
	
	Order findByOrderId(Long orderid);
}
