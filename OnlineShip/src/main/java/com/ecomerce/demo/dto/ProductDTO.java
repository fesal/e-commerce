package com.ecomerce.demo.dto;

public class ProductDTO {

	private int id;
	private String name;
	private String description;
	private String price;
	private String shipping;
	private String tax;
	private String category;
	private int user_id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String stripe;
	private String paypal;
	
	public ProductDTO()
	{}
	
	public ProductDTO(int id, String name, String description, String price, String shipping, String tax,
			String category, int user_id, String firstname, String lastname, String email, String username,
			String stripe, String paypal) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.shipping = shipping;
		this.tax = tax;
		this.category = category;
		this.user_id = user_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.stripe = stripe;
		this.paypal = paypal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStripe() {
		return stripe;
	}
	public void setStripe(String stripe) {
		this.stripe = stripe;
	}
	public String getPaypal() {
		return paypal;
	}
	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}

}
