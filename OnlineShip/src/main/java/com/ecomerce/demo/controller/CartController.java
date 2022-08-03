package com.ecomerce.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecomerce.demo.service.ProductServicePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.demo.auth.service.UserService;
import com.ecomerce.demo.model.Cart;
import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.service.ProductServices;

@Controller
public class CartController {

	@Autowired
	private ProductServicePageable productService;
	
	private List<Cart> listOfProductsInCart = new ArrayList<>();
	
	@Autowired
	private ProductServices prodServ;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping({"cart.html", "/cart.html"})
	public String cart() {
		return "cart";
	}
	
	@RequestMapping({"/cart","cart.html"})
	public String products(@RequestParam("id")int id, @RequestParam("quantity")int quantity, Model model) {
		Product product = prodServ.findById(id);
		Cart c = new Cart();
		c.setId(product.getId());
		//String username = AuthenticatedUser.getUsername();
		//User u = userService.findByUsername(username);
		double total = (quantity*Double.parseDouble(product.getPrice()));
		//c.setUser_id(u.getId());
		c.setTotal(total);
		listOfProductsInCart.add(c);
		Page<Product> listOfProducts = productService.findSimilarProducts(product.getUser().getId(), PageRequest.of(0, 20));
		model.addAttribute("product", product);
		model.addAttribute("quantity", quantity);
		model.addAttribute("total", total);
		model.addAttribute("similarProducts", listOfProducts);
		model.addAttribute("listOfProductsinCart", listOfProductsInCart);
		return "cart";
	}
	
	@RequestMapping({"/payform","payform.html"})
	public String payNow() {
		
		return "payform";
	}
	
}
