package com.ecomerce.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.model.Product;

/**
* Generated by Spring Data Generator on 22/06/2018
*/
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	@Query("SELECT p from Product p WHERE p.name LIKE CONCAT('%',:keywords,'%')")
	List<Product> search(@Param("keywords") String keywords);
	
	@Query("SELECT p1, p2 FROM Product p1 JOIN p1.category p2 WHERE p1.id =:id")
	Product findByMyId(@Param("id") int id);
	
	@Query("SELECT p FROM Product p WHERE p.user.id =:userId")
	public List<Product> findProducts(@Param("userId") int userId);

	@Query("SELECT p FROM Product p WHERE p.quantity>0 AND p.user.id =:userId")
	List<Product> findCurrentProducts(@Param("userId") int id);

	@Query("SELECT p FROM Product p WHERE p.category.name =:category")
	List<Product> searchProductsByCategory(@Param("category") String category);

	@Query("SELECT p FROM Product p WHERE p.category.name =:category AND p.name LIKE CONCAT('%',:search,'%')")
	List<Product> searchProductsByKeywordAndCategory(@Param("search") String search, @Param("category") String category);
	
	@Query("SELECT p FROM Product p")
	Page<Product> getProductsByPage(Pageable pageable);
	
	@Query("SELECT COUNT(p.id) FROM Product p")
	public int numOfProd();
	
	@Query("SELECT p.id FROM Product p WHERE p.id>=:initial AND p.id<=:end")
	public List<Integer> selectIds(@Param("initial")int initial, @Param("end")int end);
	
}