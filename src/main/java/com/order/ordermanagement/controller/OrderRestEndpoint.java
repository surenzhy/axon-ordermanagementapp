package com.order.ordermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.command.ConfirmOrderCommand;
import com.order.ordermanagement.command.PlaceOrderCommand;
import com.order.ordermanagement.command.ShipOrderCommand;
import com.order.ordermanagement.model.OrderedProduct;
import com.order.ordermanagement.query.FindAllOrderedProductsQuery;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
	
    @Autowired
    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
		super();
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

    @PostMapping("/ship-order")
    public void shipOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(orderId));
        commandGateway.send(new ShipOrderCommand(orderId));
    }
    
    @GetMapping("/all-orders")
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), 
          ResponseTypes.multipleInstancesOf(OrderedProduct.class)).join();
    }
}
