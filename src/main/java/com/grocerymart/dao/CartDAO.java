package com.grocerymart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerymart.entity.Cart;
import com.grocerymart.entity.User;

public interface CartDAO extends JpaRepository<Cart, Long> {
	public Cart findByUser(User user);
}
