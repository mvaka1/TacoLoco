package com.tacoloco.process.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class TacoOrders implements Serializable {
	
	private static final long serialVersionUID = 4594978961131139818L;
	@NotBlank
	private String orderName;
	@Min(1)
	private int quantity;
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
