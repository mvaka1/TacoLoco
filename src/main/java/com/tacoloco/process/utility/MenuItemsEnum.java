package com.tacoloco.process.utility;

public class MenuItemsEnum {
	/**
	 * 
	 * @author mounikareddyvaka
	 *
	 */
	public enum TacoMenu 
	{
		VEGGIE_TACO("Veggie Taco", 2.50), CHICKEN_TACO("Chicken Taco", 3.00), BEEF_TACO("Beef Taco", 3.00), CHORIZO_TACO("Chorizo Taco", 3.50);
	
		private String itemName; // menu item name
		private Double price;    // cost of the item
		
	private TacoMenu(String itemName, Double price) {
		this.itemName = itemName;
		this.price = price;	
	}
	
	public String getItemName() {
		return itemName;
	}	

	public Double getPrice() {
		return price;
	}
 /**
   * gets the price of the given menu item name.
   * @param orderedItemName
   * @return price of the menu item
  */
	public static Double getItemPrice(String orderedItemName){
		for (TacoMenu taconame : values()) {
			if(orderedItemName.contentEquals(taconame.getItemName())) {
				return taconame.getPrice();
			}
		}
		return (double) 0;
	}
  }
}
