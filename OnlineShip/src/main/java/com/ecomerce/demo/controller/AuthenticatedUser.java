package com.ecomerce.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser {
	
	public static String getUsername()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails us = (UserDetails) auth.getPrincipal();
		return us.getUsername();
	}
}
