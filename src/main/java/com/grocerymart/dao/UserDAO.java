package com.grocerymart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocerymart.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {
	
	public User findByUserName(String userName);

}
