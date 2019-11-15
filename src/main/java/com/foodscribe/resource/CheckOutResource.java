package com.foodscribe.resource;


import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.DeliveryAddress;
import com.foodscribe.domain.Order;
import com.foodscribe.domain.Payment;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;
import com.foodscribe.service.CartItemService;
import com.foodscribe.service.OrderService;
import com.foodscribe.service.ShoppingCartService;
import com.foodscribe.service.UserService;
import com.foodscribe.utility.MailConstructor;
@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/checkout")
public class CheckOutResource {
	private Order order = new Order();
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@RequestMapping(value = "/checkout", method=RequestMethod.POST)
	public Order checkoutPost(
				@RequestBody HashMap<String, Object> mapper
			){
		ObjectMapper om = new ObjectMapper();
		
		DeliveryAddress deliveryAddress = om.convertValue(mapper.get("deliveryAddress"), DeliveryAddress.class);
		Payment payment = om.convertValue(mapper.get("payment"), Payment.class);
		Integer userid =  (Integer) mapper.get("userid");
		
		ShoppingCart shoppingCart = userService.findById(new Long(userid)).getShoppingCart();
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		User user = userService.findById(new Long(userid));
		Order order = orderService.createOrder(shoppingCart, deliveryAddress, payment, user);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		this.order = order;
		
		return order;
		
	}

}
