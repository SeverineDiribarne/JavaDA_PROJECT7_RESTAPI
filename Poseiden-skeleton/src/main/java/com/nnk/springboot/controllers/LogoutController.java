package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LogoutController {

	private static final Logger log = LogManager.getLogger(); 
	
	 @GetMapping("/app-logout")
	    public ModelAndView logout() {
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("/logout");
	        //TODO a verifier ce que fait cette methode pour bien adapter le message
	        log.info("disconnection was successful");
	        return mav;
	    }
}
