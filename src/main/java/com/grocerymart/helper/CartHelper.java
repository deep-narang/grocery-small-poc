package com.grocerymart.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.entity.Cart;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;
import com.grocerymart.util.ObjectConversionUtil;

@Service
public class CartHelper {

	public CartResponseDTO getCartItems(Cart findByUser, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean checkIfItemInCart(List<GroceryItem> itemsList, GroceryItem item) {
		GroceryItem groceryItem = itemsList.stream()
				.filter(product -> product.getId() == item.getId())
				.findFirst().orElse(null);
		return null != groceryItem ? true : false; 
	}

	

}
