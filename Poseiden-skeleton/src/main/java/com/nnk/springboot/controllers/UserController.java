package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
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
    private UserRepository userRepository;
    
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    
    private static final String USERS = "users";
    private static final String REDIRECT_USER_LIST = "redirect:/user/list";


    @RequestMapping("/user/list")
    public String home(Model model){
        model.addAttribute(USERS, userRepository.findAll());
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
        if (!result.hasErrors()) {
        	log.info("The validation of the user as well as its backup in the database could not be carried out");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute(USERS, userRepository.findAll());
            return REDIRECT_USER_LIST;
        }
        log.info("The validation of the user is carried out as well as the backup in the database");
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
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
        userRepository.save(user);
        model.addAttribute(USERS, userRepository.findAll());
        log.info("The update of the user has been carried out");
        return REDIRECT_USER_LIST;
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute(USERS, userRepository.findAll());
        log.info("The user found by its id has been deleted from the list");
        return REDIRECT_USER_LIST;
    }
}
