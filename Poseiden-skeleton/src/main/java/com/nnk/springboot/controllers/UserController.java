package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.utils.ValidityPasswordRules;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
	public static final String MESSAGE_FULLNAME = "messageFullName";
	public static final String MESSAGE_USERNAME =  "messageUserName";
	public static final String MESSAGE_ROLE = "messageRole";
	public static final String USER_ADD="/user/add";
	public static final String USER_UPDATE = "/user/update";


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
		return USER_ADD;
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User userToValidate, BindingResult result, Model model) {

		//Check if the last name or first name is not empty
		if(userToValidate.getFullname().isEmpty()) {
			model.addAttribute(MESSAGE_FULLNAME, "Your fullname is empty");
			return USER_ADD ;
		}
		//	 	Check if the user name or first name is not empty
		if(userToValidate.getUsername().isEmpty()) {
			model.addAttribute(MESSAGE_USERNAME, "Your username is empty");
			return USER_ADD;
		}
		//Check that the radio box is checked
		if(userToValidate.getRole()==null) {
			model.addAttribute(MESSAGE_ROLE, "Your role is empty.");
			return USER_ADD;
		}
		// Check if the user does not exist in the database 
		Iterable<User> usersInDatabase = userService.getUsers();
		if (!userToValidate.getFullname().isEmpty() && (!userToValidate.getUsername().isEmpty())) {
			for(User userInDatabase : usersInDatabase) {
				if ((userToValidate.getUsername().compareTo(userInDatabase.getUsername())==0) && (userToValidate.getFullname().compareTo(userInDatabase.getFullname())==0)) {
					model.addAttribute(MESSAGE_USERNAME, "Your username already exists. ");
					model.addAttribute(MESSAGE_FULLNAME, "Your fullname already exists.");
					return USER_ADD;
				}
			}
		}
		// Verify password rules
		ValidityPasswordRules validityPasswordRules = userService.validatePassword(userToValidate);
		List<String> errorMessages = userService.buildErrorMessage(validityPasswordRules);
		if(!errorMessages.isEmpty()) {
			model.addAttribute("headerMessage", "Your password:");
			model.addAttribute("messages", errorMessages);
			log.error("The validation of the user as well as its backup in the database could not be carried out");
			log.error("There is an error in validating the password and the error message is indicated in the addUser page");
			return USER_ADD;
		} else {
			if (!result.hasErrors()) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				userToValidate.setPassword(encoder.encode(userToValidate.getPassword()));
				userService.saveUser(userToValidate);
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
		return USER_UPDATE;
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User userToValidate,
			BindingResult result, Model model) {
		// Check if the last name or first name is not empty 
		if(userToValidate.getFullname().isEmpty()) {
			model.addAttribute(MESSAGE_FULLNAME, "Your fullname is empty.");
			return USER_UPDATE;
		}
		// Check if the last name or first name is not empty and 
		if(userToValidate.getUsername().isEmpty()) {
			model.addAttribute(MESSAGE_USERNAME, "Your username is empty.");
			return USER_UPDATE;
		} 
		//Check that the radio box is checked
		if(userToValidate.getRole()==null) {
			model.addAttribute(MESSAGE_ROLE, "Your role is empty.");
			return USER_UPDATE;
		}
		// Verify password rules
		ValidityPasswordRules validityPasswordRules = userService.validatePassword(userToValidate);
		List<String> errorMessages = userService.buildErrorMessage(validityPasswordRules);
		if(!errorMessages.isEmpty()) {
			model.addAttribute("headerMessage", "Your password:");
			model.addAttribute("messages", errorMessages);
			log.error("The validation of the user as well as its backup in the database could not be carried out");
			log.error("There is an error in validating the password and the error message is indicated in the addUser page");
			return USER_UPDATE;
		} else {
			if (!result.hasErrors()) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				userToValidate.setPassword(encoder.encode(userToValidate.getPassword()));
				userToValidate.setId(id);
				userService.saveUser(userToValidate);
				model.addAttribute(USERS, userService.getUsers());
				log.info("The update of the user has been carried out");
				return REDIRECT_USER_LIST;
			}
		}
		return "redirect:/user/update";
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
