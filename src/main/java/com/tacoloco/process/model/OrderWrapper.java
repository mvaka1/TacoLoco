package com.tacoloco.process.model;

import java.io.Serializable;
import java.util.List;

public class OrderWrapper implements Serializable{
	
	private static final long serialVersionUID = 4218351622712013738L;
	
	List<TacoOrders> orders;

	public List<TacoOrders> getOrders() {
		return orders;
	}

	public void setOrders(List<TacoOrders> orders) {
		this.orders = orders;
	}

}
