package com.grocerymart.exceptionhandler;

import java.rmi.StubNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grocerymart.exception.GroceryItemNotFound;
import com.grocerymart.exception.OutOfStock;
import com.grocerymart.exception.response.CommonExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GroceryItemNotFound.class)
	public ResponseEntity<CommonExceptionResponse> itemNotFoundException(Exception e) {
		return new ResponseEntity<>(new CommonExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OutOfStock.class)
	public ResponseEntity<CommonExceptionResponse> itemOutOfStockException(Exception e) {
		return new ResponseEntity<>(new CommonExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

}
