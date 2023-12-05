package com.grocerymart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.grocerymart.constants.Constants.Role;
import com.grocerymart.dao.UserDAO;
import com.grocerymart.entity.User;

@SpringBootApplication
public class GroceryMartApplication implements CommandLineRunner {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GroceryMartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User();
		admin.setFirstName("ion");
		admin.setLastName("admin");
		admin.setUserName("ionadmin");
		com.grocerymart.entity.Role adminRole = new com.grocerymart.entity.Role();
		adminRole.setRole(Role.ADMIN.getValue());
		admin.setRole(adminRole);
		admin.setPassword(bCryptPasswordEncoder.encode("password"));
		
		User user = new User();
		user.setFirstName("ion");
		user.setLastName("user");
		user.setUserName("ionuser");
		com.grocerymart.entity.Role userRole = new com.grocerymart.entity.Role();
		userRole.setRole(Role.USER.getValue());
		user.setRole(userRole);
		user.setPassword(bCryptPasswordEncoder.encode("password"));
		
		userDao.save(user);
		userDao.save(admin);
	}

}
