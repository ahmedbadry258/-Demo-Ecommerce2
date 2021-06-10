package com.example.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Category;
import com.example.repo.CategoryRepository;
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	


}
