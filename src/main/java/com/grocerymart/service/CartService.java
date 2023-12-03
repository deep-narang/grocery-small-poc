package com.grocerymart.service;

import com.grocerymart.dto.CartResponseDTO;

public interface CartService {

	CartResponseDTO getCartItems();

	boolean addItemToCart(Long id);

}
