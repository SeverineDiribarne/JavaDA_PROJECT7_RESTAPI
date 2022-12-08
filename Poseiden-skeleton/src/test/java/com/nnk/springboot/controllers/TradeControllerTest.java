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

import com.nnk.springboot.controllers.RatingControllerTest.AddRatingFormArgumentsProvider;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

	@InjectMocks
	private TradeController tradeController;

	private MockMvc mockMvc;

	@MockBean
	TradeService tradeService;

	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}

	private BindingResult resultTest = new  BeanPropertyBindingResult(tradeController, "tradeForTest");
	private Model modelTest = new ConcurrentModel();

	private static Trade tradeForMock() {
		Trade trade4 = new Trade();
		trade4.setTradeId(4);
		trade4.setAccount("Account4 Test for mock");
		trade4.setType("Type4 Test for mock");
		trade4.setBuyQuantity(4);
		trade4.setSellQuantity(4);
		trade4.setBuyPrice(4);
		trade4.setSellPrice(4);
		trade4.setSecurity("Security4 Test for mock");
		trade4.setStatus("Status4 Test for mock");
		trade4.setTrader("Trader4 Test for mock");
		trade4.setBenchmark("Benchmark4 Test for mock");
		trade4.setBook("Book4 Test for mock");
		trade4.setCreationName("CreationName4 Test for mock");
		trade4.setRevisionName("RevisionName4 Test for mock");
		trade4.setDealName("DealName4 Test for mock");
		trade4.setDealType("DealType4 Test for mock");
		trade4.setSourceListId("SourceListId4 Test for mock");
		trade4.setSide("Side4 Test for mock");
		return trade4;
	}

	private List<Trade> listForMock() {

		Trade trade1 = new Trade();
		trade1.setTradeId(1);
		trade1.setAccount("Account1 Test for mock");
		trade1.setType("Type1 Test for mock");
		trade1.setBuyQuantity(1);
		trade1.setSellQuantity(1);
		trade1.setBuyPrice(1);
		trade1.setSellPrice(1);
		trade1.setSecurity("Security1 Test for mock");
		trade1.setStatus("Status1 Test for mock");
		trade1.setTrader("Trader1 Test for mock");
		trade1.setBenchmark("Benchmark1 Test for mock");
		trade1.setBook("Book1 Test for mock");
		trade1.setCreationName("CreationName1 Test for mock");
		trade1.setRevisionName("RevisionName1 Test for mock");
		trade1.setDealName("DealName1 Test for mock");
		trade1.setDealType("DealType1 Test for mock");
		trade1.setSourceListId("SourceListId1 Test for mock");
		trade1.setSide("Side1 Test for mock");

		Trade trade2 = new Trade();
		trade2.setTradeId(2);
		trade2.setAccount("Account2 Test for mock");
		trade2.setType("Type2 Test for mock");
		trade2.setBuyQuantity(2);
		trade2.setSellQuantity(2);
		trade2.setBuyPrice(2);
		trade2.setSellPrice(2);
		trade2.setSecurity("Security2 Test for mock");
		trade2.setStatus("Status2 Test for mock");
		trade2.setTrader("Trader2 Test for mock");
		trade2.setBenchmark("Benchmark2 Test for mock");
		trade2.setBook("Book2 Test for mock");
		trade2.setCreationName("CreationName2 Test for mock");
		trade2.setRevisionName("RevisionName2 Test for mock");
		trade2.setDealName("DealName2 Test for mock");
		trade2.setDealType("DealType2 Test for mock");
		trade2.setSourceListId("SourceListId2 Test for mock");
		trade2.setSide("Side2 Test for mock");

		Trade trade3 = new Trade();
		trade3.setTradeId(3);
		trade3.setAccount("Account3 Test for mock");
		trade3.setType("Type3 Test for mock");
		trade3.setBuyQuantity(3);
		trade3.setSellQuantity(3);
		trade3.setBuyPrice(3);
		trade3.setSellPrice(3);
		trade3.setSecurity("Security3 Test for mock");
		trade3.setStatus("Status3 Test for mock");
		trade3.setTrader("Trader3 Test for mock");
		trade3.setBenchmark("Benchmark3 Test for mock");
		trade3.setBook("Book3 Test for mock");
		trade3.setCreationName("CreationName3 Test for mock");
		trade3.setRevisionName("RevisionName3 Test for mock");
		trade3.setDealName("DealName3 Test for mock");
		trade3.setDealType("DealType3 Test for mock");
		trade3.setSourceListId("SourceListId3 Test for mock");
		trade3.setSide("Side3 Test for mock");

		List<Trade> mockedList = new ArrayList<>();
		mockedList.add(trade1);
		mockedList.add(trade2);
		mockedList.add(trade3);

		return mockedList;
	}

	//Functional test shouldGetTrade
	@Test
	void testShouldGetTrade(){
		//given
		when(tradeService.getTrades()).thenReturn(listForMock());

		//when
		String urlResult = tradeController.home(modelTest);
		Object attribute =  modelTest.getAttribute("trades");

		//then
		Iterable<?> tradeResults = null;
		if(attribute instanceof Iterable<?>) {
			tradeResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}

		Iterator<?> tradeResultsIterator = tradeResults.iterator();
		Iterator<Trade> tradeExpectedIterator = listForMock().iterator();

		while(tradeResultsIterator.hasNext()){

			Trade tradeResult = (Trade) tradeResultsIterator.next();
			Trade tradeExpected =  tradeExpectedIterator.next();

			Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeExpected.getAccount()));
			Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeExpected.getType()));
			Assertions.assertEquals(tradeResult.getBuyQuantity(), tradeExpected.getBuyQuantity());
			Assertions.assertEquals(tradeResult.getSellQuantity(),(tradeExpected.getSellQuantity()));
			Assertions.assertEquals(tradeResult.getBuyPrice(), tradeExpected.getBuyPrice());
			Assertions.assertEquals(tradeResult.getSellPrice(), tradeExpected.getSellPrice());
			Assertions.assertEquals(0, tradeResult.getSecurity().compareTo(tradeExpected.getSecurity()));
			Assertions.assertEquals(0, tradeResult.getStatus().compareTo(tradeExpected.getStatus()));
			Assertions.assertEquals(0, tradeResult.getTrader().compareTo(tradeExpected.getTrader()));
			Assertions.assertEquals(0, tradeResult.getBenchmark().compareTo(tradeExpected.getBenchmark()));
			Assertions.assertEquals(0, tradeResult.getBook().compareTo(tradeExpected.getBook()));
			Assertions.assertEquals(0, tradeResult.getCreationName().compareTo(tradeExpected.getCreationName()));
			Assertions.assertEquals(0, tradeResult.getRevisionName().compareTo(tradeExpected.getRevisionName()));
			Assertions.assertEquals(0, tradeResult.getDealName().compareTo(tradeExpected.getDealName()));
			Assertions.assertEquals(0, tradeResult.getDealType().compareTo(tradeExpected.getDealType()));
			Assertions.assertEquals(0, tradeResult.getSourceListId().compareTo(tradeExpected.getSourceListId()));
			Assertions.assertEquals(0, tradeResult.getSide().compareTo(tradeExpected.getSide()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("trade/list"));
	}

	//EndPoint Test
	@Test
	void testShouldGetTradeForEndPoint() throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("trade/list"));
	}

	static class AddTradeFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			Trade trade4 = tradeForMock();
			return Stream.of(Arguments.of(trade4));
		}
	}

	//Functional Test shouldAddTradeForm
	@ParameterizedTest
	@ArgumentsSource(AddTradeFormArgumentsProvider.class)
	void testShouldAddTradeForm(Trade tradeForTest) throws Exception{
		//given
		String urlResult = tradeController.addTrade(tradeForTest, modelTest);

		Object attribute =  modelTest.getAttribute("trade");

		//then
		Trade tradeResult = null;
		if(attribute instanceof Trade) {
			tradeResult = (Trade) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}

		Assertions.assertEquals(tradeResult.getTradeId() , tradeForTest.getTradeId());
		Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeForTest.getAccount()));
		Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeForTest.getType()));
		Assertions.assertEquals(tradeResult.getBuyQuantity(), tradeForTest.getBuyQuantity());
		Assertions.assertEquals(tradeResult.getSellQuantity(),(tradeForTest.getSellQuantity()));
		Assertions.assertEquals(tradeResult.getBuyPrice(), tradeForTest.getBuyPrice());
		Assertions.assertEquals(tradeResult.getSellPrice(), tradeForTest.getSellPrice());
		Assertions.assertEquals(0, tradeResult.getSecurity().compareTo(tradeForTest.getSecurity()));
		Assertions.assertEquals(0, tradeResult.getStatus().compareTo(tradeForTest.getStatus()));
		Assertions.assertEquals(0, tradeResult.getTrader().compareTo(tradeForTest.getTrader()));
		Assertions.assertEquals(0, tradeResult.getBenchmark().compareTo(tradeForTest.getBenchmark()));
		Assertions.assertEquals(0, tradeResult.getBook().compareTo(tradeForTest.getBook()));
		Assertions.assertEquals(0, tradeResult.getCreationName().compareTo(tradeForTest.getCreationName()));
		Assertions.assertEquals(0, tradeResult.getRevisionName().compareTo(tradeForTest.getRevisionName()));
		Assertions.assertEquals(0, tradeResult.getDealName().compareTo(tradeForTest.getDealName()));
		Assertions.assertEquals(0, tradeResult.getDealType().compareTo(tradeForTest.getDealType()));
		Assertions.assertEquals(0, tradeResult.getSourceListId().compareTo(tradeForTest.getSourceListId()));
		Assertions.assertEquals(0, tradeResult.getSide().compareTo(tradeForTest.getSide()));

		Assertions.assertEquals(0, urlResult.compareTo("trade/add"));	
	}

	//EndPoint Test
	@ParameterizedTest
	@ArgumentsSource(AddTradeFormArgumentsProvider.class)
	void testShouldAddTradeFormForEndPoint(Trade tradeForTest) throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("trade/add"));
	}

	//Functional Test ShouldValidateTrade
	@ParameterizedTest
	@ArgumentsSource(AddTradeFormArgumentsProvider.class)
	void testShouldValidateTrade(Trade tradeForTest) throws Exception{
		//given
		//when
		ArgumentCaptor<Trade> capturedTradeWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Trade.class);
		when(tradeService.saveTrade(capturedTradeWhenSaveMethodIsCalled.capture())).thenReturn(new Trade());
		String urlResult = tradeController.validate(tradeForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("trades");

		//then
		Trade tradeResult = null;
		if(attribute instanceof Trade) {
			tradeResult = (Trade) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}
		Trade tradeExpected = tradeForTest;

		Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeExpected.getAccount()));
		Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeExpected.getType()));
		Assertions.assertEquals(tradeResult.getBuyQuantity(), tradeExpected.getBuyQuantity());
		Assertions.assertEquals(tradeResult.getSellQuantity(),(tradeExpected.getSellQuantity()));
		Assertions.assertEquals(tradeResult.getBuyPrice(), tradeExpected.getBuyPrice());
		Assertions.assertEquals(tradeResult.getSellPrice(), tradeExpected.getSellPrice());
		Assertions.assertEquals(0, tradeResult.getSecurity().compareTo(tradeExpected.getSecurity()));
		Assertions.assertEquals(0, tradeResult.getStatus().compareTo(tradeExpected.getStatus()));
		Assertions.assertEquals(0, tradeResult.getTrader().compareTo(tradeExpected.getTrader()));
		Assertions.assertEquals(0, tradeResult.getBenchmark().compareTo(tradeExpected.getBenchmark()));
		Assertions.assertEquals(0, tradeResult.getBook().compareTo(tradeExpected.getBook()));
		Assertions.assertEquals(0, tradeResult.getCreationName().compareTo(tradeExpected.getCreationName()));
		Assertions.assertEquals(0, tradeResult.getRevisionName().compareTo(tradeExpected.getRevisionName()));
		Assertions.assertEquals(0, tradeResult.getDealName().compareTo(tradeExpected.getDealName()));
		Assertions.assertEquals(0, tradeResult.getDealType().compareTo(tradeExpected.getDealType()));
		Assertions.assertEquals(0, tradeResult.getSourceListId().compareTo(tradeExpected.getSourceListId()));
		Assertions.assertEquals(0, tradeResult.getSide().compareTo(tradeExpected.getSide()));
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/trade/list"));

		Trade capturedTrade = capturedTradeWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedTrade.getTradeId());
		Assertions.assertEquals("Account4 Test for mock", capturedTrade.getAccount());
		Assertions.assertEquals("Type4 Test for mock", capturedTrade.getType());
		Assertions.assertEquals(4, capturedTrade.getBuyQuantity());
		Assertions.assertEquals(4, capturedTrade.getSellQuantity());
		Assertions.assertEquals(4, capturedTrade.getBuyPrice());
		Assertions.assertEquals(4, capturedTrade.getSellPrice());
		Assertions.assertEquals("Security4 Test for mock", capturedTrade.getSecurity());
		Assertions.assertEquals("Status4 Test for mock", capturedTrade.getStatus());
		Assertions.assertEquals("Trader4 Test for mock", capturedTrade.getTrader());
		Assertions.assertEquals("Benchmark4 Test for mock", capturedTrade.getBenchmark());
		Assertions.assertEquals("Book4 Test for mock", capturedTrade.getBook());
		Assertions.assertEquals("CreationName4 Test for mock", capturedTrade.getCreationName());
		Assertions.assertEquals("RevisionName4 Test for mock", capturedTrade.getRevisionName());
		Assertions.assertEquals("DealName4 Test for mock", capturedTrade.getDealName());
		Assertions.assertEquals("DealType4 Test for mock", capturedTrade.getDealType());
		Assertions.assertEquals("SourceListId4 Test for mock", capturedTrade.getSourceListId());
		Assertions.assertEquals("Side4 Test for mock", capturedTrade.getSide());
	}

	//EndPoint Test
	@Test
	void testShouldValidateTradeForEndPoint() throws Exception{
		//given
		//When
		when(tradeService.saveTrade(any())).thenReturn(new Trade());
		//then
		mockMvc.perform(
				post("/trade/validate")
				.param("account","Account1 Test for mock")
				.param("type","Type4 Test for mock")
				.param("buyQuantity","1")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/trade/list"))
		;

	}
	static class shouldShowUpdateFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			Trade trade4 = tradeForMock();
			return Stream.of(Arguments.of(trade4));
		}
	}

	//Functional Test shouldShowUpdateForm
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldShowUpdateForm() throws Exception{
		//given
		Integer id = 4;
		when(tradeService.getTradeById(id)).thenReturn(Optional.of(tradeForMock()));

		String urlResult = tradeController.showUpdateForm( id,  modelTest);
		Object attribute =  modelTest.getAttribute("trade");

		//then
		Trade tradeResult = null;
		if(attribute instanceof Trade) {
			tradeResult = (Trade) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}

		Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeForMock().getAccount()));
		Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeForMock().getType()));
		Assertions.assertEquals(tradeResult.getBuyQuantity(),tradeForMock().getBuyQuantity());

		Assertions.assertEquals(0, urlResult.compareTo("trade/update"));	
	}

	//EndPoint Test
	@Test
	void testShouldShowUpdateFormForEndPoint() throws Exception {
		//given
		//When
		when(tradeService.getTradeById(any())).thenReturn(Optional.of(tradeForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("trade/update"));
	}

	//Functional test shouldUpdateTrade
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldUpdateTrade(Trade tradeForTest) throws Exception{
		//given
		when(tradeService.getTradeById(any())).thenReturn(Optional.of(tradeForTest));
		tradeForTest.setAccount("Account5 Test for mock");
		tradeForTest.setType("Type5 Test for mock");
		tradeForTest.setBuyQuantity(5);

		when(tradeService.saveTrade(any())).thenReturn(tradeForTest);

		ArgumentCaptor<Trade> capturedTradeWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Trade.class);
		when(tradeService.saveTrade(capturedTradeWhenSaveMethodIsCalled.capture())).thenReturn(new Trade());

		String urlResult = tradeController.updateTrade(4, tradeForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("trade");
		//then
		Trade tradeResult = null;
		if(attribute instanceof Trade) {
			tradeResult = (Trade) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}

		Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeForTest.getAccount()));
		Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeForTest.getType()));
		Assertions.assertEquals(tradeResult.getBuyQuantity(), tradeForTest.getBuyQuantity());

		Assertions.assertEquals(0, urlResult.compareTo("redirect:/trade/list"));	

		Trade capturedTrade = capturedTradeWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedTrade.getTradeId());
		Assertions.assertEquals("Account5 Test for mock", capturedTrade.getAccount());
		Assertions.assertEquals("Type5 Test for mock", capturedTrade.getType());
		Assertions.assertEquals(5, capturedTrade.getBuyQuantity());
	}
	
	//EndPoint Test
	@Test
	void testShouldUpdateTrade() throws Exception {
		//given
		//when	
		when(tradeService.getTradeById(any())).thenReturn(Optional.of(tradeForMock()));
		when(tradeService.saveTrade(any())).thenReturn(new Trade());
		//then
		mockMvc.perform(
				post("/trade/update/{id}",4)
				.param("account","Account4 Test for mock")
				.param("type","Type4 Test for mock")
				.param("buyQuantity","4")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trade/list"));
	}

	private List<Trade> listForMockDeleted() {

		Trade trade2 = new Trade();
		trade2.setTradeId(2);
		trade2.setAccount("Account2 Test for mock");
		trade2.setType("Type2 Test for mock");
		trade2.setBuyQuantity(2);
		trade2.setSellQuantity(2);
		trade2.setBuyPrice(2);
		trade2.setSellPrice(2);
		trade2.setSecurity("Security2 Test for mock");
		trade2.setStatus("Status2 Test for mock");
		trade2.setTrader("Trader2 Test for mock");
		trade2.setBenchmark("Benchmark2 Test for mock");
		trade2.setBook("Book2 Test for mock");
		trade2.setCreationName("CreationName2 Test for mock");
		trade2.setRevisionName("RevisionName2 Test for mock");
		trade2.setDealName("DealName2 Test for mock");
		trade2.setDealType("DealType2 Test for mock");
		trade2.setSourceListId("SourceListId2 Test for mock");
		trade2.setSide("Side2 Test for mock");

		Trade trade3 = new Trade();
		trade3.setTradeId(3);
		trade3.setAccount("Account3 Test for mock");
		trade3.setType("Type3 Test for mock");
		trade3.setBuyQuantity(3);
		trade3.setSellQuantity(3);
		trade3.setBuyPrice(3);
		trade3.setSellPrice(3);
		trade3.setSecurity("Security3 Test for mock");
		trade3.setStatus("Status3 Test for mock");
		trade3.setTrader("Trader3 Test for mock");
		trade3.setBenchmark("Benchmark3 Test for mock");
		trade3.setBook("Book3 Test for mock");
		trade3.setCreationName("CreationName3 Test for mock");
		trade3.setRevisionName("RevisionName3 Test for mock");
		trade3.setDealName("DealName3 Test for mock");
		trade3.setDealType("DealType3 Test for mock");
		trade3.setSourceListId("SourceListId3 Test for mock");
		trade3.setSide("Side3 Test for mock");

		List<Trade> mockedList = new ArrayList<>();
		mockedList.add(trade2);
		mockedList.add(trade3);

		return mockedList;
	}

	//Functional Test shouldDeleteTrade
	@Test
	void testShouldDeleteTrade() throws Exception{
		//given
		Integer id =1;
		//when
		ArgumentCaptor<Trade> capturedTradeWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(Trade.class);
		Mockito.doNothing().when(tradeService).deleteTrade(capturedTradeWhenDeleteMethodIsCalled.capture());

		when(tradeService.getTradeById(id)).thenReturn(Optional.of(listForMock().get(0)));
		when(tradeService.getTrades()).thenReturn(listForMockDeleted());
		String urlResult = tradeController.deleteTrade(id, modelTest);
		Object attribute =  modelTest.getAttribute("tradeList");

		//then
		Iterable<?> tradeResults = null;
		if(attribute instanceof Iterable<?>) {
			tradeResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of tradeResult");
		}
		Iterator<?> tradeResultsIterator = tradeResults.iterator();
		Iterator<Trade> tradeExpectedIterator = listForMockDeleted().iterator();

		Trade tradeResult1 = (Trade) tradeResultsIterator.next();
		Trade tradeExpected1 =  tradeExpectedIterator.next();
		Assertions.assertEquals(0, tradeResult1.getAccount().compareTo(tradeExpected1.getAccount()));
		Assertions.assertEquals(0, tradeResult1.getType().compareTo(tradeExpected1.getType()));
		Assertions.assertEquals(tradeResult1.getBuyQuantity(), tradeExpected1.getBuyQuantity());
		Assertions.assertEquals(tradeResult1.getSellQuantity(),(tradeExpected1.getSellQuantity()));
		Assertions.assertEquals(tradeResult1.getBuyPrice(), tradeExpected1.getBuyPrice());
		Assertions.assertEquals(tradeResult1.getSellPrice(),(tradeExpected1.getSellPrice()));
		Assertions.assertEquals(0, tradeResult1.getSecurity().compareTo(tradeExpected1.getSecurity()));
		Assertions.assertEquals(0, tradeResult1.getStatus().compareTo(tradeExpected1.getStatus()));
		Assertions.assertEquals(0, tradeResult1.getTrader().compareTo(tradeExpected1.getTrader()));
		Assertions.assertEquals(0, tradeResult1.getBenchmark().compareTo(tradeExpected1.getBenchmark()));
		Assertions.assertEquals(0, tradeResult1.getBook().compareTo(tradeExpected1.getBook()));
		Assertions.assertEquals(0, tradeResult1.getCreationName().compareTo(tradeExpected1.getCreationName()));
		Assertions.assertEquals(0, tradeResult1.getRevisionName().compareTo(tradeExpected1.getRevisionName()));
		Assertions.assertEquals(0, tradeResult1.getDealName().compareTo(tradeExpected1.getDealName()));
		Assertions.assertEquals(0, tradeResult1.getDealType().compareTo(tradeExpected1.getDealType()));
		Assertions.assertEquals(0, tradeResult1.getSourceListId().compareTo(tradeExpected1.getSourceListId()));
		Assertions.assertEquals(0, tradeResult1.getSide().compareTo(tradeExpected1.getSide()));

		Trade tradeResult2 = (Trade) tradeResultsIterator.next();
		Trade tradeExpected2 =  tradeExpectedIterator.next();

		Assertions.assertEquals(0, tradeResult2.getAccount().compareTo(tradeExpected2.getAccount()));
		Assertions.assertEquals(0, tradeResult2.getType().compareTo(tradeExpected2.getType()));
		Assertions.assertEquals(tradeResult2.getBuyQuantity(), tradeExpected2.getBuyQuantity());
		Assertions.assertEquals(tradeResult2.getSellQuantity(),(tradeExpected2.getSellQuantity()));
		Assertions.assertEquals(tradeResult2.getBuyPrice(), tradeExpected2.getBuyPrice());
		Assertions.assertEquals(tradeResult2.getSellPrice(),(tradeExpected2.getSellPrice()));
		Assertions.assertEquals(0, tradeResult2.getSecurity().compareTo(tradeExpected2.getSecurity()));
		Assertions.assertEquals(0, tradeResult2.getStatus().compareTo(tradeExpected2.getStatus()));
		Assertions.assertEquals(0, tradeResult2.getTrader().compareTo(tradeExpected2.getTrader()));
		Assertions.assertEquals(0, tradeResult2.getBenchmark().compareTo(tradeExpected2.getBenchmark()));
		Assertions.assertEquals(0, tradeResult2.getBook().compareTo(tradeExpected2.getBook()));
		Assertions.assertEquals(0, tradeResult2.getCreationName().compareTo(tradeExpected2.getCreationName()));
		Assertions.assertEquals(0, tradeResult2.getRevisionName().compareTo(tradeExpected2.getRevisionName()));
		Assertions.assertEquals(0, tradeResult2.getDealName().compareTo(tradeExpected2.getDealName()));
		Assertions.assertEquals(0, tradeResult2.getDealType().compareTo(tradeExpected2.getDealType()));
		Assertions.assertEquals(0, tradeResult2.getSourceListId().compareTo(tradeExpected2.getSourceListId()));
		Assertions.assertEquals(0, tradeResult2.getSide().compareTo(tradeExpected2.getSide()));


		Assertions.assertEquals(0, urlResult.compareTo("redirect:/trade/list"));	

		Trade capturedTrade = capturedTradeWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals("Account1 Test for mock", capturedTrade.getAccount());
		Assertions.assertEquals("Type1 Test for mock", capturedTrade.getType());
		Assertions.assertEquals( 1, capturedTrade.getBuyQuantity());
	}

	//EndPoint Test
	@Test
	void testShouldDeleteTradeForEndPoint() throws Exception {
		//given
		Integer id =1;
		//when
		when(tradeService.getTradeById(id)).thenReturn(Optional.of(listForMock().get(1)));
		when(tradeService.getTrades()).thenReturn(listForMock());
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trade/list"));
	}
}