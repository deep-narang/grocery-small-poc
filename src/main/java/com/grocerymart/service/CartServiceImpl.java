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
		return cartHelper.getCartItems(cartDAO.findByUser(user), user);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean addItemToCart(Long id, CartDTO cartDTO) {
		GroceryItem item = checkIfProductExists(id).get();
		if (item.isOutOfStock()) {
			throw new OutOfStock(Constants.OUT_OF_STOCK);
		}
		int quantity = Objects.nonNull(cartDTO) && Objects.nonNull(cartDTO.getQuantity()) ? cartDTO.getQuantity() : 1;
		Cart cart = new Cart();
		cart.setUser(userHelper.getLoggedInUser());
		cart.setGroceryItem(item);
		cart.setQuantity(quantity);
		cartDAO.save(cart);

		return true;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean updateCart(Long productId, CartDTO cartDTO) {
		GroceryItem groceryItem = checkIfProductExists(productId).get();
		Cart cart = cartDAO.findByGroceryItemAndUser(groceryItem, userHelper.getLoggedInUser());
		if (Objects.nonNull(cart)) {
			cart.setQuantity(cartDTO.getQuantity());
			cartDAO.save(cart); 
			return true;
		}
		return false;
	}

	@Override
	public boolean removeFromCart(Long productId) {
		GroceryItem groceryItem = checkIfProductExists(productId).get();
		Cart cart = cartDAO.findByGroceryItemAndUser(groceryItem, userHelper.getLoggedInUser());
		if (Objects.nonNull(cart)) {
			cartDAO.delete(cart);
			return true;
		}
		return false;
	}
	
	private Optional<GroceryItem> checkIfProductExists(Long id) {
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if (groceryItem.isEmpty()) {
			throw new GroceryItemNotFound(Constants.ITEM_NOT_FOUND);
		}
		return groceryItem;
	}

}
