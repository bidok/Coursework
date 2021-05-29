package com.example.demo.controller.ui;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * @author : Vasyl Bidiak
 * @created : 29.05.2021, суббота
 * @className : SecurityController
 **/
@Controller
public class SecurityController {
	@Autowired
	UserRepository userRepo;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("roles", Role.values());
		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(User user, Model model) {
		User userFromDb = userRepo.findByUsername(user.getUsername());

		if (userFromDb != null) {
			model.addAttribute("msg", "user are exist");
			return "registration";
		}

		user.setActive(true);
		user.setRoles(Collections.singleton(Role.ROLE_USER));
		userRepo.save(user);

		return "redirect:/login";
	}
}
