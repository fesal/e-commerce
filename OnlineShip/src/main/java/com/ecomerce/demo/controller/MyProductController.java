package com.ecomerce.demo.controller;

import java.util.Optional;

import com.ecomerce.demo.service.CategoryService;
import com.ecomerce.demo.service.ProductServicePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecomerce.demo.auth.service.UserService;
import com.ecomerce.demo.model.Pager;
import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.model.User;

@Controller
public class MyProductController {

	private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 15;
    private static final int[] PAGE_SIZES = {15, 20, 30};
    
    private int total;
    private String last;
	
	@Autowired
	private ProductServicePageable productServicePageable;
	
	@Autowired
	private CategoryService categoryServices;
	
	@Autowired
	private UserService userServices;
	
	@GetMapping({"edit_product.html","edit_product"})
	public String editProduct()
	{
		return "edit_product";
	}
	
	
	@GetMapping({"my_product.html","my_product","/my_product","/my_product.html"})
	public ModelAndView showPersonsPage(@RequestParam("pSize") Optional<Integer> pSize,
	                                        @RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("my_product.html");
		modelAndView.addObject("categories", categoryServices.findAll());
        int evalPageSize = pSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        User u = userServices.findByUsername(AuthenticatedUser.getUsername());
        total = productServicePageable.numOfMyProd(u.getId());
        Page<Product> persons = productServicePageable.findMyProducts(u.getId(), PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("myProdTotal", total);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("sort", "all");
        
        return modelAndView;
	}
	
	@GetMapping("my_product_sort")
	public String sort(@RequestParam("sort")String sort, @RequestParam("pSize") Optional<Integer> pSize,
            @RequestParam("page") Optional<Integer> page, Model model) {
			
		model.addAttribute("categories", categoryServices.findAll());
		int evalPageSize = pSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		
		User user = userServices.findByUsername(AuthenticatedUser.getUsername());
		Page<Product> persons = null; 
		if(sort.equals("sold"))
		{
			 persons = productServicePageable.findSold(user.getId(), PageRequest.of(evalPage, evalPageSize));
			 System.err.print("This is sold");
		}
		else if(sort.equals("current")){
			 persons = productServicePageable.findCurrent(user.getId(), PageRequest.of(evalPage, evalPageSize));
			 System.err.print("This is current");
		} 
		else {
			persons = productServicePageable.findMyProducts(user.getId(), PageRequest.of(evalPage, evalPageSize));
			System.err.print("This is all products");
		}
		
		Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("persons", persons);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		model.addAttribute("myProdTotal", total);
		model.addAttribute("sort", sort);
		return "my_product";
	}
}
