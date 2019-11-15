package com.foodscribe.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodscribe.domain.ShoppingCart;
import com.foodscribe.domain.User;
import com.foodscribe.domain.UserShipping;
import com.foodscribe.repository.UserRepository;
import com.foodscribe.repository.UserShippingRepository;
import com.foodscribe.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	
	@Transactional
	public User createUser(User user) {
		User localUser = userRepository.findByEmail(user.getEmail());
		if(null != localUser){
			LOG.info("User {} already exists", user.getEmail());
		} 
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		
		user.setShoppingCart(shoppingCart);
		user.setUserShippingList(new ArrayList<UserShipping>());
			
			localUser = userRepository.save(user);
		
		return localUser;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(userEmail);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findOne(userId);
	}

	/*@Override
	public void updateUserPaymentInfo(UserShipping userShipping, UserBilling billing, UserPayment payment, User user) {
		save(user);
		userPaymentRepository.save(payment);
		userBillingRepository.save(billing);
	}

	@Override
	public void updateUserBilling(UserBilling billing, UserPayment payment, User user) {
		payment.setUser(user);
		payment.setUserBilling(billing);
		payment.setDefaultPayment(true);
		billing.setUserPayment(payment);
		user.getUserPaymentList().add(payment);
		save(user);
		
	}
	
	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
		
		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
	}*/

	@Override
	public void updateUserShipping(UserShipping shipping, User user) {
		shipping.setUser(user);
		shipping.setUserShippingDefault(true);
		user.getUserShippingList().add(shipping);
		save(user);
	}

	@Override
	public void setUserDefaultShipping(long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
		
		for (UserShipping userShipping : userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
		
	}
	
}
