package com.ecomerce.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.demo.model.Comment;
import com.ecomerce.demo.repositories.CommentRepository;
import com.ecomerce.demo.repositories.UserRepository;

@RestController
@RequestMapping(value="/comment")
public class CommentController {

private String error= "{\"error\": \"1\"}";
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/getall")
	public List<Comment> retrieveAllComments() {
		return commentRepository.findAll();
	}

	@PostMapping("/create")
	public Object createOrders(@RequestBody Comment comment) {
		Comment savedOrder = null;
		try{
			savedOrder = commentRepository.save(comment);
		}
		catch(Exception e)
		{
			System.err.println("An error ocurred: values");
			return error;
		}
		return savedOrder;
	}
}
