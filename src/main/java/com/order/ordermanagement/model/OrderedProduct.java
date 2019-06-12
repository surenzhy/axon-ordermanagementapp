package com.order.ordermanagement.model;

public class OrderedProduct {

	private final String orderId;
	private final String product;
	private OrderStatus orderStatus;

	public OrderedProduct(String orderId, String product) {
		this.orderId = orderId;
		this.product = product;
		orderStatus = OrderStatus.PLACED;
	}

	public void setOrderConfirmed() {
		this.orderStatus = OrderStatus.CONFIRMED;
	}

	public void setOrderShipped() {
		this.orderStatus = OrderStatus.SHIPPED;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
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
		OrderedProduct other = (OrderedProduct) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}