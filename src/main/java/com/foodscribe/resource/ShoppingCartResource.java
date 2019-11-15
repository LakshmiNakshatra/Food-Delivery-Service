package com.foodscribe.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.FunFact;
import com.foodscribe.domain.MenuItem;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;
import com.foodscribe.repository.FunFactRepository;
import com.foodscribe.service.CartItemService;
import com.foodscribe.service.MenuService;
import com.foodscribe.service.ShoppingCartService;
import com.foodscribe.service.UserService;


@CrossOrigin(origins = "http://foodscribe.herokuapp.com", maxAge=3600)
@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	 
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private FunFactRepository factRepository;
	
	@RequestMapping("/add")
	public ResponseEntity addItem(@RequestBody HashMap<String, String> mapper) {
		
		String menuItemId = mapper.get("menuItemId");
		String qty = mapper.get("qty");
		String userid = mapper.get("userid");
		
		User user = userService.findById(Long.parseLong(userid));
		MenuItem menuItem = menuService.findById(Long.parseLong(menuItemId));
		
		CartItem cartItem = cartItemService.addMenuItemToCartItem(menuItem, user, Integer.parseInt(qty));
		
		return new ResponseEntity(" Menu Item added successfully!", HttpStatus.OK); 
	}
	
	@RequestMapping("/getCartItemList")
	public List<CartItem> getCartItemList(@RequestHeader(value="userid") Long userid){
		User user = userService.findById(userid);
		ShoppingCart cart = user.getShoppingCart();
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(cart);
		
		cartService.updateShoppingCart(cart);
		
		return cartItems;
	}
	
	@RequestMapping("/getShoppingCart")
	public ShoppingCart getShoppingCart(@RequestHeader(value="userid") Long userid){
		User user = userService.findById(userid);
		
		ShoppingCart cart  = user.getShoppingCart();
		
		cartService.updateShoppingCart(cart);
		
		return cart;
	}
	
	@RequestMapping("/removeItem")
	public ResponseEntity removeItem(@RequestBody String id){
		cartItemService.removeCartItem(cartItemService.findById(Long.parseLong(id)));
		
		return new ResponseEntity("Menu Item  deleted successfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/updateCartItem")
	public ResponseEntity updateCartItem(@RequestBody HashMap<String, String> mapper){
		String cartItemId = mapper.get("cartItemId");
		String qty = mapper.get("qty");
		
		CartItem cartItem  = cartItemService.findById(Long.parseLong(cartItemId));
		cartItem.setQty(Integer.parseInt(qty));
		
		cartItemService.updateItem(cartItem);
		
		return new ResponseEntity("Menu Item  updated successfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/funfacts")
	public String getFunFacts(){
		FunFact fact = new FunFact();
		Random r = new Random();
		int low = 1;
		int high = 20;
		return factRepository.findOne(new Long(r.nextInt(high-low) + low)).getDescription();
	}
}
