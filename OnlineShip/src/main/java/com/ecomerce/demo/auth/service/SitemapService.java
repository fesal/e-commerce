package com.ecomerce.demo.auth.service;

import java.util.List;

import com.ecomerce.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SitemapService {

	@Autowired
	private ProductRepository repository;
		
	public List<Integer> createSitemap(int initial, int end){
		return repository.selectIds(initial, end);
	}
	
	public int countProd()
	{
		return repository.numOfProd();
	}
}
