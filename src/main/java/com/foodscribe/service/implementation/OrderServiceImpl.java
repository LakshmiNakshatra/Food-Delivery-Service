package com.foodscribe.service.implementation;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.MenuItem;
import com.foodscribe.domain.Order;
import com.foodscribe.domain.Payment;
import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.DeliveryAddress;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;
import com.foodscribe.repository.OrderRepository;
import com.foodscribe.repository.PaymentRepository;
import com.foodscribe.repository.ShippingAddressRepository;
import com.foodscribe.service.CartItemService;
import com.foodscribe.service.MenuService;
import com.foodscribe.service.OrderService;
import com.foodscribe.service.RestaurantService;
import com.foodscribe.utility.MailConstructor;



@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Override
	public synchronized  Order createOrder(
			ShoppingCart shoppingCart,
			DeliveryAddress deliveryAddress,
			Payment payment,
			User user
			){
		Order order = new Order();
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setDeliveryAddress(deliveryAddress);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			cartItem.setOrder(order);
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		deliveryAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	@Override
	public Order createOrder(ShoppingCart shoppingCart, DeliveryAddress deliveryAddress, Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findByOrderId(Long orderid) {
		
		return orderRepository.findOne(orderid);
	}


	

}
