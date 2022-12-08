package com.nnk.springboot.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

	private static final Integer RATING_ID = 1;

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	RatingService ratingService = new RatingService();

	public List<Rating> listForMock() {

		Rating rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("moodysRating1 Test for mock");
		rating1.setSandPRating("sandPRating1 Test for mock");
		rating1.setFitchRating("fitchRating1 Test for mock");
		rating1.setOrderNumber(1);

		Rating rating2 = new Rating();
		rating2.setId(2);
		rating2.setMoodysRating("moodysRating2 Test for mock");
		rating2.setSandPRating("sandPRating2 Test for mock");
		rating2.setFitchRating("fitchRating2 Test for mock");
		rating2.setOrderNumber(2);

		Rating rating3 = new Rating();
		rating3.setId(3);
		rating3.setMoodysRating("moodysRating3 Test for mock");
		rating3.setSandPRating("sandPRating3 Test for mock");
		rating3.setFitchRating("fitchRating3 Test for mock");
		rating3.setOrderNumber(3);

		List<Rating> mockedList = new ArrayList<>();
		mockedList.add(rating1);
		mockedList.add(rating2);
		mockedList.add(rating3);

		return mockedList;
	}

	/**
	 * Call GetRatings method and verify that elements are presents
	 * @return all list of curvePoint
	 */
	@Test
	void testGetRatings() {
		//Given
		when(ratingRepository.findAll()).thenReturn(listForMock());

		//When
		Iterable<Rating> ratingResults = ratingService.getRatings();

		//Then
		Iterator<Rating> ratingResultsIterator = ratingResults.iterator();
		Iterator<Rating> ratingExpectedIterator = listForMock().iterator();

		while(ratingResultsIterator.hasNext()){
			Rating ratingResult = ratingResultsIterator.next();
			Rating ratingExpected =  ratingExpectedIterator.next();
			Assertions.assertEquals(ratingResult.getId(),ratingExpected.getId());
			Assertions.assertEquals(0,ratingResult.getMoodysRating().compareTo(ratingExpected.getMoodysRating()));
			Assertions.assertEquals(0,ratingResult.getSandPRating().compareTo(ratingExpected.getSandPRating()));
			Assertions.assertEquals(0,ratingResult.getFitchRating().compareTo(ratingExpected.getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(),(ratingExpected.getOrderNumber()));
		}
	}

	/**
	 * Method FindById
	 * @param id
	 * @return rating
	 */
	private Optional<Rating> findById(Integer id){
		for(Rating rating :listForMock()) {
			if(rating.getId()== id.intValue()) {
				return Optional.of(rating);
			}
		}
		return Optional.empty();
	}

	/**
	 * Call GetRatingById method and verify that the element is the correct one
	 * @param id
	 * @return rating by his id
	 */
	@Test
	void testGetRatingById() {
		//Given
		when(ratingRepository.findById(RATING_ID)).thenReturn(findById(RATING_ID));

		//When
		Optional<Rating> ratingResult = ratingService.getRatingById(RATING_ID);

		//Then
		Assertions.assertEquals(true, ratingResult.isPresent());
		Assertions.assertEquals(RATING_ID.intValue(), ratingResult.get().getId());
		Assertions.assertEquals("moodysRating1 Test for mock", ratingResult.get().getMoodysRating());
		Assertions.assertEquals("sandPRating1 Test for mock", ratingResult.get().getSandPRating());
		Assertions.assertEquals("fitchRating1 Test for mock", ratingResult.get().getFitchRating());
		Assertions.assertEquals(1,ratingResult.get().getOrderNumber());
	}

	/**
	 * Call saveRating method and verify element is saved in DB
	 * @param rating
	 * @return save or update rating in BDD
	 */
	@Test
	void testSaveRating() throws Exception {
		//Given
		Rating rating4 = new Rating();
		rating4.setId(4);
		rating4.setMoodysRating("moodysRating1 Test for mock");
		rating4.setSandPRating("sandPRating1 Test for mock");
		rating4.setFitchRating("fitchRating1 Test for mock");
		rating4.setOrderNumber(4);

		//When
		ArgumentCaptor<Rating> capturedRatingWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Rating.class);
		when(ratingRepository.save(capturedRatingWhenSaveMethodIsCalled.capture())).thenReturn(rating4);
		ratingService.saveRating(rating4);

		//Then
		Rating capturedRating = capturedRatingWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedRating.getId());
		Assertions.assertEquals("moodysRating1 Test for mock", capturedRating.getMoodysRating());
		Assertions.assertEquals("sandPRating1 Test for mock", capturedRating.getSandPRating());
		Assertions.assertEquals("fitchRating1 Test for mock", capturedRating.getFitchRating());
		Assertions.assertEquals(4, capturedRating.getOrderNumber());
	}	

	/**
	 * Call deleteRating method and verify element don't exist
	 */
	@Test
	void testDeleteRating() {
		//Given
		Rating rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("moodysRating1 Test for mock");
		rating1.setSandPRating("sandPRating1 Test for mock");
		rating1.setFitchRating("fitchRating1 Test for mock");
		rating1.setOrderNumber(1);

		//When
		ArgumentCaptor<Rating> capturedRatingWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(Rating.class);
		Mockito.doNothing().when(ratingRepository).delete(capturedRatingWhenDeleteMethodIsCalled.capture());
		ratingService.deleteRating(rating1);

		//Then
		Rating capturedRating = capturedRatingWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedRating.getId());
		Assertions.assertEquals("moodysRating1 Test for mock", capturedRating.getMoodysRating());
		Assertions.assertEquals("sandPRating1 Test for mock", capturedRating.getSandPRating());
		Assertions.assertEquals("fitchRating1 Test for mock",  capturedRating.getFitchRating());
		Assertions.assertEquals(1,  capturedRating.getOrderNumber());
	}
}