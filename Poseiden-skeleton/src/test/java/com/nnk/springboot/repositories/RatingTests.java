package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;

	@Test 
	void ratingTest() {
		Rating rating = new Rating(1, "MoodysRating", "SandPRating", "FitchRating", 10);
		// Save
		rating = ratingRepository.save(rating);
		Assertions.assertEquals(1, rating.getId());
		Assertions.assertEquals(10, rating.getOrderNumber() );

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		Assertions.assertEquals(20, rating.getOrderNumber());

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		Assertions.assertFalse(ratingList.isPresent());
	}
}
