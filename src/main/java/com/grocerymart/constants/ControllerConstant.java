package com.grocerymart.constants;

public class ControllerConstant {
	
	public static final String GROCERY_BASE_URL = "/grocery";
	public static final String ADD_GROCERY = "/add";
	public static final String GET_ALL_GROCERY = "/all";
	public static final String REMOVE_ITEM = "/remove/{id}";
	public static final String UPDATE_ITEM = "/update/{id}";
	public static final String UPDATE_INVENTORY = "/manage/id/{id}/inventory/{inventory}";
	public static final String ITEMS_IN_CART = "/cart/view";
	public static final String ADD_ITEM_IN_CART = "/cart/add/{id}";
	public static final String ALL_USERS = "/users";
	
	public static final String ADMIN_AUTHORITY = "hasRole('ADMIN')";
	public static final String USER_AUTHORITY = "hasRole('USER')";

}
