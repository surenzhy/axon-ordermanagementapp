package com.order.ordermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.event.OrderConfirmedEvent;
import com.order.ordermanagement.event.OrderPlacedEvent;
import com.order.ordermanagement.event.OrderShippedEvent;
import com.order.ordermanagement.model.OrderedProduct;
import com.order.ordermanagement.query.FindAllOrderedProductsQuery;

@Service
public class OrderedProductsEventHandler {

	private final Map<String, OrderedProduct> orderedProducts = new HashMap<String, OrderedProduct>();

	@EventHandler
	public void on(OrderPlacedEvent event) {
		String orderId = event.getOrderId();
		orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
	}

	@EventHandler
	public void on(OrderConfirmedEvent event) {

	}

	@EventHandler
	public void on(OrderShippedEvent event) {

	}

	@QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }	

}