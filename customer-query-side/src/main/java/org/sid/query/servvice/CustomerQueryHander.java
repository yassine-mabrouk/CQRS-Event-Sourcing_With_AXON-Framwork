package org.sid.query.servvice;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.coreapi.query.GetAllCustomer;
import org.sid.coreapi.query.GetCustomerById;
import org.sid.query.entities.Customer;
import org.sid.query.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerQueryHander {
    CustomerRepository customerRepo;

    @QueryHandler
    public List<Customer> on (GetAllCustomer query){
        return customerRepo.findAll();
    }
    @QueryHandler
    public Customer on (GetCustomerById query){
        return customerRepo.findById(query.getId())
                .orElseThrow(()-> new RuntimeException("cannot find customer"));
    }

}
