package com.ecomerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.demo.auth.service.SecurityService;

@Controller
@RequestMapping({"login","login.html"})
public class LoginController {

	@Autowired
	private SecurityService securityService;
	
	@GetMapping({"","/"})
	public String login(Model model, String error, String logout) {	
		if(error != null)
		{
			model.addAttribute("errorMsg", "Your username and password are invalid.");
		}
		if(logout != null)
		{
			model.addAttribute("You have been logged out succesfully.");
		}
		return "login";
	}
	
	@PostMapping({"","/"})
	public String doLogin(@RequestParam("username")String username, @RequestParam("password")String password)
	{
		boolean logged = securityService.autologin(username, password);
		if(logged)
		{
			return "redirect:index.html";
		}
		return "login";
	}
}
