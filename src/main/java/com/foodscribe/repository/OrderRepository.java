package com.foodscribe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.Order;
import com.foodscribe.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUser(User user);
}
