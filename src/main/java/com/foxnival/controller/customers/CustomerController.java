package com.foxnival.controller.customers;

import com.foxnival.dto.CustomerDto;
import com.foxnival.service.customers.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Add customer details into customer table",
            description = "It will add the customer details into customer table.",
            responses = {
                    @ApiResponse(description = "Created.", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Invalid subscriber id.", responseCode = "400", content = @Content)
            }
    )
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> addCustomerDetails(@RequestBody CustomerDto customerDto) {

        return new ResponseEntity<>(customerService.addCustomerDetails(customerDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch all customers based subscriber",
            description = "It will retrieve all customers based on provided subscriber id.",
            responses = {
                    @ApiResponse(description = "Successful.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Subscriber id not found.", responseCode = "400", content = @Content)
            }
    )
    @GetMapping(path = "/subscribers/{subscriberId}")
    public ResponseEntity<List<CustomerDto>> getAllUsersBySubscriberId(@PathVariable(name = "subscriberId") Long subscriberId) {

        return new ResponseEntity<>(customerService.fetchAllCustomers(subscriberId), HttpStatus.OK);
    }

    @Operation(summary = "Update customer for provided customer id and subscriber id",
            description = "It will update the customer details for provided customer id and subscriber id",
            responses = {
                    @ApiResponse(description = "Updated.", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Customer id not found.", responseCode = "400", content = @Content)
            }
    )
    @PutMapping(path = "/{customerId}/subscribers/{subscriberId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "customerId") Long customerId,
                                                      @PathVariable(name = "subscriberId") Long subscriberId,
                                                      @RequestBody CustomerDto customerDto
    ) {
        return new ResponseEntity<>(customerService.updateCustomer(customerId, subscriberId, customerDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete customer by ID",
            description = "It will delete the customer based on the provided customer ID.",
            responses = {
                    @ApiResponse(description = "Deleted.", responseCode = "200", content = @Content),
                    @ApiResponse(description = "customer not found.", responseCode = "404", content = @Content)
            }
    )
    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
