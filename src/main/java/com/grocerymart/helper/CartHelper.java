package com.grocerymart.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.entity.Cart;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;
import com.grocerymart.util.ObjectConversionUtil;

public class CartHelper {

	public CartResponseDTO getCartItems(Cart cart, User user) {
		CartResponseDTO cartResponseDTO = new CartResponseDTO();
		cartResponseDTO.setUserName(user.getUserName());
		cartResponseDTO.setRole(user.getRole().getRole());
		if (null != cart) {
			cartResponseDTO.setGroceryItemDTOList(
					cart.getItemsList().stream()
					.map(item -> ObjectConversionUtil.groceryEntityToDTO(item))
					.collect(Collectors.toList())
					);
		}
		return cartResponseDTO;
	}

}
