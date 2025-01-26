package com.foxnival.repository;

import com.foxnival.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllBySubscriberId(Long subscriberId);
    
    Optional<Customer> findByIdAndSubscriberId(Long id, Long subscriberId);
}
