package com.ecomerce.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.model.User;
import com.ecomerce.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
    public void save(User user) {
        userRepository.save(user);
    }

    public User getById(int id) {
        User user;
        user = userRepository.getOne(id);//findOne(id);
        return user;
    }
    
    public List<User> getUserByUsername(String username) {

        List<User> user = userRepository.findUsersByUsername(username);//findUsersByName(username);
        return user;
    }

    public void deleteById(int id) {

        userRepository.deleteById(id);
    }

	public User create(User user) {
		return userRepository.save(user);
	}
}
