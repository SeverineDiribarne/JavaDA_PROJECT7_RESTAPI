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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
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
	
	private BindingResult resultTest = new  BeanPropertyBindingResult(ratingController, "ratingForTest");
	private Model modelTest = new ConcurrentModel();

	private static Rating ratingForMock() {
		Rating rating4 = new Rating();
		rating4.setId(4);
		rating4.setMoodysRating("ModdysRating4 Test for mock");
		rating4.setSandPRating("SandPRating4 Test for mock");
		rating4.setFitchRating("FitchRating4 Test for mock");
		rating4.setOrderNumber(4);
		return rating4;
	}

	private List<Rating> listForMock() {

		Rating rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("MoodysRating1 Test for mock");
		rating1.setSandPRating("SandPRating1 Test for mock");
		rating1.setFitchRating("FitchRating1 Test for mock");
		rating1.setOrderNumber(1);
		

		Rating rating2 = new Rating();
		rating2.setId(2);
		rating2.setMoodysRating("ModdysRating2 Test for mock");
		rating2.setSandPRating("SandPRating2 Test for mock");
		rating2.setFitchRating("FitchRating2 Test for mock");
		rating2.setOrderNumber(2);
		
		Rating rating3 = new Rating();
		rating3.setId(3);
		rating3.setMoodysRating("ModdysRating3 Test for mock");
		rating3.setSandPRating("SandPRating3 Test for mock");
		rating3.setFitchRating("FitchRating3 Test for mock");
		rating3.setOrderNumber(3);
		

		List<Rating> mockedList = new ArrayList<>();
		mockedList.add(rating1);
		mockedList.add(rating2);
		mockedList.add(rating3);

		return mockedList;
	}
	
	//Functional test shouldGetRating
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
			Iterator<?> ratingResultsIterator = ratingResults.iterator();
			Iterator<Rating> ratingExpectedIterator = listForMock().iterator();

			while(ratingResultsIterator.hasNext()){

				Rating ratingResult = (Rating) ratingResultsIterator.next();
				Rating ratingExpected =  ratingExpectedIterator.next();

				Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingExpected.getMoodysRating()));
				Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingExpected.getSandPRating()));
				Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingExpected.getFitchRating()));
				Assertions.assertEquals(ratingResult.getOrderNumber(),(ratingExpected.getOrderNumber()));
			}
			Assertions.assertEquals(0, urlResult.compareTo("rating/list"));
		}

		//EndPoint Test
		@Test
		void testShouldGetRatingForEndPoint() throws Exception{
			//given
			//when
			//then
			mockMvc.perform(MockMvcRequestBuilders
					.get("/rating/list")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isOk())
			.andExpect(view().name("rating/list"));
		}


		static class AddRatingFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				Rating rating4 = ratingForMock();
				return Stream.of(Arguments.of(rating4));
			}
		}
		
		//Functional Test shouldAddRatingForm
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

			Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingForTest.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingForTest.getSandPRating()));
			Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingForTest.getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(),(ratingForTest.getOrderNumber()));
		
			Assertions.assertEquals(0, urlResult.compareTo("rating/add"));	
		}

		//EndPoint Test
		@ParameterizedTest
		@ArgumentsSource(AddRatingFormArgumentsProvider.class)
		void testShouldAddRatingFormForEndPoint(Rating ratingForTest) throws Exception{
			//given
			//when
			//then
			mockMvc.perform(MockMvcRequestBuilders
					.get("/rating/add")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isOk())
			.andExpect(view().name("rating/add"));
		}

		//Functional Test ShouldValidateRating
		@ParameterizedTest
		@ArgumentsSource(AddRatingFormArgumentsProvider.class)
		void testShouldValidateRating(Rating ratingForTest) throws Exception{
			//given
			//when
			ArgumentCaptor<Rating> capturedRatingWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Rating.class);
			when(ratingService.saveRating(capturedRatingWhenSaveMethodIsCalled.capture())).thenReturn(new Rating());
			String urlResult = ratingController.validate(ratingForTest, resultTest, modelTest);
			Object attribute =  modelTest.getAttribute("ratings");

			//then
			Rating ratingResult = null;
			if(attribute instanceof Rating) {
				ratingResult = (Rating) attribute;
			}
			else {
				Assertions.fail("Bad type of ratingResult");
			}
			Rating ratingExpected = ratingForTest;

			Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingExpected.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingExpected.getSandPRating()));
			Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingExpected.getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(),(ratingExpected.getOrderNumber()));
			Assertions.assertEquals(0, urlResult.compareTo("redirect:/rating/list"));

			Rating capturedRating = capturedRatingWhenSaveMethodIsCalled.getValue();
			Assertions.assertEquals(4, capturedRating.getId());
			Assertions.assertEquals("ModdysRating4 Test for mock", capturedRating.getMoodysRating());
			Assertions.assertEquals("SandPRating4 Test for mock", capturedRating.getSandPRating());
			Assertions.assertEquals("FitchRating4 Test for mock", capturedRating.getFitchRating());
			Assertions.assertEquals(4, capturedRating.getOrderNumber());
		}

		//EndPoint Test
		@Test
		void testShouldValidateRatingForEndPoint() throws Exception{
			//given
			//When
			when(ratingService.saveRating(any())).thenReturn(new Rating());
			//then
			mockMvc.perform(
					post("/rating/validate")
					.param("moodysRating","MoodysRating1 Test for mock")
					.param("sandPRating","SandPRating1 Test for mock")
					.param("fitchRating","FitchRating1 Test for mock")
					.param("orderNumber","1")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isFound())
			.andExpect(view().name("redirect:/rating/list"));
		}

		//Error Test
		@Test
		void testShouldValidateRatingWithError() throws Exception{
			//given
			//When
			when(ratingService.saveRating(any())).thenReturn(new Rating());
			//then
			mockMvc.perform(
					post("/rating/validate")
					.param("moodysRating", "12")
					.param("SandPRating", "12")
					.param("fitchRating", "12")	
					.param("orderNumber","1.0" )
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isOk())
			.andExpect(view().name("rating/add"));
			
		}

		static class shouldShowUpdateFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				Rating rating4 = ratingForMock();
				return Stream.of(Arguments.of(rating4));
			}
		}

		//Functional Test shouldShowUpdateForm
		@ParameterizedTest
		@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
		void testShouldShowUpdateForm() throws Exception{
			//given
			Integer id = 4;
			when(ratingService.getRatingById(id)).thenReturn(Optional.of(ratingForMock()));

			String urlResult = ratingController.showUpdateForm( id, modelTest);
			Object attribute =  modelTest.getAttribute("rating");

			//then
			Rating ratingResult = null;
			if(attribute instanceof Rating) {
				ratingResult = (Rating) attribute;
			}
			else {
				Assertions.fail("Bad type of ratingResult");
			}

			Assertions.assertEquals(ratingResult.getId(), ratingForMock().getId());
			Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingForMock().getMoodysRating()));
			Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingForMock().getSandPRating()));
			Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingForMock().getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(), ratingForMock().getOrderNumber());

			Assertions.assertEquals(0, urlResult.compareTo("rating/update"));	
		}

		//EndPoint Test
		@Test
		void testShouldShowUpdateFormForEndPoint() throws Exception {
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

		//Functional test shouldUpdateRating
		@ParameterizedTest
		@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
		void testShouldUpdateRating(Rating ratingForTest) throws Exception{
			//given
			when(ratingService.getRatingById(any())).thenReturn(Optional.of(ratingForTest));
			ratingForTest.setMoodysRating("MoodysRating5 Test for mock");
			ratingForTest.setSandPRating("SandPRating5 Test for mock");
			ratingForTest.setFitchRating("FitchRating5 Test for mock");
			ratingForTest.setOrderNumber(5);
			when(ratingService.saveRating(any())).thenReturn(ratingForTest);

			ArgumentCaptor<Rating> capturedRatingWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Rating.class);
			when(ratingService.saveRating(capturedRatingWhenSaveMethodIsCalled.capture())).thenReturn(new Rating());
			
			String urlResult = ratingController.updateRating(4, ratingForTest, resultTest, modelTest);
			Object attribute =  modelTest.getAttribute("rating");
			//then
			Rating ratingResult = null;
			if(attribute instanceof Rating) {
				ratingResult = (Rating) attribute;
			}
			else {
				Assertions.fail("Bad type of ratingResult");
			}
			
			Assertions.assertEquals(ratingResult.getId(), ratingForTest.getId());
			Assertions.assertEquals(0, ratingResult.getMoodysRating().compareTo(ratingForTest.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult.getSandPRating().compareTo(ratingForTest.getSandPRating()));
			Assertions.assertEquals(0, ratingResult.getFitchRating().compareTo(ratingForTest.getFitchRating()));
			Assertions.assertEquals(ratingResult.getOrderNumber(),(ratingForTest.getOrderNumber()));

			Assertions.assertEquals(0, urlResult.compareTo("redirect:/rating/list"));	
			
			Rating capturedRating = capturedRatingWhenSaveMethodIsCalled.getValue();
			Assertions.assertEquals(4, capturedRating.getId());
			Assertions.assertEquals("MoodysRating5 Test for mock", capturedRating.getMoodysRating());
			Assertions.assertEquals("SandPRating5 Test for mock", capturedRating.getSandPRating());
			Assertions.assertEquals("FitchRating5 Test for mock", capturedRating.getFitchRating());
			Assertions.assertEquals(5, capturedRating.getOrderNumber());
		}
		
		//EndPoint Test
		@Test
		void testShouldUpdateBidForEndPoint() throws Exception {
			//given
			//when	
			when(ratingService.getRatingById(any())).thenReturn(Optional.of(ratingForMock()));
			when(ratingService.saveRating(any())).thenReturn(new Rating());
			//then
			mockMvc.perform(
					post("/rating/update/{id}",4)
					.param("moodysRating", "MoodysRating4 Test for mock")
					.param("sandPRating", "SandPRating4 Test for mock")
					.param("fitchRating", "FitchRating4 Test for mock")
					.param("orderNumber", "4")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/rating/list"));
		}
		
		private List<Rating> listForMockDeleted() {

			Rating rating2 = new Rating();
			rating2.setId(2);
			rating2.setMoodysRating("MoodysRating2 Test for mock");
			rating2.setSandPRating("SandPRating2 Test for mock");
			rating2.setFitchRating("FitchRating2 Test for mock");
			rating2.setOrderNumber(2);
			

			Rating rating3 = new Rating();
			rating3.setId(3);
			rating3.setMoodysRating("MoodysRating3 Test for mock");
			rating3.setSandPRating("SandPRating3 Test for mock");
			rating3.setFitchRating("FitchRating3 Test for mock");
			rating3.setOrderNumber(3);

			List<Rating> mockedList = new ArrayList<>();
			mockedList.add(rating2);
			mockedList.add(rating3);

			return mockedList;
		}
		//Functional Test shouldDeleteBid
		@Test
		void testShouldDeleteRating() throws Exception{
			//given
			Integer id =1;
			//when
			ArgumentCaptor<Rating> capturedRatingWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(Rating.class);
			Mockito.doNothing().when(ratingService).deleteRating(capturedRatingWhenDeleteMethodIsCalled.capture());
			
			when(ratingService.getRatingById(id)).thenReturn(Optional.of(listForMock().get(0)));
			when(ratingService.getRatings()).thenReturn(listForMockDeleted());
			String urlResult = ratingController.deleteRating(id, modelTest);
			Object attribute =  modelTest.getAttribute("ratingList");

			//then
			Iterable<?> ratingResults = null;
			if(attribute instanceof Iterable<?>) {
				ratingResults = (Iterable<?>) attribute;
			}
			else {
				Assertions.fail("Bad type of ratingResult");
			}
			Iterator<?> ratingResultsIterator = ratingResults.iterator();
			Iterator<Rating> ratingExpectedIterator = listForMockDeleted().iterator();

			Rating ratingResult1 = (Rating) ratingResultsIterator.next();
			Rating ratingExpected1 =  ratingExpectedIterator.next();
			Assertions.assertEquals(0, ratingResult1.getMoodysRating().compareTo(ratingExpected1.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult1.getSandPRating().compareTo(ratingExpected1.getSandPRating()));
			Assertions.assertEquals(0, ratingResult1.getFitchRating().compareTo(ratingExpected1.getFitchRating()));
			Assertions.assertEquals(ratingResult1.getOrderNumber(),(ratingExpected1.getOrderNumber()));
			
			Rating ratingResult2 = (Rating) ratingResultsIterator.next();
			Rating ratingExpected2 =  ratingExpectedIterator.next();

			Assertions.assertEquals(0, ratingResult2.getMoodysRating().compareTo(ratingExpected2.getMoodysRating()));
			Assertions.assertEquals(0, ratingResult2.getSandPRating().compareTo(ratingExpected2.getSandPRating()));
			Assertions.assertEquals(0, ratingResult2.getFitchRating().compareTo(ratingExpected2.getFitchRating()));
			Assertions.assertEquals(ratingResult2.getOrderNumber(),(ratingExpected2.getOrderNumber()));
			
			Assertions.assertEquals(0, urlResult.compareTo("redirect:/rating/list"));	
			
			Rating capturedRating = capturedRatingWhenDeleteMethodIsCalled.getValue();
			Assertions.assertEquals("MoodysRating1 Test for mock", capturedRating.getMoodysRating());
			Assertions.assertEquals("SandPRating1 Test for mock", capturedRating.getSandPRating());
			Assertions.assertEquals("FitchRating1 Test for mock", capturedRating.getFitchRating());
			Assertions.assertEquals( 1, capturedRating.getOrderNumber());
		}

		//EndPoint Test
		@Test
		void testShouldDeleteRatingForEndPoint() throws Exception {
			//given
			Integer id =1;
			//when
			when(ratingService.getRatingById(id)).thenReturn(Optional.of(listForMock().get(1)));
			when(ratingService.getRatings()).thenReturn(listForMock());
			//then
			mockMvc.perform(MockMvcRequestBuilders
					.get("/rating/delete/{id}", 1)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/rating/list"));
		}
}
