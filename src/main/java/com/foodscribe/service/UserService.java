package com.foodscribe.service;

import java.util.Set;

import com.foodscribe.domain.User;
import com.foodscribe.domain.UserShipping;



public interface UserService {
	User createUser(User user);

	User findByUsername(String username);

	User findByEmail(String userEmail);
	
	User save(User user);
	
	User findById(Long userId);

	void updateUserShipping(UserShipping shipping, User user);
	
	void setUserDefaultShipping(long userShippingId, User user);
}
