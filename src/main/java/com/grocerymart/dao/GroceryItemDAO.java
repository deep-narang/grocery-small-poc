package com.grocerymart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocerymart.entity.GroceryItem;

@Repository
public interface GroceryItemDAO extends JpaRepository<GroceryItem, Long>{

	public List<GroceryItem> findByOutOfStock(boolean outOfStock);
}
