package com.foxnival.service.customers;

import com.foxnival.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto addCustomerDetails(CustomerDto customerDto);

    List<CustomerDto> fetchAllCustomers(Long subscriberId);

    CustomerDto updateCustomer(Long customerId, Long subscriberId, CustomerDto customerDto);

    void deleteCustomer(Long customerId);
}
