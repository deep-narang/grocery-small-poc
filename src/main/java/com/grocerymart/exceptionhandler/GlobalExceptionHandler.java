package com.grocerymart.exceptionhandler;

import java.rmi.StubNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grocerymart.exception.GroceryItemNotFound;
import com.grocerymart.exception.OutOfStock;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(GroceryItemNotFound.class)
	public ResponseEntity<GroceryItemNotFound> itemNotFoundException(Exception e){
		return new ResponseEntity<>(new GroceryItemNotFound(e.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OutOfStock.class)
	public ResponseEntity<OutOfStock> itemOutOfStockException(Exception e){
		return new ResponseEntity<>(new OutOfStock(e.getMessage()), HttpStatus.NOT_FOUND);
	}

}
