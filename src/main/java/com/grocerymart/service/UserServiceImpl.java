package com.grocerymart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocerymart.dao.UserDAO;
import com.grocerymart.dto.CartDTO;
import com.grocerymart.dto.CartResponseDTO;
import com.grocerymart.dto.UserDTO;
import com.grocerymart.entity.User;
import com.grocerymart.util.ObjectConversionUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = userDAO.findAll();
		return allUsers.stream()
				.map(user -> ObjectConversionUtil.userEntityToDTOConversion(user))
				.collect(Collectors.toList());
	}

	

}
