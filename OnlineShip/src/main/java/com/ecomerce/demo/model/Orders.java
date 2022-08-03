package com.ecomerce.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Orders.class, property="id")
public class Orders implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//@NotEmpty(message="The value must not be null.")
	private int quantity;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date date_sold;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private int zipcode;

	//@JsonManagedReference
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable=false)
	private Product product;
	
	//@JsonBackReference
	@OneToMany(targetEntity = Comment.class, mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<Comment> comments;
	
	//@JsonManagedReference
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
	private User perdoruesi;
	
	
	public Orders() {
	}

	public Orders(int quantity, Date date_sold, String street, String city, String state, int zipcode, Product product, User user) {
		this.quantity = quantity;
		this.date_sold = date_sold;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.product = product;
		this.perdoruesi = user;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return perdoruesi;
	}

	public void setUser(User user) {
		this.perdoruesi = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
