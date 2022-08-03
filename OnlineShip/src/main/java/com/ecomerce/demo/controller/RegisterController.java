package com.ecomerce.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.demo.auth.service.UserService;
import com.ecomerce.demo.model.User;
//import com.example.demo.service.UserService;

@Controller
@RequestMapping({"register", "register.html"})
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping({"","/"})
	private String register() {
		return "register";
	}
	
	@PostMapping({"","/"})
	public Object createUsers(@Valid  @ModelAttribute("user")User user,BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword) {
		User savedUser = null;
		try{
				if (bindingResult.hasErrors()) {
		            return "register";
		        }
				if(user.getPass().equals(confirmPassword))
				{
					String[] roles = {"USER"};
					//savedUser = userService.saveUser(user, roles);
					savedUser = userService.saveUser(user, roles);
				}
				else
				{
					System.err.println("Passwords do not match");
				}
		}
		catch(Exception e)
		{
			System.err.println("An error ocurred: values");
		}
		return "redirect:/my_profile?id="+savedUser.getId();
	}
}
