package com.grocerymart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.dto.GroceryItemResponseDTO;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.service.CartService;
import com.grocerymart.service.GroceryItemService;
import com.grocerymart.service.GroceryItemServiceImpl;

@RestController
@RequestMapping("/grocery")
public class GroceryController {
	
	@Autowired
	private GroceryItemService groceryItemService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public GroceryItemResponseDTO getAvailableGrocery() {
		return groceryItemService.getAvailableGrocery();
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public GroceryItem addNewItem(@RequestBody GroceryItemDTO groceryItemDTO) {
		return groceryItemService.addGroceryItem(groceryItemDTO);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public GroceryItemResponseDTO getAllGroceryItems() {
		return groceryItemService.getAllGroceryItems();
	}
	
	@PostMapping("/remove/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public boolean markItemAsOutOfStock(@PathVariable Long id) {
		return groceryItemService.markAsOutOfStock(id);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItemDTO groceryItem) {
		return groceryItemService.updateItem(groceryItem, id);
	}
	
	@PutMapping("/manage/id/{id}/inventory/{inventory}")
	@PreAuthorize("hasRole('ADMIN')")
	public GroceryItem updateInventory(@PathVariable Long id, @PathVariable Long inventory) {
		return groceryItemService.updateInvetory(id, inventory);
	}
	
	@GetMapping("/cart/view")
	@PreAuthorize("hasRole('USER')")
	public CartResponseDTO getProductsFromCart() {
		return cartService.getCartItems();
	}
	
	@PostMapping("/cart/add/{id}")
	@PreAuthorize("hasRole('USER')")
	public boolean addProductToCart(@PathVariable Long id) {
		return cartService.addItemToCart(id);
	}
	
}
