package com.example.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Address;
import com.example.entity.Category;
import com.example.entity.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
Category findByName(String name);

}
