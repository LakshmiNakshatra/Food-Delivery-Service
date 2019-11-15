package com.foodscribe.service;

import com.foodscribe.domain.UserShipping;

public interface UserShippingService {

	UserShipping findById(Long id);
	
	void removeById(Long id);
}
