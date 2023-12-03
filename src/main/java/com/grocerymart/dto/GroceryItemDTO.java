package com.grocerymart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryItemDTO {

	private Long id;
	private String itemName;
	private Double itemPrice;
	private Long inventory;
	
}
