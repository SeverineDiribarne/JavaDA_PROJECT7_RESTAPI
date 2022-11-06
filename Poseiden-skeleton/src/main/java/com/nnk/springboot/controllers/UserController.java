package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.utils.ValidityPasswordRules;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserService userService;


	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	private static final String USERS = "users";
	private static final String REDIRECT_USER_LIST = "redirect:/user/list";


	@RequestMapping("/user/list")
	public String home(Model model){
		model.addAttribute(USERS, userService.getUsers());
		log.info("all users are found and returned to view");
		return "user/list";
	}

	@GetMapping("/user/add")
	public String addUser(User user, Model model) {
		model.addAttribute(user);
		log.info("The display of the addUser page of an user is functional");
		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {

		ValidityPasswordRules validityPasswordRules = userService.validatePassword(user);
		String errorMessage = userService.buildErrorMessage(validityPasswordRules);
		if(!errorMessage.isEmpty()) {
			model.addAttribute("msg", errorMessage);
			log.info("The validation of the user as well as its backup in the database could not be carried out");
			log.info("There is an error in validating the password and the error message is indicated in the addUser page");
			return "redirect:/user/add";
		} else {
			if (!result.hasErrors()) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(user.getPassword()));
				userService.saveUser(user);
				model.addAttribute(USERS, userService.getUsers());
				log.info("The validation of the user is carried out as well as the backup in the database");
				return REDIRECT_USER_LIST;
			}
		}
		return "redirect:/login";
	}	

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		log.info("the update of the user found by its id has been carried out");
		return "user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			log.info("user update could not be performed");
			return "user/update";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userService.saveUser(user);
		model.addAttribute(USERS, userService.getUsers());
		log.info("The update of the user has been carried out");
		return REDIRECT_USER_LIST;
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.deleteUser(user);
		model.addAttribute(USERS, userService.getUsers());
		log.info("The user found by its id has been deleted from the list");
		return REDIRECT_USER_LIST;
	}
}
