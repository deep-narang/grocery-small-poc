package com.grocerymart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerymart.dto.CartDTO;
import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.dto.GroceryItemResponseDTO;
import com.grocerymart.dto.UserDTO;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;
import com.grocerymart.service.CartService;
import com.grocerymart.service.GroceryItemService;
import com.grocerymart.service.GroceryItemServiceImpl;
import com.grocerymart.service.UserService;
import com.grocerymart.constants.*;

@RestController
@RequestMapping(value = ControllerConstant.GROCERY_BASE_URL)
public class GroceryController {
	
	@Autowired
	private GroceryItemService groceryItemService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public GroceryItemResponseDTO getAvailableGrocery() {
		return groceryItemService.getAvailableGrocery();
	}

	@PostMapping(value = ControllerConstant.ADD_GROCERY)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public GroceryItem addNewItem(@RequestBody GroceryItemDTO groceryItemDTO) {
		return groceryItemService.addGroceryItem(groceryItemDTO);
	}
	
	@GetMapping(value = ControllerConstant.GET_ALL_GROCERY)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public GroceryItemResponseDTO getAllGroceryItems() {
		return groceryItemService.getAllGroceryItems();
	}
	
	@PostMapping(value = ControllerConstant.REMOVE_ITEM)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public boolean markItemAsOutOfStock(@PathVariable Long id) {
		return groceryItemService.markAsOutOfStock(id);
	}
	
	@PutMapping(value = ControllerConstant.UPDATE_ITEM)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItemDTO groceryItem) {
		return groceryItemService.updateItem(groceryItem, id);
	}
	
	@PutMapping(value = ControllerConstant.UPDATE_INVENTORY)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public GroceryItem updateInventory(@PathVariable Long id, @PathVariable Long inventory) {
		return groceryItemService.updateInvetory(id, inventory);
	}
	
	@GetMapping(value = ControllerConstant.ITEMS_IN_CART)
	@PreAuthorize(value = ControllerConstant.USER_AUTHORITY)
	public CartResponseDTO getProductsFromCart() {
		return cartService.getCartItems();
	}
	
	@PostMapping(value = ControllerConstant.ADD_ITEM_IN_CART)
	@PreAuthorize(value = ControllerConstant.USER_AUTHORITY)
	public boolean addProductToCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
		return cartService.addItemToCart(id, cartDTO);
	}
	
	@GetMapping(value = ControllerConstant.ALL_USERS)
	@PreAuthorize(value = ControllerConstant.ADMIN_AUTHORITY)
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
}
