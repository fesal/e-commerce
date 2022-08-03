package com.ecomerce.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.ecomerce.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomerce.demo.dto.OrderDTO;
import com.ecomerce.demo.model.Orders;
import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.model.User;
import com.ecomerce.demo.repositories.OrdersRepository;
import com.ecomerce.demo.repositories.ProductRepository;
import com.ecomerce.demo.repositories.UserRepository;

@Controller
@RequestMapping(value="/orders")
public class OrdersController{

	private String error= "{\"error\": \"1\"}";
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/getall")
	public List<OrderDTO> retrieveAllOrders() {
		return orderService.findAll();
	}
	
	@RequestMapping({"","/"})
	public String findAll(Model model) {	
		model.addAttribute("posts", orderRepository.findAll());
		return "order";
	}

	@PostMapping("/create")
	public Object createOrders(@RequestBody OrderDTO order) {
		Orders savedOrder = null;
		try{
			Product p = productRepository.getOne(order.getProduct_id());
			User u = userRepository.getOne(order.getUser_id());
			Orders o = new Orders(order.getQuantity(), new Date(),order.getStreet(),
					order.getCity(),order.getState(), order.getZipcode(), p, u);
			savedOrder = orderRepository.save(o);
		}
		catch(Exception e)
		{
			System.err.println("An error ocurred: values"+e.getMessage());
			return error;//"{ \"message\": \"Fields must not be empty\"}";
		}
		return savedOrder;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteOrders(@PathVariable int id) {
		orderRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateOrders(@RequestBody Orders order, @PathVariable int id) {

		Optional<Orders> ordersOptional = orderRepository.findById(id);

		if (!ordersOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);//ResponseEntity.notFound().build();
		order.setId(id);	
		orderRepository.save(order);

		return ResponseEntity.noContent().build();
	}
	
}
