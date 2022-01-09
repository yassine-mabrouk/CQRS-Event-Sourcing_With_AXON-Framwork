package org.sid.command.aggregate;


import ch.qos.logback.core.spi.LifeCycle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.coreapi.commands.CreateCustomerCommand;
import org.sid.coreapi.commands.CreatedCustomerEvent;
import org.sid.coreapi.commands.UpdateCustomerCommand;
import org.sid.coreapi.commands.UpdatedCustomerEvent;

@Aggregate
@Slf4j
public class CustomerAggregate {
   // agregate idetifier
    @AggregateIdentifier
    private  String customerId;
    private String name;
    private String email;

    public CustomerAggregate() {
     // obligatoire
    }
    // fonction de decision  cote metier
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        // implimentre cote metier apres envopye une commande
        log.info("====================");
        log.info("CreateCustomerCommand reseved ");
        AggregateLifecycle.apply(new CreatedCustomerEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }
    // fonction evolution
    @EventSourcingHandler
    public void on (CreatedCustomerEvent event){
      log.info("====================");
      log.info("CreatedCustomerEvent reseved ");
      customerId=event.getId();
         name= event.getName();
       email= event.getEmail();
    }
   // ==============Update ==============
    @CommandHandler
    public void on (UpdateCustomerCommand command){
        log.info("====================");
        log.info("UpdateCustomerCommand received  ");
        AggregateLifecycle.apply(new UpdatedCustomerEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }
    @EventSourcingHandler
    public void on (UpdatedCustomerEvent event){
        log.info("====================");
        log.info("UpdatedCustomerEvent received  ");
        customerId= event.getId();
        name= event.getName();
        email= event.getEmail();
    }

}
