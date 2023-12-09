package com.grocerymart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymart.constants.Constants;
import com.grocerymart.dao.CartDAO;
import com.grocerymart.dao.GroceryItemDAO;
import com.grocerymart.dto.CartDTO;
import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.entity.Cart;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;
import com.grocerymart.exception.GroceryItemNotFound;
import com.grocerymart.exception.OutOfStock;
import com.grocerymart.helper.CartHelper;
import com.grocerymart.helper.UserHelper;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CartHelper cartHelper;

	@Autowired
	private GroceryItemDAO groceryItemDAO;

	@Override
	public CartResponseDTO getCartItems() {
		User user = userHelper.getLoggedInUser();
		return cartHelper.getCartItems(cartDAO.findByUserId(user.getId()), user);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean addItemToCart(Long id, CartDTO cartDTO) {
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if (groceryItem.isEmpty()) {
			throw new GroceryItemNotFound(Constants.ITEM_NOT_FOUND);
		}
		GroceryItem item = groceryItem.get();
		if (item.isOutOfStock()) {
			throw new OutOfStock(Constants.OUT_OF_STOCK);
		}
		int quantity = Objects.nonNull(cartDTO) && Objects.nonNull(cartDTO.getQuantity()) ? cartDTO.getQuantity() : 1;
		Cart cart = new Cart();
		cart.setUserId(userHelper.getLoggedInUser().getId());
		cart.setItem(item);
		cart.setQuantity(quantity);
		cartDAO.save(cart);

		return true;
	}

}
