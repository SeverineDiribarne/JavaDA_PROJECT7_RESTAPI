package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {

	@Autowired
	RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model)
	{
		// find all Rating, add to model
		Iterable<Rating> ratings = ratingService.getRatings();
		model.addAttribute("ratings", ratings);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating, Model model) {
		model.addAttribute("rating", rating);
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// check data valid and save to db, after saving return Rating list
		if(result.hasErrors()) {
			return "rating/add";
		}
		ratingService.saveRating(rating);
		model.addAttribute(rating);
		model.addAttribute("ratings", rating);
		return "rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// get Rating by Id and to model then show to the form
		Rating ratingFoundById = ratingService.getRatingById(id).get();
		model.addAttribute(ratingFoundById);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
			BindingResult result, Model model) {
		// check required fields, if valid call service to update Rating and return Rating list
		Rating ratingFoundById = ratingService.getRatingById(id).get();
		ratingFoundById.setMoodysRating(rating.getMoodysRating());
		ratingFoundById.setSandPRating(rating.getSandPRating());
		ratingFoundById.setFitchRating(rating.getFitchRating());
		ratingFoundById.setOrderNumber(rating.getOrderNumber());
		ratingService.saveRating(ratingFoundById);
		model.addAttribute("ratingList", ratingFoundById);
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		// Find Rating by Id and delete the Rating, return to Rating list
		ratingService.deleteRatingById(id);
		Iterable<Rating> ratings = ratingService.getRatings();
		model.addAttribute(ratings);
		return "redirect:/rating/list";
	}
}
