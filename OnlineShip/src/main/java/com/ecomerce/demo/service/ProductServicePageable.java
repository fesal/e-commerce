package com.ecomerce.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.model.Product;
import com.ecomerce.demo.repositories.OrdersRepository;
import com.ecomerce.demo.repositories.ProductPagingRepository;

@Service
public class ProductServicePageable {
	
	@Autowired
	private ProductPagingRepository productRepository;
	
	@Autowired
	private OrdersRepository orderRepository;
	
	public Page<Product> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
	}
	
	public Page<Product> searchProductsByCategory(Optional<String> category, Pageable pageable) {
		return productRepository.searchProductsByCategory(category, pageable);
	}

	public Page<Product> searchProductsByKeywordAndCategory(Optional<String> search, Optional<String> category, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.searchProductsByKeywordAndCategory(search, category, pageable);
	}
	
	public Page<Product>  searchProducts(String keywords, Pageable pageable)
	{
		return productRepository.search(keywords, pageable);
	}
	
	public Page<Product> findMyProducts(int userId, Pageable page) {
		return productRepository.findProducts(userId, page);
	}
	
	public Page<Product> findSold(int userId, Pageable page)
	{
		return orderRepository.findProductsSold(userId, page);
	}
	
	public Page<Product> findCurrent(int id, Pageable page) {
		return productRepository.findCurrentProducts(id, page);
	}
	
	public Page<Product> findSimilarProducts(int userId, Pageable page)
	{
		return productRepository.findSimilarProducts(userId, page);
	}

	
	public int numOfMyProd(int userId)
	{
		return productRepository.numOfMyProd(userId);
	}
}
