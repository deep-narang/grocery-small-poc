package com.grocerymart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if(groceryItem.isEmpty()) {
			throw new GroceryItemNotFound("Item Not FOund");
		}
		GroceryItem item = groceryItem.get();
		if (item.isOutOfStock()) {
			throw new OutOfStock("Item Out of Stock");
		}
		User loggedInUser = userHelper.getLoggedInUser();
		Cart userCart = cartDAO.findByUser(loggedInUser);
		if (null != userCart) {
			if(cartHelper.checkIfItemInCart(userCart.getItemsList(), item)) {
				userCart.setQuantity(userCart.getQuantity() + cartDTO.getQuantity());
			} else {
				userCart.getItemsList().add(item);
			}
			cartDAO.save(userCart);
		} else {
			List<GroceryItem> itemsList = new ArrayList<GroceryItem>();
			Cart cart = new Cart();
			cart.setUser(loggedInUser);
			itemsList.add(item);
			cart.setItemsList(itemsList);
			cartDAO.save(cart);
		}
		return true;
	}

}
