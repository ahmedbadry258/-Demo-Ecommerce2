package com.example.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repo.CategoryRepository;
import com.example.repo.ItemRepository;
import com.example.repo.ProductRepository;
import com.example.repo.UserRepository;

@Controller
public class AdminController {
	
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/management")
	public String management() {
		return "management";
	}
	
	@GetMapping("/management/product")
	public String productForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("category", new Category());
		model.addAttribute("products",productRepository.findAll() );
		return "product";
	}
	@PostMapping("/management/product/save")
	public String addProduct(@Valid @ModelAttribute("product")Product product,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			model.addAttribute("products",productRepository.findAll() );
			return "product";
		}
		productRepository.save(product);
		model.addAttribute("products",productRepository.findAll() );
		model.addAttribute("msg", "Product Saved Successfully");
		model.addAttribute("categories", categoryRepository.findAll());
		return"product";
	}
	
	@GetMapping("/management/category")
	public String categoryForm(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryRepository.findAll());
		return "category_form";
	}
	@PostMapping("/management/category/save")
	public String saveCategory(@Valid @ModelAttribute ("category") Category category,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());	
			return "category_form";
		}
		categoryRepository.save(category);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("msg", "Add Category Successfully");
		return "category_form";
	}
	
	@GetMapping("/management/users")
	public String users(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "users";
	}
	
	@GetMapping("/management/orders")
	public String orders(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "orders";
	}

	

}
