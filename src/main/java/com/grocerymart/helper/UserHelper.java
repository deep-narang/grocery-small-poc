package com.grocerymart.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.grocerymart.dao.UserDAO;
import com.grocerymart.entity.User;

public class UserHelper {
	
	@Autowired
	private UserDAO userDAO;

	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDAO.findByUserName(authentication.getPrincipal().toString());
	}

}
