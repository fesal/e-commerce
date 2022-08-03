package com.ecomerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.demo.auth.service.UserService;
import com.ecomerce.demo.model.User;

@Controller
public class MyProfileController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping({"my_profile"})
	public String product(Model model, @RequestParam("id") int id){
		System.err.print(id);
		User u = userService.getById(id);
		System.err.print(u.getId());
		model.addAttribute("user", u);
		return "my_profile";
	}
	
	@GetMapping({"my_profile.html"})
	public String product(Model model){
		User user = userService.findByUsername(AuthenticatedUser.getUsername());
		model.addAttribute("user", user);
		return "my_profile";
	}
	
	@PostMapping({"edit", "/edit"})
	public String product(Model model, @ModelAttribute("user") User user){
		User u = userService.findByUsername(AuthenticatedUser.getUsername());
		if(user.getFirstname().length()>2 & user.getLastname().length()>2 && user.getEmail().contains("@") && user.getEmail().contains("."))
		{
			u.setEmail(user.getEmail());
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getLastname());
			u.setPass(user.getPass());
			u.setPaypal(user.getPaypal());
			u.setStripe(user.getStripe());
			String[] role = {"USER"};
			userService.saveUser(u, role);
		}
		model.addAttribute("user", u);
		return "my_profile";
	}
}
