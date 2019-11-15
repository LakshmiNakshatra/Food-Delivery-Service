package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.Payment;


public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
