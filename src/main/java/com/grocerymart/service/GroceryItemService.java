package com.grocerymart.service;

import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.dto.GroceryItemResponseDTO;
import com.grocerymart.entity.GroceryItem;

public interface GroceryItemService {

	GroceryItem addGroceryItem(GroceryItemDTO groceryItemDTO);
	
	GroceryItemResponseDTO getAllGroceryItems();

	boolean markAsOutOfStock(Long id);

	GroceryItem updateItem(GroceryItemDTO groceryItem, Long id);

	GroceryItem updateInvetory(Long id, Long inventory);

	GroceryItemResponseDTO getAvailableGrocery();
	
}
