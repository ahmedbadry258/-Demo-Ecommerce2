package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Item;
import com.example.entity.Product;
import com.example.entity.User;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	 List<Item> findByName(String name);
	 List<Item> findByUser(User user);
	 

}
