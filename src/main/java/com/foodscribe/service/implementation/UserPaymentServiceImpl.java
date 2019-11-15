package com.foodscribe.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.UserPayment;
import com.foodscribe.repository.UserPaymentRepository;
import com.foodscribe.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Override
	public UserPayment findById(Long id) {
		// TODO Auto-generated method stub
		return userPaymentRepository.findOne(id);
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userPaymentRepository.delete(id);
	}

}
