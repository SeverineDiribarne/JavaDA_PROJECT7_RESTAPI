package com.nnk.springboot.controllers;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class LoginController {

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public ModelAndView login() {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.html");
		log.info("Login page is well displayed");
		return mav;
	}
}