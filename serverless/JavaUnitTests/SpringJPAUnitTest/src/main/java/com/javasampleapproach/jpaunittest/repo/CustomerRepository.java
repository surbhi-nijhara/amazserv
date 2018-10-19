package com.javasampleapproach.jpaunittest.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.jpaunittest.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
}
