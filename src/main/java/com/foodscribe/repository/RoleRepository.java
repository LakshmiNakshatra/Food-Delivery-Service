package com.foodscribe.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodscribe.domain.security.Role;


public interface RoleRepository extends CrudRepository<Role, Long>{

}
