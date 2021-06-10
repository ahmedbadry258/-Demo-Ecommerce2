package com.example.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repo.CategoryRepository;
import com.example.repo.ProductRepository;


@Controller
@Transactional
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Product>products=productRepository.findAll();
		model.addAttribute("products",products );
		return "products/index";
	}
	@GetMapping("/aboutus")
	public String aboutUs() {
		return "about_us";
	}

	@GetMapping("/{name}")
	public String getByCategory(@PathVariable("name")String name,Model model) {
		System.out.println(name);
		Category c=categoryRepository.findByName(name);
		List<Product> products=productRepository.findByCategory(c);
		model.addAttribute("products",products );
		return"products/index";
	}
	
}
