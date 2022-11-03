package com.nnk.springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {
	
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/*")
	public String home(Model model){
		log.info("The home page is displayed correctly");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model){
		log.info("The administrator's home page is displayed correctly");
		return "redirect:/bidList/list";
	}
}
