package com.ecomerce.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomerce.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("Select u from Category u where u.name = :name")
	Category findByName(@Param("name") String name);

}
