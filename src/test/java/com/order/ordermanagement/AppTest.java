package com.order.ordermanagement;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.order.ordermanagement.command.ConfirmOrderCommand;
import com.order.ordermanagement.command.PlaceOrderCommand;
import com.order.ordermanagement.command.ShipOrderCommand;
import com.order.ordermanagement.event.OrderConfirmedEvent;
import com.order.ordermanagement.event.OrderPlacedEvent;
import com.order.ordermanagement.event.OrderShippedEvent;
import com.order.ordermanagement.model.OrderAggregate;

@RunWith(SpringRunner.class)
public class AppTest {
	
	private FixtureConfiguration<OrderAggregate> fixture;
	 
	@Before
	public void setUp() {
	    fixture = new AggregateTestFixture(OrderAggregate.class);
	}
	
	@Test
	public void when_place_order_command_then_order_placed_event() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.givenNoPriorActivity()
		  .when(new PlaceOrderCommand(orderId, product))
		  .expectEvents(new OrderPlacedEvent(orderId, product));
	}
	
	@Test
	public void when_place_order_event_and_expect_order_ship_event_and_expect_exception() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.given(new OrderPlacedEvent(orderId, product))
		  .when(new ShipOrderCommand(orderId))
		  .expectException(IllegalStateException.class);
	}
	
	@Test
	public void when_place_order_event_and_order_confirmed_event_and_expect_order_shipped_event() {
		String orderId = UUID.randomUUID().toString();
		String product = "Deluxe Chair";
		fixture.given(new  OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
		  .when(new ShipOrderCommand(orderId))
		  .expectEvents(new OrderShippedEvent(orderId));
	}
}