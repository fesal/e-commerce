package com.ecomerce.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.demo.dto.OrderDTO;
import com.ecomerce.demo.model.Orders;
import com.ecomerce.demo.repositories.OrdersRepository;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<OrderDTO> findAll() {
		return getOrders(ordersRepository.findAll());
	}

	private List<OrderDTO> getOrders(List<Orders> orders){
		List<OrderDTO> orderList = new ArrayList<>();
		for(Orders order : orders) {
			orderList.add(new OrderDTO(order.getId(), order.getQuantity(), order.getDate_sold(), order.getStreet(), order.getCity(), order.getState(), order.getZipcode(), order.getUser().getId(), order.getProduct().getId()));
		}
		return orderList;
	}
	
}
