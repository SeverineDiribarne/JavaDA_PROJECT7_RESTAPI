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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class TradeServiceTest {

	private static final Integer TRADE_ID = 1;

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	TradeService tradeService = new TradeService();

	public List<Trade> listForMock() {

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
		trade2.setTradeId(1);
		trade2.setAccount("Account1 Test for mock");
		trade2.setType("Type1 Test for mock");
		trade2.setBuyQuantity(1);
		trade2.setSellQuantity(1);
		trade2.setBuyPrice(1);
		trade2.setSellPrice(1);
		trade2.setSecurity("Security1 Test for mock");
		trade2.setStatus("Status1 Test for mock");
		trade2.setTrader("Trader1 Test for mock");
		trade2.setBenchmark("Benchmark1 Test for mock");
		trade2.setBook("Book1 Test for mock");
		trade2.setCreationName("CreationName1 Test for mock");
		trade2.setRevisionName("RevisionName1 Test for mock");
		trade2.setDealName("DealName1 Test for mock");
		trade2.setDealType("DealType1 Test for mock");
		trade2.setSourceListId("SourceListId1 Test for mock");
		trade2.setSide("Side1 Test for mock");

		Trade trade3 = new Trade();
		trade3.setTradeId(1);
		trade3.setAccount("Account1 Test for mock");
		trade3.setType("Type1 Test for mock");
		trade3.setBuyQuantity(1);
		trade3.setSellQuantity(1);
		trade3.setBuyPrice(1);
		trade3.setSellPrice(1);
		trade3.setSecurity("Security1 Test for mock");
		trade3.setStatus("Status1 Test for mock");
		trade3.setTrader("Trader1 Test for mock");
		trade3.setBenchmark("Benchmark1 Test for mock");
		trade3.setBook("Book1 Test for mock");
		trade3.setCreationName("CreationName1 Test for mock");
		trade3.setRevisionName("RevisionName1 Test for mock");
		trade3.setDealName("DealName1 Test for mock");
		trade3.setDealType("DealType1 Test for mock");
		trade3.setSourceListId("SourceListId1 Test for mock");
		trade3.setSide("Side1 Test for mock");

		List<Trade> mockedList = new ArrayList<>();
		mockedList.add(trade1);
		mockedList.add(trade2);
		mockedList.add(trade3);

		return mockedList;
	}
	
	/**
	 * Call GetTrades method and verify that elements are presents
	 * @return all list of trade
	 */
	@Test
	void testGetTrades() {
		//Given
		when(tradeRepository.findAll()).thenReturn(listForMock());

		//When
		Iterable<Trade> tradeResults = tradeService.getTrades();

		//Then
		Iterator<Trade> tradeResultsIterator = tradeResults.iterator();
		Iterator<Trade> tradeExpectedIterator = listForMock().iterator();

		while(tradeResultsIterator.hasNext()){
			Trade tradeResult = tradeResultsIterator.next();
			Trade tradeExpected =  tradeExpectedIterator.next();
			Assertions.assertEquals(0, tradeResult.getAccount().compareTo(tradeExpected.getAccount()));
			Assertions.assertEquals(0, tradeResult.getType().compareTo(tradeExpected.getType()));
			Assertions.assertEquals(tradeResult.getBuyQuantity(), tradeExpected.getBuyQuantity());
			Assertions.assertEquals(tradeResult.getSellQuantity(),(tradeExpected.getSellQuantity()));
			Assertions.assertEquals(tradeResult.getBuyPrice(), tradeExpected.getBuyPrice());
			Assertions.assertEquals(tradeResult.getSellPrice(),(tradeExpected.getSellPrice()));
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
	}
	/**
	 * Method findById
	 * @param id
	 * @return trade
	 */
	private Optional<Trade> findById(Integer id){
		for(Trade trade :listForMock()) {
			if(trade.getTradeId()== id.intValue()) {
				return Optional.of(trade);
			}
		}
		return Optional.empty();
	}
	/**
	 * Call GetTradeById method and verify that the element is the correct one
	 * @param id
	 * @return trade by his id
	 */
	@Test
	void testGetTradeById() {
		//Given
		when(tradeRepository.findById(TRADE_ID)).thenReturn(findById(TRADE_ID));

		//When
		Optional<Trade> tradeResult = tradeService.getTradeById(TRADE_ID);

		//Then
		Assertions.assertEquals(true, tradeResult.isPresent());
		Assertions.assertEquals(TRADE_ID.intValue(), tradeResult.get().getTradeId() );
		Assertions.assertEquals("Account1 Test for mock", tradeResult.get().getAccount());
		Assertions.assertEquals("Type1 Test for mock", tradeResult.get().getType());
		Assertions.assertEquals(1,tradeResult.get().getBuyQuantity());
		Assertions.assertEquals(1,tradeResult.get().getSellQuantity());
		Assertions.assertEquals(1,tradeResult.get().getBuyPrice());
		Assertions.assertEquals(1,tradeResult.get().getSellPrice());
		Assertions.assertEquals("Security1 Test for mock", tradeResult.get().getSecurity());
		Assertions.assertEquals("Status1 Test for mock", tradeResult.get().getStatus());
		Assertions.assertEquals("Trader1 Test for mock", tradeResult.get().getTrader());
		Assertions.assertEquals("Benchmark1 Test for mock", tradeResult.get().getBenchmark());
		Assertions.assertEquals("Book1 Test for mock", tradeResult.get().getBook());
		Assertions.assertEquals("CreationName1 Test for mock", tradeResult.get().getCreationName());
		Assertions.assertEquals("RevisionName1 Test for mock", tradeResult.get().getRevisionName());
		Assertions.assertEquals("DealName1 Test for mock", tradeResult.get().getDealName());
		Assertions.assertEquals("DealType1 Test for mock", tradeResult.get().getDealType());
		Assertions.assertEquals("SourceListId1 Test for mock", tradeResult.get().getSourceListId());
		Assertions.assertEquals("Side1 Test for mock", tradeResult.get().getSide());
	}

	/**
	 * Call saveTrade method and verify element is saved in DB
	 * @param trade
	 * @return save or update trade in BDD
	 */
	@Test
	void testSaveTrade() throws Exception {
		//Given
		Trade trade4 = new Trade();
		trade4.setTradeId(4);
		trade4.setAccount("Account4  Test for mock");
		trade4.setType("Type4 Test  for mock");
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

		//When
		ArgumentCaptor<Trade> capturedTradeWhenSaveMethodIsCalled = ArgumentCaptor.forClass(Trade.class);
		when(tradeRepository.save(capturedTradeWhenSaveMethodIsCalled.capture())).thenReturn(trade4);
		tradeService.saveTrade(trade4);

		//Then
		Trade capturedTrade = capturedTradeWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedTrade.getTradeId());
		Assertions.assertEquals("Account4  Test for mock", capturedTrade.getAccount());
		Assertions.assertEquals("Type4 Test  for mock", capturedTrade.getType());
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

	/**
	 * Call deleteTrade method and verify element don't exist
	 */
	@Test
	void testDeleteTrade() {
		//Given
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

		//When
		ArgumentCaptor<Trade> capturedTradeWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(Trade.class);
		Mockito.doNothing().when(tradeRepository).delete(capturedTradeWhenDeleteMethodIsCalled.capture());
		tradeService.deleteTrade(trade1);
		
		//Then
		Trade capturedTrade = capturedTradeWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedTrade.getTradeId());
		Assertions.assertEquals("Account1 Test for mock", capturedTrade.getAccount());
		Assertions.assertEquals("Type1 Test for mock", capturedTrade.getType());
		Assertions.assertEquals(1, capturedTrade.getBuyQuantity());
		Assertions.assertEquals(1, capturedTrade.getSellQuantity());
		Assertions.assertEquals(1, capturedTrade.getBuyPrice());
		Assertions.assertEquals(1, capturedTrade.getSellPrice());
		Assertions.assertEquals("Security1 Test for mock", capturedTrade.getSecurity());
		Assertions.assertEquals("Status1 Test for mock", capturedTrade.getStatus());
		Assertions.assertEquals("Trader1 Test for mock", capturedTrade.getTrader());
		Assertions.assertEquals("Benchmark1 Test for mock", capturedTrade.getBenchmark());
		Assertions.assertEquals("Book1 Test for mock", capturedTrade.getBook());
		Assertions.assertEquals("CreationName1 Test for mock", capturedTrade.getCreationName());
		Assertions.assertEquals("RevisionName1 Test for mock", capturedTrade.getRevisionName());
		Assertions.assertEquals("DealName1 Test for mock", capturedTrade.getDealName());
		Assertions.assertEquals("DealType1 Test for mock", capturedTrade.getDealType());
		Assertions.assertEquals("SourceListId1 Test for mock", capturedTrade.getSourceListId());
		Assertions.assertEquals("Side1 Test for mock", capturedTrade.getSide());
	}
}