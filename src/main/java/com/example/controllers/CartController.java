package com.example.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.entity.Address;
import com.example.entity.Item;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repo.AddressRepository;
import com.example.repo.ItemRepository;
import com.example.repo.MailService;
import com.example.repo.ProductRepository;
import com.example.repo.UserRepository;

@Controller
public class CartController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private MailService mailService;

	@GetMapping("/cart")
	public String cartPage(HttpSession session, Model model) {

		if (session.getAttribute("cart") == null) {
			return "cart/index";
		} else {
			List<Product> cart = (List<Product>) session.getAttribute("cart");
			double total = this.calculateTotal(cart);
			model.addAttribute("total", total);
		}
		return "cart/index";
	}

	@GetMapping("/cart/{id}")
	public String getProduct(@PathVariable("id") int id, HttpSession session) {
		if (session.getAttribute("cart") == null) {
			List<Product> cart = new ArrayList<Product>();
			cart.add(productRepository.findById(id).get());
			session.setAttribute("cart", cart);
		} else {
			List<Product> cart = (List<Product>) session.getAttribute("cart");
			cart.add(productRepository.findById(id).get());
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/";
	}

	@GetMapping("/remove/{id}")
	public String removeProduct(@PathVariable("id") int id, HttpSession session) {
		System.out.println(id);
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		int index = this.isExist(id, cart);
		System.out.println(index);
		cart.remove(index);
		System.out.println("Removed");
		session.setAttribute("cart", cart);
		return "redirect:/cart/";
	}

	private int isExist(int id, List<Product> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public double calculateTotal(List<Product> cart) {
		double total = 0;
		for (Product i : cart) {
			total += i.getPrice();
		}
		return total;
	}

	@PostMapping("/profile")
	public String orderDone(@ModelAttribute("address") Address address, Principal principal, HttpSession session,
			Model model) {
		List<Product> ps = new ArrayList<Product>();
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		for (Product product : cart) {
			System.out.println(product.getName());
			ps.add(productRepository.findById(product.getId()).get());
		}
		Item item = new Item();
		item.setName("New Order");
		item.setOrderDate(new Date());
		item.setAddress(addressRepository.save(address));
		item.setUser(userRepository.findByEmail(principal.getName()));
		item.setQuantity(1);

		item.setProducts(ps);
		item.setTotal(this.calculateTotal(cart));

		Item item2 = itemRepository.save(item);
		System.out.println("saved : " + item2.getId());
		int orderId = item2.getId();
		model.addAttribute("orderId", orderId);
		session.removeAttribute("cart");
		User user = userRepository.findByEmail(principal.getName());
		System.out.println(user.getId());
		List<Item> items = itemRepository.findByUser(userRepository.findByEmail(principal.getName()));
		
	

		mailService.forBuyingProduct(user.getEmail(), user.getFullname());
		model.addAttribute("items", items);
		return "profile";
	}

	@GetMapping("/payCredit")
	public String payment() {
		return "credit";
	}

	@GetMapping("/cart/checkout")
	public String checkOut(Model model,HttpSession session) {
		List<Product> ps = new ArrayList<Product>();
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		//Calculate Total 
		double total=this.calculateTotal(cart);
		model.addAttribute("total", total);
		System.out.println("check out");
		model.addAttribute("address", new Address());
		return "cart/address";
	}

}
