package com.grocerymart.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grocery_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryItem implements Serializable{
	
	private static final long serialVersionUID = 8367786333884466979L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String itemName;
	
	@Column(name = "price")
	private Double itemPrice;
	
	@Column(name = "in_stock")
	private boolean outOfStock;
	
	@Column(name = "inventory")
	private Long inventory;

	@Override
	public String toString() {
		return "GroceryItem [itemName=" + itemName + ", itemPrice=" + itemPrice + ", inventory=" + inventory + "]";
	}
	
	

}
