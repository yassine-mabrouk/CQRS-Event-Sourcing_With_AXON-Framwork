package org.sid.query.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.coreapi.query.GetAllCustomer;
import org.sid.coreapi.query.GetCustomerById;
import org.sid.query.entities.Customer;
import org.sid.query.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/query/cutomers")
public class CustomerQueryController {
    QueryGateway queryGateway;
    CustomerRepository CustomerRepo;

  @GetMapping
    public CompletableFuture <List<Customer>> getCustomers(){

       return  queryGateway.query(new GetAllCustomer(),ResponseTypes.multipleInstancesOf(Customer.class) );
  }
    @GetMapping(path = "/{id}")
    public  CompletableFuture<Customer> getCutomersById(@PathVariable String id){
      return queryGateway.query(new GetCustomerById(id), ResponseTypes.instanceOf(Customer.class));
    }
    @ExceptionHandler
    public ResponseEntity<String>  error(Exception ex){
      ResponseEntity<String> error =new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       return error;
  }

}
