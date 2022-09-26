package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static final Logger log = LogManager.getLogger(); 
	
	@RequestMapping("/")
	public String home(Model model)
	{
		log.info("The home page is displayed correctly");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		log.info("The administrator's home page is displayed correctly");
		return "redirect:/bidList/list";
	}


}
