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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomerce.demo.model.Pager;
import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.service.ProductServices;

@Controller
public class IndexController {

	private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 25;
    private static final int[] PAGE_SIZES = {35, 45, 60};
    
    //private boolean searchedAll, searchedByCategory, searchCategoryKeywords  = false;
    private String keyword, category; 
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private CategoryService categoryServices;
	
	@Autowired
	private ProductServicePageable productServicePageable;
	
	
	@GetMapping({"index.html","index.html/", "", "/"})
	public String index(Model model, @RequestParam("search") Optional<String> search, 
			@RequestParam("category") Optional<String> category, 
			@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {	
		Page<Product> persons = null;
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        if(!category.isPresent() && !search.isPresent())
		{
        	persons = productServicePageable.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		}
        else if(category.get().equals("all") && search.isPresent())
		{
			persons =  productServicePageable.searchProducts(search.get(), PageRequest.of(evalPage, evalPageSize));
			System.err.print("Category: "+category+" Keyword"+keyword+ " persons "+persons.getSize());
		}
		else if(search.get().equals("")&& !category.equals("all"))
		{
			persons = productServicePageable.searchProductsByCategory(category, PageRequest.of(evalPage, evalPageSize));
		}
		else if(!search.equals("")&& !category.equals("all"))
		{
			persons = productServicePageable.searchProductsByKeywordAndCategory(search, category,  PageRequest.of(evalPage, evalPageSize));
		}
		
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        model.addAttribute("persons", persons);
        model.addAttribute("categories", categoryServices.findAll());
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		return "index";
	}
	
	@PostMapping({"mobile_index","mobile_index/"})
	public String index(Model model, @RequestParam("search") Optional<String> search, 
			@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) 
	{	
		Page<Product> persons = null;
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		persons =  productServicePageable.searchProducts(search.get(), PageRequest.of(evalPage, evalPageSize));
		//keyword = search;
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        model.addAttribute("persons", persons);
        model.addAttribute("categories", categoryServices.findAll());
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		return "index";
	}
	
	@GetMapping({"mobile_index","mobile_index/"})
	public String indexMobile(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) 
	{	
		Page<Product> persons = null;
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        persons = productServicePageable.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        model.addAttribute("persons", persons);
        model.addAttribute("categories", categoryServices.findAll());
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		return "index";
	}
	
	@GetMapping("sort_by_category")
	public String categoryFilter(Model model, @RequestParam("category") Optional<String> category, @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {	
		model.addAttribute("categories", categoryServices.findAll());
		Page<Product> persons = null;
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        persons = productServicePageable.searchProductsByCategory(category, PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
	    model.addAttribute("persons", persons);
	    model.addAttribute("categories", categoryServices.findAll());
	    model.addAttribute("selectedPageSize", evalPageSize);
	    model.addAttribute("pageSizes", PAGE_SIZES);
	    model.addAttribute("pager", pager);
		return "index";
	}

}
