package com.ecomerce.demo.auth.service;

import com.ecomerce.demo.model.User;

public interface UserService {
	User saveUser(User user, String[] roles);

	User findByUsername(String username);
	
	User getById(int id);
}
