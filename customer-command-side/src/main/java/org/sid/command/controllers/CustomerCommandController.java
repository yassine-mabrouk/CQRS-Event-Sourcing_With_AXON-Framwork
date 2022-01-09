package org.sid.command.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.sid.coreapi.commands.CreateCustomerCommand;
import org.sid.coreapi.commands.CreatedCustomerEvent;
import org.sid.coreapi.commands.UpdateCustomerCommand;
import org.sid.coreapi.dtos.CustomerRequestDto;
import org.sid.coreapi.dtos.CustomerUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/command/customers")
public class CustomerCommandController {
    private CommandGateway commandGateway ;
    private EventStore eventStore ;

    @PostMapping(path = "/create")
    public  CompletableFuture<String> createCustomer (@RequestBody CustomerRequestDto request){
        CompletableFuture<String> response = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail()
        ));
         return response;
    }
    @PutMapping(path="/update")
    public CompletableFuture<String> updateCustomer(@RequestBody CustomerUpdateRequestDto request){
        CompletableFuture<String> response = commandGateway.send(new UpdateCustomerCommand(
                request.getCustomerId(),
                request.getName(),
                request.getEmail()
        ));
        return response;
    }

    @GetMapping(path="/eventStore/{id}")
    public Stream eventStore (@PathVariable String id ){
        return  eventStore.readEvents(id).asStream();
    }

}
