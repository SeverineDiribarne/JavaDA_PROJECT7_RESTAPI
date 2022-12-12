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
		bid4.setCommentary("commentary4 Test for mock");
		bid4.setSecurity("Security4 Test for mock");
		bid4.setStatus("status4 Test for mock");
		bid4.setTrader("trader4 Test for mock");
		bid4.setBook("book4 Test for mock");
		bid4.setCreationName("creationName4 Test for mock");
		bid4.setRevisionName("revisionName4 Test for mock");
		bid4.setDealName("dealName4 Test for mock");
		bid4.setDealType("dealType4 Test for mock");
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
		bid1.setCommentary("commentary4 Test for mock");
		bid1.setSecurity("Security4 Test for mock");
		bid1.setStatus("status4 Test for mock");
		bid1.setTrader("trader4 Test for mock");
		bid1.setBook("book4 Test for mock");
		bid1.setCreationName("creationName4 Test for mock");
		bid1.setRevisionName("revisionName4 Test for mock");
		bid1.setDealName("dealName4 Test for mock");
		bid1.setDealType("dealType4 Test for mock");
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
		bid2.setCommentary("commentary4 Test for mock");
		bid2.setSecurity("Security4 Test for mock");
		bid2.setStatus("status4 Test for mock");
		bid2.setTrader("trader4 Test for mock");
		bid2.setBook("book4 Test for mock");
		bid2.setCreationName("creationName4 Test for mock");
		bid2.setRevisionName("revisionName4 Test for mock");
		bid2.setDealName("dealName4 Test for mock");
		bid2.setDealType("dealType4 Test for mock");
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
		bid3.setCommentary("commentary4 Test for mock");
		bid3.setSecurity("Security4 Test for mock");
		bid3.setStatus("status4 Test for mock");
		bid3.setTrader("trader4 Test for mock");
		bid3.setBook("book4 Test for mock");
		bid3.setCreationName("creationName4 Test for mock");
		bid3.setRevisionName("revisionName4 Test for mock");
		bid3.setDealName("dealName4 Test for mock");
		bid3.setDealType("dealType4 Test for mock");
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
			Assertions.assertEquals(0, bidListResult.getCommentary().compareTo(bidListExpected.getCommentary()));
			Assertions.assertEquals(0, bidListResult.getSecurity().compareTo(bidListExpected.getSecurity()));
			Assertions.assertEquals(0, bidListResult.getStatus().compareTo(bidListExpected.getStatus()));
			Assertions.assertEquals(0, bidListResult.getTrader().compareTo(bidListExpected.getTrader()));
			Assertions.assertEquals(0, bidListResult.getBook().compareTo(bidListExpected.getBook()));
			Assertions.assertEquals(0, bidListResult.getCreationName().compareTo(bidListExpected.getCreationName()));
			Assertions.assertEquals(0, bidListResult.getRevisionName().compareTo(bidListExpected.getRevisionName()));
			Assertions.assertEquals(0, bidListResult.getDealName().compareTo(bidListExpected.getDealName()));
			Assertions.assertEquals(0, bidListResult.getDealType().compareTo(bidListExpected.getDealType()));
			Assertions.assertEquals(0, bidListResult.getSourceListId().compareTo(bidListExpected.getSourceListId()));
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
		Assertions.assertEquals(0, bidResult.getCommentary().compareTo(bidForTest.getCommentary()));
		Assertions.assertEquals(0, bidResult.getSecurity().compareTo(bidForTest.getSecurity()));
		Assertions.assertEquals(0, bidResult.getStatus().compareTo(bidForTest.getStatus()));
		Assertions.assertEquals(0, bidResult.getTrader().compareTo(bidForTest.getTrader()));
		Assertions.assertEquals(0, bidResult.getBook().compareTo(bidForTest.getBook()));
		Assertions.assertEquals(0, bidResult.getCreationName().compareTo(bidForTest.getCreationName()));
		Assertions.assertEquals(0, bidResult.getRevisionName().compareTo(bidForTest.getRevisionName()));
		Assertions.assertEquals(0, bidResult.getDealName().compareTo(bidForTest.getDealName()));
		Assertions.assertEquals(0, bidResult.getDealType().compareTo(bidForTest.getDealType()));
		Assertions.assertEquals(0, bidResult.getSourceListId().compareTo(bidForTest.getSourceListId()));
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
		Assertions.assertEquals(0, bidListResult.getCommentary().compareTo(bidListExpected.getCommentary()));
		Assertions.assertEquals(0, bidListResult.getSecurity().compareTo(bidListExpected.getSecurity()));
		Assertions.assertEquals(0, bidListResult.getStatus().compareTo(bidListExpected.getStatus()));
		Assertions.assertEquals(0, bidListResult.getTrader().compareTo(bidListExpected.getTrader()));
		Assertions.assertEquals(0, bidListResult.getBook().compareTo(bidListExpected.getBook()));
		Assertions.assertEquals(0, bidListResult.getCreationName().compareTo(bidListExpected.getCreationName()));
		Assertions.assertEquals(0, bidListResult.getRevisionName().compareTo(bidListExpected.getRevisionName()));
		Assertions.assertEquals(0, bidListResult.getDealName().compareTo(bidListExpected.getDealName()));
		Assertions.assertEquals(0, bidListResult.getDealType().compareTo(bidListExpected.getDealType()));
		Assertions.assertEquals(0, bidListResult.getSourceListId().compareTo(bidListExpected.getSourceListId()));
		Assertions.assertEquals(0, bidListResult.getSide().compareTo(bidListExpected.getSide()));
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/bidList/list"));

		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedBidList.getBidListId());
		Assertions.assertEquals("Account4 Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type4 Test for mock", capturedBidList.getType());
		Assertions.assertEquals(4, capturedBidList.getBidQuantity());
	}

	private static BidList emptyAccountBidListForMock() {
		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("");
		bid1.setType("Your type is empty");
		bid1.setBidQuantity(1);

		return bid1;
	}
	static class EmptyAccountBidListFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			BidList bid1 = emptyAccountBidListForMock();
			return Stream.of(Arguments.of(bid1));
		}
	}

	//Functional Test ShouldValidateBidEmptyAccount
	@ParameterizedTest
	@ArgumentsSource(EmptyAccountBidListFormArgumentsProvider.class)
	void testShouldValidateBidEmptyAccount(BidList bidForTest) throws Exception{
		//given
		//when
		String urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgAccount";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("bidList/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your account is empty"));
	}

	private static BidList emptyTypeBidListForMock() {
		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Your account is empty");
		bid1.setType("");
		bid1.setBidQuantity(1);

		return bid1;
	}
	static class EmptyTypeBidListFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			BidList bid1 = emptyTypeBidListForMock();
			return Stream.of(Arguments.of(bid1));
		}
	}

	//Functional Test ShouldValidateBidEmptyType
	@ParameterizedTest
	@ArgumentsSource(EmptyTypeBidListFormArgumentsProvider.class)
	void testShouldValidateBidEmptyType(BidList bidForTest) throws Exception{
		//given
		//when
		String urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgType";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("bidList/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your type is empty"));
	}

	private static BidList emptyBidQuantityBidListForMock() {
		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Your account is empty");
		bid1.setType("Your type is empty");
		bid1.setBidQuantity(0);

		return bid1;
	}
	static class EmptyBidQuantityBidListFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			BidList bid1 = emptyBidQuantityBidListForMock();
			return Stream.of(Arguments.of(bid1));
		}
	}

	//Functional Test ShouldValidateBidEmptyBidQuantity
	@ParameterizedTest
	@ArgumentsSource(EmptyBidQuantityBidListFormArgumentsProvider.class)
	void testShouldValidateBidEmptyAccountEmptyBidQuantity(BidList bidForTest) throws Exception{
		//given
		//when
		String urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgQuantity";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("bidList/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your bidQuantity is equal to 0"));
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

		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/bidList/list"))	;
	}

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
		Assertions.assertEquals(0, bidResult.getCommentary().compareTo(bidForMock().getCommentary()));
		Assertions.assertEquals(0, bidResult.getSecurity().compareTo(bidForMock().getSecurity()));
		Assertions.assertEquals(0, bidResult.getStatus().compareTo(bidForMock().getStatus()));
		Assertions.assertEquals(0, bidResult.getTrader().compareTo(bidForMock().getTrader()));
		Assertions.assertEquals(0, bidResult.getBook().compareTo(bidForMock().getBook()));
		Assertions.assertEquals(0, bidResult.getCreationName().compareTo(bidForMock().getCreationName()));
		Assertions.assertEquals(0, bidResult.getRevisionName().compareTo(bidForMock().getRevisionName()));
		Assertions.assertEquals(0, bidResult.getDealName().compareTo(bidForMock().getDealName()));
		Assertions.assertEquals(0, bidResult.getDealType().compareTo(bidForMock().getDealType()));
		Assertions.assertEquals(0, bidResult.getSourceListId().compareTo(bidForMock().getSourceListId()));
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
	
	//Functional Test ShouldUpdateBidEmptyAccount
		@ParameterizedTest
		@ArgumentsSource(EmptyAccountBidListFormArgumentsProvider.class)
		void testShouldUpdateBidEmptyAccount(BidList bidForTest) throws Exception{
			//given
			//when
			String urlResult = bidListController.updateBid(1, bidForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgAccount";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("bidList/update"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your account is empty"));
		}
		
		//Functional Test ShouldUpdateBidEmptyType
				@ParameterizedTest
				@ArgumentsSource(EmptyTypeBidListFormArgumentsProvider.class)
				void testShouldUpdateBidEmptyType(BidList bidForTest) throws Exception{
					//given
					//when
					String urlResult = bidListController.updateBid(1, bidForTest, resultTest, modelTest);
					//then
					String attributeKey = "msgType";
					Object errorMessageReturned = modelTest.getAttribute(attributeKey);

					String errorMessageResult = null;
					if(errorMessageReturned instanceof String) {
						errorMessageResult = (String) errorMessageReturned;
					}
					else {
						Assertions.fail("Bad type of errorMessageResult");
					}

					Assertions.assertEquals(0, urlResult.compareTo("bidList/update"));
					Assertions.assertEquals(0, errorMessageResult.compareTo("Your type is empty"));
				}

				//Functional Test ShouldUpdateBidEmptyBidQuantity
				@ParameterizedTest
				@ArgumentsSource(EmptyBidQuantityBidListFormArgumentsProvider.class)
				void testShouldUpdateBidEmptyBidQuantity(BidList bidForTest) throws Exception{
					//given
					//when
					String urlResult = bidListController.updateBid(1, bidForTest, resultTest, modelTest);
					//then
					String attributeKey = "msgQuantity";
					Object errorMessageReturned = modelTest.getAttribute(attributeKey);

					String errorMessageResult = null;
					if(errorMessageReturned instanceof String) {
						errorMessageResult = (String) errorMessageReturned;
					}
					else {
						Assertions.fail("Bad type of errorMessageResult");
					}

					Assertions.assertEquals(0, urlResult.compareTo("bidList/update"));
					Assertions.assertEquals(0, errorMessageResult.compareTo("Your bidQuantity is equal to 0"));
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
		bid2.setCommentary("commentary4 Test for mock");
		bid2.setSecurity("Security4 Test for mock");
		bid2.setStatus("status4 Test for mock");
		bid2.setTrader("trader4 Test for mock");
		bid2.setBook("book4 Test for mock");
		bid2.setCreationName("creationName4 Test for mock");
		bid2.setRevisionName("revisionName4 Test for mock");
		bid2.setDealName("dealName4 Test for mock");
		bid2.setDealType("dealType4 Test for mock");
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
		bid3.setCommentary("commentary4 Test for mock");
		bid3.setSecurity("Security4 Test for mock");
		bid3.setStatus("status4 Test for mock");
		bid3.setTrader("trader4 Test for mock");
		bid3.setBook("book4 Test for mock");
		bid3.setCreationName("creationName4 Test for mock");
		bid3.setRevisionName("revisionName4 Test for mock");
		bid3.setDealName("dealName4 Test for mock");
		bid3.setDealType("dealType4 Test for mock");
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
		Assertions.assertEquals(0, bidListResult1.getCommentary().compareTo(bidListExpected1.getCommentary()));
		Assertions.assertEquals(0, bidListResult1.getSecurity().compareTo(bidListExpected1.getSecurity()));
		Assertions.assertEquals(0, bidListResult1.getStatus().compareTo(bidListExpected1.getStatus()));
		Assertions.assertEquals(0, bidListResult1.getTrader().compareTo(bidListExpected1.getTrader()));
		Assertions.assertEquals(0, bidListResult1.getBook().compareTo(bidListExpected1.getBook()));
		Assertions.assertEquals(0, bidListResult1.getCreationName().compareTo(bidListExpected1.getCreationName()));
		Assertions.assertEquals(0, bidListResult1.getRevisionName().compareTo(bidListExpected1.getRevisionName()));
		Assertions.assertEquals(0, bidListResult1.getDealName().compareTo(bidListExpected1.getDealName()));
		Assertions.assertEquals(0, bidListResult1.getDealType().compareTo(bidListExpected1.getDealType()));
		Assertions.assertEquals(0, bidListResult1.getSourceListId().compareTo(bidListExpected1.getSourceListId()));
		Assertions.assertEquals(0, bidListResult1.getSide().compareTo(bidListExpected1.getSide()));

		BidList bidListResult2 = (BidList) bidListResultsIterator.next();
		BidList bidListExpected2 =  BidListExpectedIterator.next();

		Assertions.assertEquals(0, bidListResult2.getAccount().compareTo(bidListExpected2.getAccount()));
		Assertions.assertEquals(0, bidListResult2.getType().compareTo(bidListExpected2.getType()));
		Assertions.assertEquals(bidListResult2.getBidQuantity(), bidListExpected2.getBidQuantity());
		Assertions.assertEquals(bidListResult2.getAskQuantity(),(bidListExpected2.getAskQuantity()));
		Assertions.assertEquals(0, bidListResult2.getBenchmark().compareTo(bidListExpected2.getBenchmark()));
		Assertions.assertEquals(0, bidListResult2.getCommentary().compareTo(bidListExpected2.getCommentary()));
		Assertions.assertEquals(0, bidListResult2.getSecurity().compareTo(bidListExpected2.getSecurity()));
		Assertions.assertEquals(0, bidListResult2.getStatus().compareTo(bidListExpected2.getStatus()));
		Assertions.assertEquals(0, bidListResult2.getTrader().compareTo(bidListExpected2.getTrader()));
		Assertions.assertEquals(0, bidListResult2.getBook().compareTo(bidListExpected2.getBook()));
		Assertions.assertEquals(0, bidListResult2.getCreationName().compareTo(bidListExpected2.getCreationName()));
		Assertions.assertEquals(0, bidListResult2.getRevisionName().compareTo(bidListExpected2.getRevisionName()));
		Assertions.assertEquals(0, bidListResult2.getDealName().compareTo(bidListExpected2.getDealName()));
		Assertions.assertEquals(0, bidListResult2.getDealType().compareTo(bidListExpected2.getDealType()));
		Assertions.assertEquals(0, bidListResult2.getSourceListId().compareTo(bidListExpected2.getSourceListId()));
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