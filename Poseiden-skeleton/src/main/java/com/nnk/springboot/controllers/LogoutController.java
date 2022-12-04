package com.nnk.springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LogoutController {

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogoutController.class);
	
	 @GetMapping("/app-logout")
	    public ModelAndView logout() {
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("/logout");
	        log.info("disconnection was successful");
	        return mav;
	    }
}
