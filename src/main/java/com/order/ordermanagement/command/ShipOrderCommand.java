package com.order.ordermanagement.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

	@TargetAggregateIdentifier
	private final String orderId;

	public ShipOrderCommand(String orderId) {
		super();
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		ShipOrderCommand other = (ShipOrderCommand) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

	public String getOrderId() {
		return orderId;
	}

}