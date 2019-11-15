package com.foodscribe.service;

import com.foodscribe.domain.UserPayment;

public interface UserPaymentService{
	UserPayment findById(Long id);
	
	void removeById(Long id);
	
	
}
