package com.grocerymart.helper;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymart.constants.Constants;
import com.grocerymart.constants.ControllerConstant;
import com.grocerymart.dao.GroceryItemDAO;
import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.exception.GroceryItemNotFound;

@Service
public class GroceryItemHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(GroceryItemHelper.class);
	
	@Autowired
	private GroceryItemDAO groceryItemDAO;
	
	public GroceryItem updateGroceryItem(GroceryItemDTO groceryItemDTO, Long id) {
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if(groceryItem.isEmpty()) {
			throw new GroceryItemNotFound(Constants.ITEM_NOT_FOUND);
		}
		GroceryItem item = groceryItem.get();
		
		logger.info(Constants.UPDATE_ITEM_FIELD);
		if (null != groceryItemDTO.getInventory()) {
			item.setInventory(groceryItemDTO.getInventory());
			item.setOutOfStock(groceryItemDTO.getInventory() > 0);
		}
		if (null != groceryItemDTO.getItemName()) {
			item.setItemName(groceryItemDTO.getItemName());
		}
		if (null != groceryItemDTO.getItemPrice()) {
			item.setItemPrice(groceryItemDTO.getItemPrice());
		}
		return item;
	}

}
