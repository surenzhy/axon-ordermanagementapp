package com.order.ordermanagement.model;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.order.ordermanagement.command.ConfirmOrderCommand;
import com.order.ordermanagement.command.PlaceOrderCommand;
import com.order.ordermanagement.command.ShipOrderCommand;
import com.order.ordermanagement.event.OrderConfirmedEvent;
import com.order.ordermanagement.event.OrderPlacedEvent;
import com.order.ordermanagement.event.OrderShippedEvent;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	private boolean orderConfirmed;

	
	@EventSourcingHandler
	public void on(OrderPlacedEvent event) {
		this.orderId = event.getOrderId();
		orderConfirmed = false;
	}

	@EventSourcingHandler
	public void on(OrderConfirmedEvent event) {
		orderConfirmed = true;
	}
	
	@CommandHandler
	public void handle(ConfirmOrderCommand command) {
		AggregateLifecycle.apply(new OrderConfirmedEvent(orderId));
	}

	@CommandHandler
	public void handle(ShipOrderCommand command) {
		if (!orderConfirmed) {
			throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
		}
		AggregateLifecycle.apply(new OrderShippedEvent(orderId));
	}

	@CommandHandler
	public OrderAggregate(PlaceOrderCommand command) {
		AggregateLifecycle.apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
	}
	
	protected OrderAggregate() {
	}
}