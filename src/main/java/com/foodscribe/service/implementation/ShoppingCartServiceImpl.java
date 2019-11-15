package com.foodscribe.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.repository.ShoppingCartRepository;
import com.foodscribe.service.CartItemService;
import com.foodscribe.service.ShoppingCartService;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		float cartTotal = 0;
		List<CartItem> cartItems = shoppingCart.getCartItemList();
		
		for(CartItem cartItem : cartItems) {
			
				cartItemService.updateItem(cartItem);
				
				cartTotal = cartTotal + cartItem.getSubtotal();
			
		}
		shoppingCart.setGrandTotal(cartTotal);
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
	}

	@Override
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for( CartItem cartItem: cartItemList){
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		
		shoppingCart.setGrandTotal(0);
		shoppingCartRepository.save(shoppingCart);
		
	}

}
