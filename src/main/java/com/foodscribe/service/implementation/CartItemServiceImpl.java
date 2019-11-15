package com.foodscribe.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.CartItem;
import com.foodscribe.domain.MenuItem;
import com.foodscribe.domain.MenuToCartItem;
import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;
import com.foodscribe.repository.CartItemRepository;
import com.foodscribe.repository.MenuToCartItemRepository;
import com.foodscribe.service.CartItemService;


@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private MenuToCartItemRepository menuToCartItemRepository;

	@Override
	public CartItem addMenuItemToCartItem(MenuItem menuItem, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for(CartItem cartItem: cartItemList){
			if(menuItem.getId() == cartItem.getMenuItem().getId()){
				cartItem.setQty(cartItem.getQty() + qty);
				cartItem.setSubtotal(menuItem.getItemPrice()*qty);
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setMenuItem(menuItem);
		cartItem.setQty(qty);
		cartItem.setSubtotal(menuItem.getItemPrice()*qty);
		cartItemRepository.save(cartItem);
		
		
		MenuToCartItem bookToCartItem = new MenuToCartItem();
		bookToCartItem.setCartItem(cartItem);
		bookToCartItem.setMenuItem(menuItem);
		menuToCartItemRepository.save(bookToCartItem);
		
		return cartItem;
	}

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

//	@Override
//	public List<CartItem> findByOrder(Order order) {
//		return cartItemRepository.findByOrder(order);
//	}

	@Override
	public CartItem updateItem(CartItem cartItem) {
		cartItem.setSubtotal(cartItem.getMenuItem().getItemPrice() * cartItem.getQty());
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}
	
	@Transactional
	@Override
	public void removeCartItem(CartItem cartItem) {
		menuToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	@Override
	public CartItem findById(Long id) {
		
		return cartItemRepository.findOne(id);
	}

	@Override
	public CartItem save(CartItem cartItem) {
		 
		return cartItemRepository.save(cartItem);
	}


}
