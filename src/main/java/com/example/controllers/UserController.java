package com.example.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Address;
import com.example.entity.Item;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repo.ItemRepository;
import com.example.repo.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	

	
	@GetMapping("/test")
	public String userHome(Model model) {
		model.addAttribute("user", new User());
		return "user/index";
	}
	@GetMapping("/register")
	public String pageRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	@PostMapping("/adduser")
	public String addUser(@Valid @ModelAttribute("user") User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return"register";
		}
		user.setRegiserDate(new Date());
		user.setActive(true);
		user.setRole("USER");
		userRepository.save(user);
		model.addAttribute("msg", "Registeration Successfully");
		return "login";
	}
	
	@GetMapping("/showOrder/{id}")
	public String showOrder(@PathVariable("id") int id,Model model) {
		Item item=itemRepository.findById(id).get();
		List<Product> products=item.getProducts();
		model.addAttribute("products", products);
		return "showOrder";
	}
	
	@GetMapping("/myOrders")
	public String getMyOrders(Model model, Principal principal) {

		List<Item> items = itemRepository.findByUser(userRepository.findByEmail(principal.getName()));
		for (Item i : items) {
			System.out.println(i.getId());
		}
		model.addAttribute("items", items);

		return "my_orders";
	}

	@GetMapping("/profile")
	public String getProfile(Model model, Principal principal) {

		List<Item> items = itemRepository.findByUser(userRepository.findByEmail(principal.getName()));
		for (Item i : items) {
			System.out.println(i.getId());
		}
		model.addAttribute("items", items);

		return "profile";
	}

	
	
}
