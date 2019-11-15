package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.FunFact;

public interface FunFactRepository extends CrudRepository<FunFact, Long>{

}
