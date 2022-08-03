package com.ecomerce.demo.auth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.ecomerce.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecomerce.demo.model.Role;
import com.ecomerce.demo.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findUsersByUsername(username);
		UserDetails userDetails = null;
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		if(users.size()>0)
		{	User user = users.get(0);
			for (Role role : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
			}
			userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass(),
					grantedAuthorities);
		}
		return userDetails;
	}
}
