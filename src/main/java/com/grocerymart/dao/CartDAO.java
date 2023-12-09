package com.grocerymart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocerymart.entity.Cart;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.entity.User;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {
	public List<Cart> findByUser(User user);
	public Cart findByGroceryItemAndUser(GroceryItem groceryItem, User user);
}
