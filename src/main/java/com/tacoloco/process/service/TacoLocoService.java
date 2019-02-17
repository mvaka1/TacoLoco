package com.tacoloco.process.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.tacoloco.process.model.TacoOrders;
import com.tacoloco.process.utility.MenuItemsEnum.TacoMenu;
/**
 * 
 * @author mounikareddyvaka
 *
 */

@Service
public class TacoLocoService {
	/**
	 * For the given order name and quantity, roll up cost is calculated, 
	 * if the total order is more than 4, 20% discount is applied on total calculated cost.
	 * @param order {@link TacoOrders}
	 * @return total order cost
	 */
	public double calculateTotal(List<TacoOrders> order) {
		double total = 0; 
		int ordercount =0 ;
		for (TacoOrders tacoOrder : order) {
			total =  total + calculateOrderRollup(tacoOrder);
			ordercount = ordercount + tacoOrder.getQuantity();
		}
		if(ordercount >= 4) {
			total = (total * 80)/100;
		}
		return total;
	}
    /**
     * gets the price of the valid menu item times the quantity respectively
     * @param tacoOrder
     * @return
     */
	private double calculateOrderRollup(TacoOrders tacoOrder) {
		double price = TacoMenu.getItemPrice(tacoOrder.getOrderName());
	if( (price > 0 && tacoOrder.getQuantity() > 0)) {
		return Double.valueOf(tacoOrder.getQuantity()) * price;
	}
	return 0;
	}

}
