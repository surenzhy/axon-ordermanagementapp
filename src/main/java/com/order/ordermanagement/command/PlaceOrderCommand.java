package com.order.ordermanagement.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PlaceOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;
	private final String product;

	public PlaceOrderCommand(String orderId, String product) {
		super();
		this.orderId = orderId;
		this.product = product;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getProduct() {
		return product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceOrderCommand other = (PlaceOrderCommand) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
