package com.grocerymart.constants;

public class Constants {
	
	public enum Role {
		ADMIN("ADMIN"),
		USER("USER");
		
		public String value;
		
		private Role(String value) {
			this.value=value;
		}
		
		public String getValue() {
			return this.value;
		}

	}
	
	public static final String ITEM_NOT_FOUND = "Item with specified id not found!";
	public static final String UPDATE_ITEM_FIELD = "Updating the Items fields";
	public static final String OUT_OF_STOCK = "Item Out of Stock";
	public static final String REMOVE_ITEM = "Item with id {} marked as out of stock ";

}
