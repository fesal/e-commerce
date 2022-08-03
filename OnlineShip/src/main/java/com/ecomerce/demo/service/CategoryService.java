package com.ecomerce.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.model.Category;
import com.ecomerce.demo.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll()
	{
		return categoryRepository.findAll();
	}
	
	public Category findById(int id)
	{
		return categoryRepository.getOne(id);
	}
	
	public Category findByName(String name)
	{
		return categoryRepository.findByName(name);
	}
}
