package com.grocerymart.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grocerymart.dao.UserDAO;
import com.grocerymart.entity.User;

@Service
public class UserHelper {
	
	@Autowired
	private UserDAO userDAO;

	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDAO.findByUserName(authentication.getName());
	}

}
