package com.ecomerce.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.model.Product;


@Repository
public interface ProductPagingRepository  extends PagingAndSortingRepository<Product, Integer>{
	
	@Query("SELECT p from Product p WHERE p.name LIKE CONCAT('%',:keywords,'%')")
	Page<Product> search(@Param("keywords") String keywords, Pageable pageable);
	
	@Query("SELECT p1, p2 FROM Product p1 JOIN p1.category p2 WHERE p1.id =:id")
	Product findByMyId(@Param("id") int id);
	
	@Query("SELECT p FROM Product p WHERE p.user.id =:userId")
	public List<Product> findProducts(@Param("userId") int userId);

	@Query("SELECT p FROM Product p WHERE p.quantity>0 AND p.user.id =:userId")
	List<Product> findCurrentProducts(@Param("userId") int id);

	@Query("SELECT p FROM Product p WHERE p.category.name =:category")
	Page<Product> searchProductsByCategory(@Param("category") Optional<String> category, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category.name =:category AND p.name LIKE CONCAT('%',:search,'%')")
	Page<Product> searchProductsByKeywordAndCategory(@Param("search") Optional<String> search, @Param("category") Optional<String> category, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.user.id =:userId")
	Page<Product> findProducts(@Param("userId") int userId, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.quantity>0 AND p.user.id =:userId")
	Page<Product> findCurrentProducts(@Param("userId") int userId, Pageable page);
	
	@Query("SELECT p FROM Product p WHERE p.user.id =:userId")
	public Page<Product> findSimilarProducts(@Param("userId") int userId, Pageable page);
	
	@Query("SELECT COUNT(p.id) FROM Product p WHERE p.user.id =:userId")
	public int numOfMyProd(@Param("userId") int userId);
}
