package com.ecomerce.demo.service;

import java.util.List;

import com.ecomerce.demo.repositories.OrdersRepository;
import com.ecomerce.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.model.Product;

@Service
public class ProductServices {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrdersRepository orderRepository;
	
	
	public Page<Product> getProductsByPage(Pageable p)
	{
		return productRepository.getProductsByPage(p);
	}
	
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}
	
	public boolean create(Product p)
	{
		if(p!=null)
		{
			productRepository.save(p);
			return true;
		}
		return false;
	}
	
	public Product findById(int id)
	{
		return productRepository.getOne(id);
	}
	
	public Product findByMyId(int id)
	{
		return productRepository.findByMyId(id);
	}
	
	public List<Product> searchProducts(String keywords)
	{
		List<Product> products = productRepository.search(keywords);
		return products;
	}

	public List<Product> findMyProducts(int userId) {
		return productRepository.findProducts(userId);
	}

	public List<Product> findSold(int id) {
		return orderRepository.findSoldProducts(id);
	}

	public List<Product> findCurrent(int id) {
		return productRepository.findCurrentProducts(id);
	}

	public List<Product> searchProductsByCategory(String search) {
		return productRepository.searchProductsByCategory(search);
	}

	public List<Product> searchProductsByKeywordAndCategory(String search, String category) {
		return productRepository.searchProductsByKeywordAndCategory(search, category);
	}
	
	public int numOfProd()
	{
		return productRepository.numOfProd();
	}
}
