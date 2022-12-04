package com.nnk.springboot.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public ModelAndView login() {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.html");
		log.info("Login page is well displayed");
		return mav;
	}

	@PostMapping("/login/validate")
	public String validatePassword(@Valid @ModelAttribute User userToValidate,  BindingResult result, Model model) {
		//check password valid + display the message thymeleaf "error on user name and/or password"
		Iterable<User> usersInDatabase = userService.getUsers() ;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		for(User userInDatabase : usersInDatabase) {
			if(userToValidate.getUsername().compareTo(userInDatabase.getUsername())==0 && encoder.matches(userToValidate.getPassword(), userInDatabase.getPassword())) {
				log.info("User is correctly identified.");
				return "redirect:/bidList/list";	
			}
		}
		log.error("User made a mistake in his username or password and is not identified.");
		model.addAttribute("message", "Invalid email and/or password"); 
		return "/login";
	}
}