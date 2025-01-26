package com.foxnival.service.customers;

import com.foxnival.dto.CustomerDto;
import com.foxnival.entity.Customer;
import com.foxnival.entity.Subscriber;
import com.foxnival.exception.InvalidRequestException;
import com.foxnival.mapper.CustomerMapper;
import com.foxnival.repository.CustomerRepository;
import com.foxnival.repository.SubscribeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Override
    public CustomerDto addCustomerDetails(CustomerDto customerDto) {
        CustomerDto returnCustomerDto = new CustomerDto();
        Optional<Subscriber> optionalSubscriber = subscribeRepository.findById(customerDto.getSubscriberId());
        if (!optionalSubscriber.isPresent()) {
            log.error("Subscriber not found for provided subscriber id : " + customerDto.getSubscriberId());
            throw new InvalidRequestException("Subscriber not found for provided subscriber id.");
        }
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        returnCustomerDto = customerMapper.customerToCustomerDto(savedCustomer);
        return returnCustomerDto;
    }

    @Override
    public List<CustomerDto> fetchAllCustomers(Long subscriberId) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        Optional<Subscriber> optionalSubscriber = subscribeRepository.findById(subscriberId);
        if (!optionalSubscriber.isPresent()) {
            log.error("Subscriber not found for provided subscriber id : " + subscriberId);
            throw new InvalidRequestException("Subscriber not found for provided subscriber id.");
        }
        List<Customer> fetchedAllCustomers = customerRepository.findAllBySubscriberId(subscriberId);
        customerDtos = customerMapper.customersToCustomerDtos(fetchedAllCustomers);
        return customerDtos;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, Long subscriberId, CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = new CustomerDto();
        Optional<Customer> optionalCustomer = customerRepository.findByIdAndSubscriberId(customerId, subscriberId);
        if (!optionalCustomer.isPresent()) {
            log.error("Customer not found for provided customer id : " + customerId);
            throw new InvalidRequestException("Customer not found for provided customer.");
        }
        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.getName());
        customer.setSource(customerDto.getSource());
        customer.setPurpose(customerDto.getPurpose());
        customer.setEmail(customerDto.getEmail());
        customer.setComments(customerDto.getComments());
        customer.setMobileNo(customerDto.getMobileNo());

        Customer save = customerRepository.save(customer);
        updatedCustomerDto = customerMapper.customerToCustomerDto(save);
        return updatedCustomerDto;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            log.error("Customer not found for provided customer id : " + customerId);
            throw new InvalidRequestException("Customer not found for provided customer.");
        }
        customerRepository.deleteById(customerId);
    }
}
