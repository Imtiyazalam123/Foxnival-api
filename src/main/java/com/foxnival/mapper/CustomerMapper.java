package com.foxnival.mapper;

import com.foxnival.dto.CustomerDto;
import com.foxnival.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE  = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "subscriberId", target = "subscriber.id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mapping(source = "subscriber.id", target = "subscriberId")
    CustomerDto customerToCustomerDto(Customer customer);

    List<CustomerDto> customersToCustomerDtos(List<Customer> customers);
}
