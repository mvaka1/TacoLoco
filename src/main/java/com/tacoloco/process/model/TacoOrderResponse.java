package com.tacoloco.process.model;

import java.io.Serializable;
import java.util.List;

public class TacoOrderResponse implements Serializable {
	
	private static final long serialVersionUID = -920383395551782205L;
	
	
	private double orderTotalCost; //total cost of all items ordered
	
	public double getOrderTotalCost() {
		return orderTotalCost;
	}
	public void setOrderTotalCost(double orderTotalCost) {
		this.orderTotalCost = orderTotalCost;
	}
	

	

}
