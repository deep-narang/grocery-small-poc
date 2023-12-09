package com.grocerymart.service;

import com.grocerymart.dto.CartDTO;
import com.grocerymart.dto.CartResponseDTO;

public interface CartService {

	CartResponseDTO getCartItems();

	boolean addItemToCart(Long id, CartDTO cartDTO);

	boolean updateCart(Long productId, CartDTO cartDTO);

	boolean removeFromCart(Long productId);

}
