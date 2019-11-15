package com.foodscribe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.User;



public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	List<User> findAll();
	User findByUsername(String username);
}
