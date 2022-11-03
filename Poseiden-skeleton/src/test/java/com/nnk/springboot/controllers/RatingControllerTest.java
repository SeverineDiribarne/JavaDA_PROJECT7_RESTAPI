package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

	@InjectMocks
	private RatingController ratingController;

	private MockMvc mockMvc;
	
	@MockBean
	RatingService ratingService;

	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}
	
	private Model modelTest = new ConcurrentModel();

	private static Rating ratingForMock() {
		Rating rating4 = new Rating();
		rating4.setId(4);
		rating4.setMoodysRating("ModdysRating4 Test for Mock");
		rating4.setSandPRating("SandPRating4 test for mock");
		rating4.setFitchRating("FitchRating4 test");
		rating4.setOrderNumber(10);
		return rating4;
	}

	private List<Rating> listForMock() {

		Rating rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("ModdysRating1 Test for Mock");
		rating1.setSandPRating("SandPRating1 test for mock");
		rating1.setFitchRating("FitchRating1 test");
		rating1.setOrderNumber(6);
		

		Rating rating2 = new Rating();
		rating2.setId(2);
		rating2.setMoodysRating("ModdysRating2 Test for Mock");
		rating2.setSandPRating("SandPRating2 test for mock");
		rating2.setFitchRating("FitchRating2 test");
		rating2.setOrderNumber(2);
		
		Rating rating3 = new Rating();
		rating3.setId(3);
		rating3.setMoodysRating("ModdysRating3 Test for Mock");
		rating3.setSandPRating("SandPRating3 test for mock");
		rating3.setFitchRating("FitchRating3 test");
		rating3.setOrderNumber(5);
		

		List<Rating> mockedList = new ArrayList<>();
		mockedList.add(rating1);
		mockedList.add(rating2);
		mockedList.add(rating3);

		return mockedList;
	}
	
	@Test
	void testShouldGetRating() throws Exception{
		//given
		when(ratingService.getRatings()).thenReturn(listForMock());

		//when
		String urlResult = ratingController.home(modelTest);
		Object attribute =  modelTest.getAttribute("ratings");

		//then
		Iterable<?> ratingResults = null;
		if(attribute instanceof Iterable<?>) {
			ratingResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of ratingResult");
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/rating/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("rating/list"));
		
		Iterator<?> ratingResultsIterator = ratingResults.iterator();
		Iterator<Rating> ratingExpectedIterator = listForMock().iterator();

		while(ratingResultsIterator.hasNext()){

			Rating ratingResult = (Rating) ratingResultsIterator.next();
			Rating ratingExpected =  ratingExpectedIterator.next();

			Assertions.assertEquals(ratingResult.getId(), ratingExpected.getId());
			Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingExpected.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingExpected.getSandPRating()));
			Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingExpected.getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(),ratingExpected.getOrderNumber());
		}
		Assertions.assertEquals(0, urlResult.compareTo("rating/list"));
	}
	
	static class AddRatingFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			Rating rating4 = ratingForMock();
			return Stream.of(Arguments.of(rating4));
		}
	}

	@ParameterizedTest
	@ArgumentsSource(AddRatingFormArgumentsProvider.class)
	void testShouldAddRatingForm(Rating ratingForTest) throws Exception{
		//given
		String urlResult = ratingController.addRatingForm(ratingForTest, modelTest);

		Object attribute =  modelTest.getAttribute("rating");

		//then
		Rating ratingResult = null;
		if(attribute instanceof Rating) {
			ratingResult = (Rating) attribute;
		}
		else {
			Assertions.fail("Bad type of ratingResult");
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/rating/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("rating/add"));
		Assertions.assertEquals(ratingResult.getId() , ratingForTest.getId());
		Assertions.assertEquals(ratingResult.getMoodysRating() , ratingForTest.getMoodysRating());
		Assertions.assertEquals(ratingResult.getSandPRating() , ratingForTest.getSandPRating());
		Assertions.assertEquals(ratingResult.getFitchRating() , ratingForTest.getFitchRating());
		Assertions.assertEquals(ratingResult.getOrderNumber() , ratingForTest.getOrderNumber());


		Assertions.assertEquals(0, urlResult.compareTo("rating/add"));	
	}

	@Test
	void testShouldValidateRating() throws Exception{
		//given
		//When
		when(ratingService.saveRating(any())).thenReturn(new Rating());
		//then
		mockMvc.perform(
				post("/rating/validate")
				.param("id","1")
				.param("term","10.0")
				.param("value","10.0")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("rating/list"))
		;

	}

	@Test
	void testShouldShowUpdateForm() throws Exception {
		//given
		//When
		when(ratingService.getRatingById(any())).thenReturn(Optional.of(ratingForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/rating/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("rating/update"));
	}

	@Test
	void testShouldUpdateRating() throws Exception {
		//given
		//when	
		when(ratingService.getRatingById(any())).thenReturn(Optional.of(ratingForMock()));
		when(ratingService.saveRating(any())).thenReturn(new Rating());
		//then
		mockMvc.perform(
				post("/rating/update/{id}",4)
				.param("id","1")
				.param("term","10.0")
				.param("value","10.0")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/rating/list"));
	}

	@Test
	void testShouldDeleteRating() throws Exception {
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/rating/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/rating/list"));
	}
}
