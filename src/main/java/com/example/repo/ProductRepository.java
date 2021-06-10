package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;
import com.example.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	List<Product> findByCategory(Category category);
}
