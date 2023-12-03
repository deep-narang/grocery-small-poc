package com.grocerymart.dto;

import java.util.ArrayList;
import java.util.List;

import com.grocerymart.entity.GroceryItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartResponseDTO {
	
	private String userName;
	private String role;
	private List<GroceryItemDTO> groceryItemDTOList;

}
