package com.ecomerce.demo.controller;

import com.ecomerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ecomerce.demo.repositories.UserRepository;

@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	/*@RequestMapping(value="/users", method = RequestMethod.GET)
    public String getUser(Model model) {
		model.addAttribute("users", new User());
        model.addAttribute("accounts", userService.findAll());
        return "users";
    }
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
    public String getEditAccount(@RequestParam("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("users", user);
        List<User> users = userService.findAll();
        model.addAttribute("accounts", users);
        return "users";
    }
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
    public String getRemoveAccount(@RequestParam("id") int id,Model model) {

        userService.deleteById(id);
        List<User> users = userService.findAll();
        model.addAttribute("accounts", users);
        return "redirect:/accounts";
    }*/
	 
	/*
	private String error= "{\"error\": \"1\"}";
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/getall")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/create")
	public Object createUsers(@RequestBody User user) {
		User savedUser = null;
		try{
			savedUser = userRepository.save(user);
		}
		catch(Exception e)
		{
			System.err.println("An error ocurred: values");
			return error;
		}
		return savedUser;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateOrders(@RequestBody User user, @PathVariable int id) {

		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		user.setId(id);	
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	*/
}
