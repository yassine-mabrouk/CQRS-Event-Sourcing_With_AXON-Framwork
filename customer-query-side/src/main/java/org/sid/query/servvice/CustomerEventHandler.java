package org.sid.query.servvice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.coreapi.commands.CreatedCustomerEvent;
import org.sid.coreapi.commands.DeletedCustomerEvent;
import org.sid.coreapi.commands.UpdatedCustomerEvent;
import org.sid.query.entities.Customer;
import org.sid.query.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerEventHandler
{
    CustomerRepository customerRepo;
    @EventHandler
    public  void on (CreatedCustomerEvent event){
        log.info("======================");
        log.info("CreatedCustomerEvent received");
        Customer customer=new Customer();
        customer.setCustomerId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customerRepo.save(customer);
    }
    @EventSourcingHandler
    public void on (UpdatedCustomerEvent event){
        log.info("======================");
        log.info("UpdatedCustomerEvent received ");
      Customer customer=customerRepo.findById(event.getId()).get();
      customer.setName(event.getName());
      customer.setEmail(event.getEmail());
      customerRepo.save(customer);
    }
    // delete handeler
    @EventSourcingHandler
    public void on (DeletedCustomerEvent event){
        log.info("======================");
        log.info("UpdatedCustomerEvent received ");
        Customer customer=customerRepo.findById(event.getId()).get();
        customerRepo.delete(customer);
    }

}
