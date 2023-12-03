package com.grocerymart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.GroceryItem.GroceryItemBuilder;

public class ObjectConversionUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectConversionUtil.class);
	
	public static GroceryItem groceryItemDtoToEntity(GroceryItemDTO groceryItemDTO) {
		logger.info("Converting DTO to Entity Object");
		
		return GroceryItem.builder()
				.itemName(groceryItemDTO.getItemName())
				.inventory(groceryItemDTO.getInventory())
				.itemPrice(groceryItemDTO.getItemPrice())
				.outOfStock(groceryItemDTO.getInventory()>0)
				.build();
	}
	
	public static GroceryItemDTO groceryEntityToDTO(GroceryItem groceryItem) {
		return GroceryItemDTO.builder()
				.id(groceryItem.getId())
				.itemName(groceryItem.getItemName())
				.itemPrice(groceryItem.getItemPrice())
				.inventory(groceryItem.getInventory())
				.build();
	}

}
