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
	
	private Model modelTest = new ConcurrentModel();
	
	private static Trade tradeForMock() {
		Trade trade4 = new Trade();
		trade4.setTradeId(4);
		trade4.setAccount("Account4 Test for Mock");
		trade4.setType("Type4 test for mock");
		trade4.setBuyQuantity(10);
		trade4.setSellQuantity(8);
		trade4.setBuyPrice(7);
		trade4.setSellPrice(2);
		trade4.setSecurity("security4");
		trade4.setStatus("status4");
		trade4.setTrader("trader4");
		trade4.setBenchmark("benchmark4");
		trade4.setBook("book4");
		trade4.setCreationName("creationName4");
		trade4.setRevisionName("revisionName4");
		trade4.setDealName("dealName4");
		trade4.setDealType("dealtype4");
		trade4.setSourceListId("4");
		trade4.setSide("Test4");
		return trade4;
	}
	
	private List<Trade> listForMock() {

		Trade trade1 = new Trade();
		trade1.setTradeId(1);
		trade1.setAccount("Account1 Test for Mock");
		trade1.setType("Type1 test for mock");
		trade1.setBuyQuantity(12);
		trade1.setSellQuantity(6);
		trade1.setBuyPrice(9);
		trade1.setSellPrice(4);
		trade1.setSecurity("security1");
		trade1.setStatus("status1");
		trade1.setTrader("trader1");
		trade1.setBenchmark("benchmark1");
		trade1.setBook("book1");
		trade1.setCreationName("creationName1");
		trade1.setRevisionName("revisionName1");
		trade1.setDealName("dealName1");
		trade1.setDealType("dealtype1");
		trade1.setSourceListId("1");
		trade1.setSide("Test1");

		Trade trade2 = new Trade();
		trade2.setTradeId(2);
		trade2.setAccount("Account2 Test for Mock");
		trade2.setType("Type2 test for mock");
		trade2.setBuyQuantity(4);
		trade2.setSellQuantity(2);
		trade2.setBuyPrice(3);
		trade2.setSellPrice(5);
		trade2.setSecurity("security2");
		trade2.setStatus("status2");
		trade2.setTrader("trader2");
		trade2.setBenchmark("benchmark2");
		trade2.setBook("book2");
		trade2.setCreationName("creationName2");
		trade2.setRevisionName("revisionName2");
		trade2.setDealName("dealName2");
		trade2.setDealType("dealtype2");
		trade2.setSourceListId("10");
		trade2.setSide("Test2");

		Trade trade3 = new Trade();
		trade3.setTradeId(3);
		trade3.setAccount("Account3 Test for Mock");
		trade3.setType("Type3 test for mock");
		trade3.setBuyQuantity(10);
		trade3.setSellQuantity(5);
		trade3.setBuyPrice(6);
		trade3.setSellPrice(2);
		trade3.setSecurity("security3");
		trade3.setStatus("status3");
		trade3.setTrader("trader3");
		trade3.setBenchmark("benchmark3");
		trade3.setBook("book3");
		trade3.setCreationName("creationName3");
		trade3.setRevisionName("revisionName3");
		trade3.setDealName("dealName3");
		trade3.setDealType("dealtype3");
		trade3.setSourceListId("20");
		trade3.setSide("Test3");
		
		List<Trade> mockedList = new ArrayList<>();
		mockedList.add(trade1);
		mockedList.add(trade2);
		mockedList.add(trade3);

		return mockedList;
	}
	
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
	
	static class AddTradeFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			Trade trade4 = tradeForMock();
			return Stream.of(Arguments.of(trade4));
		}
	}

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
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("trade/add"));
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

	@Test
	void testShouldValidateTrade() throws Exception{
		//given
		//When
		when(tradeService.saveTrade(any())).thenReturn(new Trade());
		//then
		mockMvc.perform(
				post("/trade/validate")
				.param("account","Account1 Test for Mock")
				.param("type","Type4 test for mock")
				.param("buyQuantity","10")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("trade/list"))
		;

	}

	@Test
	void testShouldShowUpdateForm() throws Exception {
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

	@Test
	void testShouldUpdateTrade() throws Exception {
		//given
		//when	
		when(tradeService.getTradeById(any())).thenReturn(Optional.of(tradeForMock()));
		when(tradeService.saveTrade(any())).thenReturn(new Trade());
		//then
		mockMvc.perform(
				post("/trade/update/{id}",4)
				.param("account","Account1 Test for Mock")
				.param("type","Type4 test for mock")
				.param("buyQuantity","10")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trade/list"));
	}

	@Test
	void testShouldDeleteTrade() throws Exception {
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/trade/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trade/list"));
	}
}