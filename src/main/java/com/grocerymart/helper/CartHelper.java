package com.grocerymart.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.entity.Cart;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;
import com.grocerymart.util.ObjectConversionUtil;

@Service
public class CartHelper {

	public CartResponseDTO getCartItems(List<Cart> findByUser, User user) {
		CartResponseDTO cartResponseDTO = new CartResponseDTO();
		cartResponseDTO.setUserName(user.getUserName());
		cartResponseDTO.setRole(user.getRole().getRole());
		cartResponseDTO.setGroceryItemDTOList(findByUser.stream().map(cart -> {
			GroceryItem item = cart.getGroceryItem();
			return GroceryItemDTO.builder()
					.id(item.getId())
					.itemName(item.getItemName())
					.itemPrice(item.getItemPrice())
					.inventory(cart.getQuantity().longValue())
					.build();
		}).collect(Collectors.toList()));
		return cartResponseDTO;
	}

}
