package com.example.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.entity.Contact;
import com.example.repo.ContactRepository;
import com.example.repo.MailService;
import com.example.repo.UserRepository;

@Controller
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;
	
	@GetMapping("/contact")
	public String pageContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact";
	}
	@PostMapping("/successContact")
	public String saveContact(@ModelAttribute("contact") Contact contact,Model model,HttpServletRequest request) {
		String name=(String) request.getParameter("Name");
		String email=(String) request.getParameter("Email");
		System.out.println("Name Is"+ name +"Email Is" + email);
		contact.setEmail(email);
		contact.setFullname(name);
		Contact con=contactRepository.save(contact);
		mailService.forContact(email,name);
		model.addAttribute("id", con.getId());
		return "success_contact";
	}

}
