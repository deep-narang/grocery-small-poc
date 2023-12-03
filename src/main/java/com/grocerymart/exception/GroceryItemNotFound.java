package com.grocerymart.exception;

public class GroceryItemNotFound extends RuntimeException {
	public GroceryItemNotFound(String message) {
		super(message);
	}
}
