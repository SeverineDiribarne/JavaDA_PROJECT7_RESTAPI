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
//import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class BidListControllerTest {

	@InjectMocks
	private BidListController bidListController;

	private MockMvc mockMvc;

	@MockBean
	BidListService bidListService;

	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}

	private BindingResult resultTest = new  BeanPropertyBindingResult(bidListController, "bidForTest");
	private Model modelTest = new ConcurrentModel();

	//	private BindingResult bindingResult = new   ; 

	private static BidList bidForMock() {
		BidList bid4 = new BidList();
		bid4.setBidListId(4);
		bid4.setAccount("Account4 Test for mock");
		bid4.setType("Type4 Test for mock");
		bid4.setBidQuantity(4);
		bid4.setAskQuantity(4);
		bid4.setBid(4);
		bid4.setAsk(4);
		bid4.setBenchmark("Benchmark4 Test for mock");
		bid4.setSide("Side4 Test for mock");
		bid4.setSourceListId("SourceListId4 Test for mock");
		return bid4;
	}

	private List<BidList> listForMock() {

		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Account1 Test for mock");
		bid1.setType("Type1 Test for mock");
		bid1.setBidQuantity(1);
		bid1.setAskQuantity(1);
		bid1.setBid(1);
		bid1.setAsk(1);
		bid1.setBenchmark("Benchmark1 Test for mock");
		bid1.setSide("Side1 Test for mock");
		bid1.setSourceListId("SourceListId1 Test for mock");

		BidList bid2 = new BidList();
		bid2.setBidListId(2);
		bid2.setAccount("Account2 Test for mock");
		bid2.setType("Type2 Test for mock");
		bid2.setBidQuantity(2);
		bid2.setAskQuantity(2);
		bid2.setBid(2);
		bid2.setAsk(2);
		bid2.setBenchmark("Benchmark2 Test for mock");
		bid2.setSide("Side2 Test for mock");
		bid2.setSourceListId("SourceListId2 Test for mock");

		BidList bid3 = new BidList();
		bid3.setBidListId(3);
		bid3.setAccount("Account3 Test for mock");
		bid3.setType("Type3 Test for mock");
		bid3.setBidQuantity(3);
		bid3.setAskQuantity(3);
		bid3.setBid(3);
		bid3.setAsk(3);
		bid3.setBenchmark("Benchmark3 Test for mock");
		bid3.setSide("Side3 Test for mock");
		bid3.setSourceListId("SourceListId3 Test for mock");

		List<BidList> mockedList = new ArrayList<>();
		mockedList.add(bid1);
		mockedList.add(bid2);
		mockedList.add(bid3);

		return mockedList;
	}
	//Functional test shouldGetBidList
	@Test
	void testShouldGetBidList() throws Exception{
		//given
		when(bidListService.getBidLists()).thenReturn(listForMock());

		//when
		String urlResult = bidListController.home(modelTest);
		Object attribute =  modelTest.getAttribute("bidLists");

		//then
		Iterable<?> bidListResults = null;
		if(attribute instanceof Iterable<?>) {
			bidListResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of bidListResult");
		}
		Iterator<?> bidListResultsIterator = bidListResults.iterator();
		Iterator<BidList> bidListExpectedIterator = listForMock().iterator();

		while(bidListResultsIterator.hasNext()){

			BidList bidListResult = (BidList) bidListResultsIterator.next();
			BidList bidListExpected =  bidListExpectedIterator.next();

			Assertions.assertEquals(0, bidListResult.getAccount().compareTo(bidListExpected.getAccount()));
			Assertions.assertEquals(0, bidListResult.getType().compareTo(bidListExpected.getType()));
			Assertions.assertEquals(bidListResult.getBidQuantity(), bidListExpected.getBidQuantity());
			Assertions.assertEquals(bidListResult.getAskQuantity(),(bidListExpected.getAskQuantity()));
			Assertions.assertEquals(0, bidListResult.getBenchmark().compareTo(bidListExpected.getBenchmark()));
			Assertions.assertEquals(0, bidListResult.getSide().compareTo(bidListExpected.getSide()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("bidList/list"));
	}

	//EndPoint Test
	@Test
	void testShouldGetBidListForEndPoint() throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bidList/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("bidList/list"));
	}


	static class AddBidFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			BidList bid4 = bidForMock();
			return Stream.of(Arguments.of(bid4));
		}
	}
	//Functional Test shouldAddBidForm
	@ParameterizedTest
	@ArgumentsSource(AddBidFormArgumentsProvider.class)
	void testShouldAddBidForm(BidList bidForTest) throws Exception{
		//given
		String urlResult = bidListController.addBidForm(bidForTest, modelTest);

		Object attribute =  modelTest.getAttribute("bidList");

		//then
		BidList bidResult = null;
		if(attribute instanceof BidList) {
			bidResult = (BidList) attribute;
		}
		else {
			Assertions.fail("Bad type of bidResult");
		}

		Assertions.assertEquals(0, bidResult.getAccount().compareTo(bidForTest.getAccount()));
		Assertions.assertEquals(0, bidResult.getType().compareTo(bidForTest.getType()));
		Assertions.assertEquals(bidResult.getBidQuantity(), bidForTest.getBidQuantity());
		Assertions.assertEquals(bidResult.getAskQuantity(),(bidForTest.getAskQuantity()));
		Assertions.assertEquals(0, bidResult.getBenchmark().compareTo(bidForTest.getBenchmark()));
		Assertions.assertEquals(0, bidResult.getSide().compareTo(bidForTest.getSide()));

		Assertions.assertEquals(0, urlResult.compareTo("bidList/add"));	
	}

	//EndPoint Test
	@ParameterizedTest
	@ArgumentsSource(AddBidFormArgumentsProvider.class)
	void testShouldAddBidFormForEndPoint(BidList bidForTest) throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bidList/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("bidList/add"));
	}

	//Functional Test ShouldValidateBid
	@ParameterizedTest
	@ArgumentsSource(AddBidFormArgumentsProvider.class)
	void testShouldValidateBid(BidList bidForTest) throws Exception{
		//given
		//when
		ArgumentCaptor<BidList> capturedBidListWhenSaveMethodIsCalled = ArgumentCaptor.forClass(BidList.class);
		when(bidListService.saveBidList(capturedBidListWhenSaveMethodIsCalled.capture())).thenReturn(new BidList());
		String urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("bidLists");

		//then
		BidList bidListResult = null;
		if(attribute instanceof BidList) {
			bidListResult = (BidList) attribute;
		}
		else {
			Assertions.fail("Bad type of bidListResult");
		}
		BidList bidListExpected = bidForTest;

		Assertions.assertEquals(0, bidListResult.getAccount().compareTo(bidListExpected.getAccount()));
		Assertions.assertEquals(0, bidListResult.getType().compareTo(bidListExpected.getType()));
		Assertions.assertEquals(bidListResult.getBidQuantity(), bidListExpected.getBidQuantity());
		Assertions.assertEquals(bidListResult.getAskQuantity(),(bidListExpected.getAskQuantity()));
		Assertions.assertEquals(0, bidListResult.getBenchmark().compareTo(bidListExpected.getBenchmark()));
		Assertions.assertEquals(0, bidListResult.getSide().compareTo(bidListExpected.getSide()));
		Assertions.assertEquals(0, urlResult.compareTo("bidList/list"));

		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedBidList.getBidListId());
		Assertions.assertEquals("Account4 Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type4 Test for mock", capturedBidList.getType());
		Assertions.assertEquals(4, capturedBidList.getBidQuantity());
	}

	//EndPoint Test
	@Test
	void testShouldValidateBidForEndPoint() throws Exception{
		//given
		//When
		when(bidListService.saveBidList(any())).thenReturn(new BidList());
		//then
		mockMvc.perform(
				post("/bidList/validate")
				.param("account","Account1 Test for mock")
				.param("type","Type1 Test for mock")
				.param("bidQuantity","10.0")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("bidList/list"))	;
	}

	//	static class ErrorArgumentsProvider implements ArgumentsProvider{
	//		@Override
	//		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
	//			BidList bid = new BidList();
	//			bid.setAccount("12");
	//			bid.setType("12");
	//			bid.setBidQuantity(-1);
	//			return Stream.of(Arguments.of(bid));
	//		}
	//	}
	//
	//	//Functional Test ShouldValidateBidWithError
	//	@ParameterizedTest
	//	@ArgumentsSource(ErrorArgumentsProvider.class)
	//	void testShouldValidateBidWithError(BidList bidForTest) throws Exception{
	//		//given
	//		String urlResult = null;
	//		//when
	//		ArgumentCaptor<BidList> capturedBidListWhenSaveMethodIsCalled = ArgumentCaptor.forClass(BidList.class);
	//		when(bidListService.saveBidList(capturedBidListWhenSaveMethodIsCalled.capture())).thenReturn(new BidList());
	//		try {
	//		 urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
	//		}catch(ResponseStatusException response) {
	//			Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
	//			Assertions.assertEquals("Your bid is not found", response.getReason());
	//		}
	//		Object attribute =  modelTest.getAttribute("bidLists");
	//
	//		//then
	//		Assertions.assertNull(attribute);
	//		Assertions.assertNull(urlResult);
	//
	//		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
	//		Assertions.assertNull(capturedBidList);
	//	}


	//Error Test
	@Test
	void testShouldValidateBidWithError() throws Exception{
		/**
		 * Voir avec Marc comment dans Thymeleaf , afficher un message d'erreur et retourner sur la page add
		 */
		//given
		//When
		when(bidListService.saveBidList(any())).thenReturn(new BidList());
		//then
		mockMvc.perform(
				post("/bidList/validate")
				.param("account", "12")
				.param("type", "12")
				.param("bidQuantity","null" )
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("bidList/add"));
	}

	static class shouldShowUpdateFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			BidList bid4 = bidForMock();
			return Stream.of(Arguments.of(bid4));
		}
	}

	//Functional Test shouldShowUpdateForm
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldShowUpdateForm() throws Exception{
		//given
		Integer id = 4;
		when(bidListService.getBidListById(id)).thenReturn(Optional.of(bidForMock()));

		String urlResult = bidListController.showUpdateForm( id,  modelTest);
		Object attribute =  modelTest.getAttribute("bidList");

		//then
		BidList bidResult = null;
		if(attribute instanceof BidList) {
			bidResult = (BidList) attribute;
		}
		else {
			Assertions.fail("Bad type of bidResult");
		}

		Assertions.assertEquals(0, bidResult.getAccount().compareTo(bidForMock().getAccount()));
		Assertions.assertEquals(0, bidResult.getType().compareTo(bidForMock().getType()));
		Assertions.assertEquals(bidResult.getBidQuantity(), bidForMock().getBidQuantity());
		Assertions.assertEquals(bidResult.getAskQuantity(),(bidForMock().getAskQuantity()));
		Assertions.assertEquals(0, bidResult.getBenchmark().compareTo(bidForMock().getBenchmark()));
		Assertions.assertEquals(0, bidResult.getSide().compareTo(bidForMock().getSide()));

		Assertions.assertEquals(0, urlResult.compareTo("bidList/update"));	
	}

	//EndPoint Test
	@Test
	void testShouldShowUpdateFormForEndPoint() throws Exception {
		//given
		//When
		when(bidListService.getBidListById(any())).thenReturn(Optional.of(bidForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bidList/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("bidList/update"));
	}

	//Functional test shouldUpdateBid
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldUpdateBid(BidList bidForTest) throws Exception{
		//given
		when(bidListService.getBidListById(any())).thenReturn(Optional.of(bidForTest));
		bidForTest.setAccount("Account5 Test for mock");
		bidForTest.setType("Type5 Test for mock");
		bidForTest.setBidQuantity(5);
		bidForTest.setAskQuantity(5);
		bidForTest.setBenchmark("Benchmark5 Test for mock");
		bidForTest.setSide("Side5 Test for mock");
		when(bidListService.saveBidList(any())).thenReturn(bidForTest);

		ArgumentCaptor<BidList> capturedBidListWhenSaveMethodIsCalled = ArgumentCaptor.forClass(BidList.class);
		when(bidListService.saveBidList(capturedBidListWhenSaveMethodIsCalled.capture())).thenReturn(new BidList());
		
		String urlResult = bidListController.updateBid(4, bidForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("bidList");
		//then
		BidList bidResult = null;
		if(attribute instanceof BidList) {
			bidResult = (BidList) attribute;
		}
		else {
			Assertions.fail("Bad type of bidResult");
		}

		Assertions.assertEquals(0, bidResult.getAccount().compareTo(bidForTest.getAccount()));
		Assertions.assertEquals(0, bidResult.getType().compareTo(bidForTest.getType()));
		Assertions.assertEquals(bidResult.getBidQuantity(), bidForTest.getBidQuantity());
		Assertions.assertEquals(bidResult.getAskQuantity(),(bidForTest.getAskQuantity()));
		Assertions.assertEquals(0, bidResult.getBenchmark().compareTo(bidForTest.getBenchmark()));
		Assertions.assertEquals(0, bidResult.getSide().compareTo(bidForTest.getSide()));

		Assertions.assertEquals(0, urlResult.compareTo("redirect:/bidList/list"));	
		
		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedBidList.getBidListId());
		Assertions.assertEquals("Account5 Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type5 Test for mock", capturedBidList.getType());
		Assertions.assertEquals(5, capturedBidList.getBidQuantity());
	}
	
	//EndPoint Test
	@Test
	void testShouldUpdateBidForEndPoint() throws Exception {
		//given
		//when	
		when(bidListService.getBidListById(any())).thenReturn(Optional.of(bidForMock()));
		when(bidListService.saveBidList(any())).thenReturn(new BidList());
		//then
		mockMvc.perform(
				post("/bidList/update/{id}",4)
				.param("account", "Account4 Test for mock")
				.param("type", "Type4 Test for mock")
				.param("bidQuantity", "4")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/bidList/list"));
	}
	
	private List<BidList> listForMockDeleted() {

		BidList bid2 = new BidList();
		bid2.setBidListId(2);
		bid2.setAccount("Account2 Test for mock");
		bid2.setType("Type2 Test for mock");
		bid2.setBidQuantity(2);
		bid2.setAskQuantity(2);
		bid2.setBid(2);
		bid2.setAsk(2);
		bid2.setBenchmark("Benchmark2 Test for mock");
		bid2.setSide("Side2 Test for mock");
		bid2.setSourceListId("SourceListId2 Test for mock");

		BidList bid3 = new BidList();
		bid3.setBidListId(3);
		bid3.setAccount("Account3 Test for mock");
		bid3.setType("Type3 Test for mock");
		bid3.setBidQuantity(3);
		bid3.setAskQuantity(3);
		bid3.setBid(3);
		bid3.setAsk(3);
		bid3.setBenchmark("Benchmark3 Test for mock");
		bid3.setSide("Side3 Test for mock");
		bid3.setSourceListId("SourceListId3 Test for mock");

		List<BidList> mockedList = new ArrayList<>();
		mockedList.add(bid2);
		mockedList.add(bid3);

		return mockedList;
	}
	//Functional Test shouldDeleteBid
	@Test
	void testShouldDeleteBid() throws Exception{
		//given
		Integer id =1;
		//when
		ArgumentCaptor<BidList> capturedBidListWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(BidList.class);
		Mockito.doNothing().when(bidListService).deleteBidList(capturedBidListWhenDeleteMethodIsCalled.capture());
		
		when(bidListService.getBidListById(id)).thenReturn(Optional.of(listForMock().get(0)));
		when(bidListService.getBidLists()).thenReturn(listForMockDeleted());
		String urlResult = bidListController.deleteBid(id, modelTest);
		Object attribute =  modelTest.getAttribute("bidListList");

		//then
		Iterable<?> bidListResults = null;
		if(attribute instanceof Iterable<?>) {
			bidListResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of bidListResult");
		}
		Iterator<?> bidListResultsIterator = bidListResults.iterator();
		Iterator<BidList> BidListExpectedIterator = listForMockDeleted().iterator();

		BidList bidListResult1 = (BidList) bidListResultsIterator.next();
		BidList bidListExpected1 =  BidListExpectedIterator.next();
		Assertions.assertEquals(0, bidListResult1.getAccount().compareTo(bidListExpected1.getAccount()));
		Assertions.assertEquals(0, bidListResult1.getType().compareTo(bidListExpected1.getType()));
		Assertions.assertEquals(bidListResult1.getBidQuantity(), bidListExpected1.getBidQuantity());
		Assertions.assertEquals(bidListResult1.getAskQuantity(),(bidListExpected1.getAskQuantity()));
		Assertions.assertEquals(0, bidListResult1.getBenchmark().compareTo(bidListExpected1.getBenchmark()));
		Assertions.assertEquals(0, bidListResult1.getSide().compareTo(bidListExpected1.getSide()));

		BidList bidListResult2 = (BidList) bidListResultsIterator.next();
		BidList bidListExpected2 =  BidListExpectedIterator.next();

		Assertions.assertEquals(0, bidListResult2.getAccount().compareTo(bidListExpected2.getAccount()));
		Assertions.assertEquals(0, bidListResult2.getType().compareTo(bidListExpected2.getType()));
		Assertions.assertEquals(bidListResult2.getBidQuantity(), bidListExpected2.getBidQuantity());
		Assertions.assertEquals(bidListResult2.getAskQuantity(),(bidListExpected2.getAskQuantity()));
		Assertions.assertEquals(0, bidListResult2.getBenchmark().compareTo(bidListExpected2.getBenchmark()));
		Assertions.assertEquals(0, bidListResult2.getSide().compareTo(bidListExpected2.getSide()));
		
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/bidList/list"));	
		
		BidList capturedBidList = capturedBidListWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals("Account1 Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type1 Test for mock", capturedBidList.getType());
		Assertions.assertEquals( 1, capturedBidList.getBidQuantity());
	}

	//EndPoint Test
	@Test
	void testShouldDeleteBidForEndPoint() throws Exception {
		//given
		Integer id =1;
		//when
		when(bidListService.getBidListById(id)).thenReturn(Optional.of(listForMock().get(1)));
		when(bidListService.getBidLists()).thenReturn(listForMock());
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bidList/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/bidList/list"));
	}
}