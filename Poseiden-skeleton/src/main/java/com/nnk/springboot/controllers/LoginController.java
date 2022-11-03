package com.nnk.springboot.controllers;


import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.LoginService;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);

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
		return "/login";
	}
	
//	@RequestMapping("/*")
//	public String getGithub() {
//		return "welcome, github user";
//	}
}