package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
@Service
public class RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	/**
	 * get all ratings
	 * @return list of ratings
	 */
	public Iterable<Rating> getRatings() {
		return ratingRepository.findAll();
	}
	
	/**
	 * get rating by id
	 * @param id
	 * @return rating
	 */
	public Optional<Rating> getRatingById(Integer id) {
		return ratingRepository.findById(id);
	}
	
	/**
	 * save rating
	 * @param rating
	 * @return rating
	 */
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	/**
	 * delete rating
	 * @param rating
	 */
	public void deleteRating(Rating rating) {
		ratingRepository.delete(rating);
	}
}