package com.ecomerce.demo.auth.service;

import java.util.HashSet;
import java.util.Set;

import com.ecomerce.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.model.Role;
import com.ecomerce.demo.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User saveUser(User user, String[] roles) {
		user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
		Set<Role> rolesSet = new HashSet<Role>();
		for (String role : roles) {
			rolesSet.add(new Role(role));
		}
		user.setRoles(rolesSet);
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findUsersByUsername(username).get(0);
	}
	
	@Override
	public User getById(int id)
	{
		return userRepository.getOne(id);
	}
}
