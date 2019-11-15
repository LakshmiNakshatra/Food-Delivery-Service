package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.DeliveryAddress;


public interface ShippingAddressRepository extends CrudRepository<DeliveryAddress, Long> {
	
}
