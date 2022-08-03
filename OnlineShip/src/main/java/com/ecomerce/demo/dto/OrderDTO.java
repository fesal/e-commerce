package com.ecomerce.demo.dto;

import java.util.Date;

public class OrderDTO {

private int id;

	private int quantity;

	private Date date_sold;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private int zipcode;
	
	private int user_id;
	
	private int product_id;

	public OrderDTO() {}
	
	public OrderDTO(int id, int quantity, Date date_sold, String street, String city, String state, int zipcode, int user_id, int product_id) {
		this.id = id;
		this.quantity = quantity;
		this.date_sold = date_sold;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.user_id = user_id;
		this.product_id = product_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate_sold() {
		return date_sold;
	}

	public void setDate_sold(Date date_sold) {
		this.date_sold = date_sold;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
}
