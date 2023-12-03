package com.grocerymart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymart.dao.GroceryItemDAO;
import com.grocerymart.dto.GroceryItemDTO;
import com.grocerymart.dto.GroceryItemResponseDTO;
import com.grocerymart.entity.GroceryItem;
import com.grocerymart.exception.GroceryItemNotFound;
import com.grocerymart.helper.GroceryItemHelper;
import com.grocerymart.util.ObjectConversionUtil;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{
	
	private static final Logger logger = LoggerFactory.getLogger(GroceryItemServiceImpl.class);
	
	@Autowired
	private GroceryItemDAO groceryItemDAO;
	
	@Autowired
	private GroceryItemHelper groceryItemHelper;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public GroceryItem addGroceryItem(GroceryItemDTO groceryItemDTO) {
		GroceryItem groceryItem = ObjectConversionUtil.groceryItemDtoToEntity(groceryItemDTO);
		logger.info("Saving item to DB {}", groceryItem);
		return groceryItemDAO.save(groceryItem);
	}

	@Override
	public GroceryItemResponseDTO getAllGroceryItems() {
		logger.info("Fetching all the groceries from the DB");
		List<GroceryItem> itemsList = groceryItemDAO.findAll();
		logger.info("Converting all the entities into the DTO");
		return GroceryItemResponseDTO.builder()
				.groceryItemList(itemsList.stream()
						.map(item -> ObjectConversionUtil.groceryEntityToDTO(item))
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public boolean markAsOutOfStock(Long id) {
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if (groceryItem.isEmpty()) {
			logger.error("Item with id {} not found!");
			throw new GroceryItemNotFound("Item with specified id not found!");
		}
		GroceryItem item = groceryItem.get();
		groceryItemDAO.delete(item);
		logger.info("Item with id {} marked as out of stock", id);
		return true;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public GroceryItem updateItem(GroceryItemDTO groceryItem, Long id) {
		return groceryItemDAO.save(groceryItemHelper.updateGroceryItem(groceryItem, id));
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public GroceryItem updateInvetory(Long id, Long inventory) {
		Optional<GroceryItem> groceryItem = groceryItemDAO.findById(id);
		if (groceryItem.isEmpty()) {
			throw new GroceryItemNotFound("Item with specified id not found!");
		}
		GroceryItem item = groceryItem.get();
		item.setInventory(inventory);
		item.setOutOfStock(inventory<=0);
		return groceryItemDAO.save(item);
	}

	@Override
	public GroceryItemResponseDTO getAvailableGrocery() {
		List<GroceryItemDTO> groceryDtoList = groceryItemDAO.findAll()
				.stream()
				.filter(item -> !item.isOutOfStock())
				.map(item -> ObjectConversionUtil.groceryEntityToDTO(item))
				.collect(Collectors.toList());
		
		return GroceryItemResponseDTO.builder()
				.groceryItemList(groceryDtoList)
				.build();
	}

}
