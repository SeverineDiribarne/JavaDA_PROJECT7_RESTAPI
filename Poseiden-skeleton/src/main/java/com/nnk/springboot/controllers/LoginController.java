package com.nnk.springboot.controllers;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.LoginService;
import com.nnk.springboot.utils.ValidityPasswordRules;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static final Logger log = LogManager.getLogger(); 

//	private final OAuth2AuthorizedClientService authorizedClientService;
//
//	public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
//		this.authorizedClientService = authorizedClientService;
//	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login");
		log.info("The login page is well displayed");
		return mav;
	}

	@PostMapping("/login/validate")
	public String validatePassword(@Valid @ModelAttribute User user, Model model) {
		// check password valid
		ValidityPasswordRules validityPasswordRules = loginService.validatePassword(user);

		String errorMessage = loginService.buildErrorMessage(validityPasswordRules);
		if(!errorMessage.isEmpty()) {
			model.addAttribute("msg", errorMessage);
			log.info("There is an error in validating the password and the error message is indicated in the login page");
			return "redirect:/login";
		}
		else {
			log.info("The password validation was successful and the list of bids is displayed");
			return "redirect:/bidList/list";
		}
	}

}
