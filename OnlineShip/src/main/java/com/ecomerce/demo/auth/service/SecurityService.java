package com.ecomerce.demo.auth.service;

public interface SecurityService {
	String findLoggedInUsername();

	boolean autologin(String username, String password);
}
