package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Slf4j
@Controller
public class RatingController {

	@Autowired
	RatingService ratingService;
	
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RatingController.class);

	@RequestMapping("/rating/list")
	public String home(Model model)
	{
		// find all Rating, add to model
		Iterable<Rating> ratings = ratingService.getRatings();
		model.addAttribute("ratings", ratings);
		log.info("all ratings are found and returned to view");
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating, Model model) {
		model.addAttribute("rating", rating);
		log.info("The display of the addRating page of a bid is functional");
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// check data valid and save to db, after saving return Rating list
		if(result.hasErrors()) {
			log.info("The validation of the rating as well as its backup in the database could not be carried out");
			return "rating/add";
		}
		ratingService.saveRating(rating);
		model.addAttribute(rating);
		model.addAttribute("ratings", rating);
		log.info("The validation of the rating is carried out as well as the backup in the database");
		return "rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// get Rating by Id and to model then show to the form
		Rating ratingFoundById = ratingService.getRatingById(id).get();
		model.addAttribute(ratingFoundById);
		log.info("the update of the rating found by its id has been carried out");
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
			BindingResult result, Model model) {
		// check required fields, if valid call service to update Rating and return Rating list
		if(result.hasErrors()) {
			log.info("rating update could not be performed");
			return "rating/update" ;    	
		}
		Rating ratingFoundById = ratingService.getRatingById(id).get();
		ratingFoundById.setMoodysRating(rating.getMoodysRating());
		ratingFoundById.setSandPRating(rating.getSandPRating());
		ratingFoundById.setFitchRating(rating.getFitchRating());
		ratingFoundById.setOrderNumber(rating.getOrderNumber());
		ratingService.saveRating(ratingFoundById);
		log.info("The update of the rating has been carried out");
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		// Find Rating by Id and delete the Rating, return to Rating list
		ratingService.deleteRatingById(id);
		Iterable<Rating> ratings = ratingService.getRatings();
		model.addAttribute(ratings);
		log.info("The rating found by its id has been deleted from the list");
		return "redirect:/rating/list";
	}
}
