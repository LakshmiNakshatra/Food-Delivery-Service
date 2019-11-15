package com.foodscribe.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.domain.Order;
import com.foodscribe.domain.User;
import com.foodscribe.service.OrderService;
import com.foodscribe.service.UserService;

@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/order")
public class OrderResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired OrderService orderService;
	
	@RequestMapping("/orderList")
	public List<Order> getOrderList(@RequestHeader(value="userid") Long userid) {
		User user = userService.findById(userid);
		List<Order> orderList = user.getOrderList();
		
		return orderList;
	}
	
	@RequestMapping("/{orderid}")
	public Order getOrder(@PathVariable("orderid") String orderid) {
		Order order = orderService.findByOrderId(Long.parseLong(orderid));
		return order;
	}

}
