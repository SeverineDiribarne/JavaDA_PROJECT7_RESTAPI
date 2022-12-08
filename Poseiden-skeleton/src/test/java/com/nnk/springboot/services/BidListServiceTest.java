package com.nnk.springboot.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

	private static final Integer BID_ID = 1;

	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	BidListService bidListService = new BidListService();

	public List<BidList> listForMock() {

		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Account1 Test for mock");
		bid1.setType("Type1 Test for mock");
		bid1.setBidQuantity(1);
		bid1.setAskQuantity(1);
		bid1.setBid(1);
		bid1.setAsk(1);
		bid1.setBenchmark("Benchmark1 Test for mock");
		bid1.setCommentary("Commentary1 Test for mock");
		bid1.setSecurity("Security1 Test for mock");
		bid1.setStatus("Status1 Test for mock");
		bid1.setTrader("Trader1 Test for mock");
		bid1.setBook("Book1 Test for mock");
		bid1.setCreationName("CreationName1 Test for mock");
		bid1.setRevisionName("RevisionName1 Test for mock");
		bid1.setDealName("DealName1 Test for mock");
		bid1.setDealType("DealType1 Test for mock");
		bid1.setSourceListId("SourceListId1 Test for mock");
		bid1.setSide("Side1 Test for mock");

		BidList bid2 = new BidList();
		bid2.setBidListId(2);
		bid2.setAccount("Account2 Test for mock");
		bid2.setType("Type2 Test for mock");
		bid2.setBidQuantity(2);
		bid2.setAskQuantity(2);
		bid2.setBid(2);
		bid2.setAsk(2);
		bid2.setBenchmark("Benchmark2 Test for mock");
		bid2.setCommentary("Commentary2 Test for mock");
		bid2.setSecurity("Security2 Test for mock");
		bid2.setStatus("Status2 Test for mock");
		bid2.setTrader("Trader2 Test for mock");
		bid2.setBook("Book2 Test for mock");
		bid2.setCreationName("CreationName2 Test for mock");
		bid2.setRevisionName("RevisionName2 Test for mock");
		bid2.setDealName("DealName2 Test for mock");
		bid2.setDealType("DealType2 Test for mock");
		bid2.setSourceListId("SourceListId2 Test for mock");
		bid2.setSide("Side2 Test for mock");

		BidList bid3 = new BidList();
		bid3.setBidListId(3);
		bid3.setAccount("Account3 Test for mock");
		bid3.setType("Type3 Test for mock");
		bid3.setBidQuantity(3);
		bid3.setAskQuantity(3);
		bid3.setBid(3);
		bid3.setAsk(3);
		bid3.setBenchmark("Benchmark3 Test for mock");
		bid3.setCommentary("Commentary3 Test for mock");
		bid3.setSecurity("Security3 Test for mock");
		bid3.setStatus("Status3 Test for mock");
		bid3.setTrader("Trader3 Test for mock");
		bid3.setBook("Book3 Test for mock");
		bid3.setCreationName("CreationName3 Test for mock");
		bid3.setRevisionName("RevisionName3 Test for mock");
		bid3.setDealName("DealName3 Test for mock");
		bid3.setDealType("DealType3 Test for mock");
		bid3.setSourceListId("SourceListId3 Test for mock");
		bid3.setSide("Side3 Test for mock");

		List<BidList> mockedList = new ArrayList<>();
		mockedList.add(bid1);
		mockedList.add(bid2);
		mockedList.add(bid3);

		return mockedList;
	}
	/**
	 * Call GetBidLists method and verify that elements are presents
	 * @return all list of bid
	 */
	@Test
	void testGetBidLists() {
		//Given
		when(bidListRepository.findAll()).thenReturn(listForMock());

		//When
		Iterable<BidList> bidListResults = bidListService.getBidLists();

		//Then
		Iterator<BidList> bidListResultsIterator = bidListResults.iterator();
		Iterator<BidList> bidListExpectedIterator = listForMock().iterator();

		while(bidListResultsIterator.hasNext()){
			BidList bidListResult = bidListResultsIterator.next();
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
	}
	/**
	 * Method findById
	 * @param id
	 * @return bid
	 */
	private Optional<BidList> findById(Integer id){
		for(BidList bid :listForMock()) {
			if(bid.getBidListId()== id.intValue()) {
				return Optional.of(bid);
			}
		}
		return Optional.empty();
	}
	/**
	 * Call GetBidListById method and verify that the element is the correct one
	 * @param id
	 * @return bid by his id
	 */
	@Test
	void testGetBidListById() {
		//Given
		when(bidListRepository.findById(BID_ID)).thenReturn(findById(BID_ID));

		//When
		Optional<BidList> bidListResult = bidListService.getBidListById(BID_ID);

		//Then
		Assertions.assertEquals(true, bidListResult.isPresent());
		Assertions.assertEquals(BID_ID.intValue(), bidListResult.get().getBidListId() );
		Assertions.assertEquals("Account1 Test for mock", bidListResult.get().getAccount());
		Assertions.assertEquals("Type1 Test for mock", bidListResult.get().getType());
		Assertions.assertEquals(1,bidListResult.get().getBidQuantity());
		Assertions.assertEquals(1,bidListResult.get().getAskQuantity());
		Assertions.assertEquals("Benchmark1 Test for mock", bidListResult.get().getBenchmark());
		Assertions.assertEquals("Commentary1 Test for mock", bidListResult.get().getCommentary());
		Assertions.assertEquals("Security1 Test for mock", bidListResult.get().getSecurity());
		Assertions.assertEquals("Status1 Test for mock", bidListResult.get().getStatus());
		Assertions.assertEquals("Trader1 Test for mock", bidListResult.get().getTrader());
		Assertions.assertEquals("Book1 Test for mock", bidListResult.get().getBook());
		Assertions.assertEquals("CreationName1 Test for mock", bidListResult.get().getCreationName());
		Assertions.assertEquals("RevisionName1 Test for mock", bidListResult.get().getRevisionName());
		Assertions.assertEquals("DealName1 Test for mock", bidListResult.get().getDealName());
		Assertions.assertEquals("DealType1 Test for mock", bidListResult.get().getDealType());
		Assertions.assertEquals("SourceListId1 Test for mock", bidListResult.get().getSourceListId());
		Assertions.assertEquals("Side1 Test for mock", bidListResult.get().getSide());
	}

	/**
	 * Call saveBidList method and verify element is saved in DB
	 * @param bidList
	 * @return save or update bidList in BDD
	 */
	@Test
	void testSaveBidList() throws Exception {
		//Given
		BidList bid4 = new BidList();
		bid4.setBidListId(4);
		bid4.setAccount("Account4  Test for mock");
		bid4.setType("Type4 Test  for mock");
		bid4.setBidQuantity(4);
		bid4.setAskQuantity(4);
		bid4.setBid(4);
		bid4.setAsk(4);
		bid4.setBenchmark("Benchmark4 Test for mock");
		bid4.setCommentary("Commentary4 Test for mock");
		bid4.setSecurity("Security4 Test for mock");
		bid4.setStatus("Status4 Test for mock");
		bid4.setTrader("Trader4 Test for mock");
		bid4.setBook("Book4 Test for mock");
		bid4.setCreationName("CreationName4 Test for mock");
		bid4.setRevisionName("RevisionName4 Test for mock");
		bid4.setDealName("DealName4 Test for mock");
		bid4.setDealType("DealType4 Test for mock");
		bid4.setSourceListId("SourceListId4 Test for mock");
		bid4.setSide("Side4 Test for mock");

		//When
		ArgumentCaptor<BidList> capturedBidListWhenSaveMethodIsCalled = ArgumentCaptor.forClass(BidList.class);
		when(bidListRepository.save(capturedBidListWhenSaveMethodIsCalled.capture())).thenReturn(bid4);
		bidListService.saveBidList(bid4);

		//Then
		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedBidList.getBidListId());
		Assertions.assertEquals("Account4  Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type4 Test  for mock", capturedBidList.getType());
		Assertions.assertEquals(4, capturedBidList.getBidQuantity());
		Assertions.assertEquals(4, capturedBidList.getAskQuantity());
		Assertions.assertEquals(4, capturedBidList.getBid());
		Assertions.assertEquals(4, capturedBidList.getAsk());
		Assertions.assertEquals("Benchmark4 Test for mock", capturedBidList.getBenchmark());
		Assertions.assertEquals("Commentary4 Test for mock", capturedBidList.getCommentary());
		Assertions.assertEquals("Security4 Test for mock", capturedBidList.getSecurity());
		Assertions.assertEquals("Status4 Test for mock", capturedBidList.getStatus());
		Assertions.assertEquals("Trader4 Test for mock", capturedBidList.getTrader());
		Assertions.assertEquals("Book4 Test for mock", capturedBidList.getBook());
		Assertions.assertEquals("CreationName4 Test for mock", capturedBidList.getCreationName());
		Assertions.assertEquals("RevisionName4 Test for mock", capturedBidList.getRevisionName());
		Assertions.assertEquals("DealName4 Test for mock", capturedBidList.getDealName());
		Assertions.assertEquals("DealType4 Test for mock", capturedBidList.getDealType());
		Assertions.assertEquals("SourceListId4 Test for mock", capturedBidList.getSourceListId());
		Assertions.assertEquals("Side4 Test for mock", capturedBidList.getSide());
	}	

	/**
	 * Call deleteBidList method and verify element don't exist
	 */
	@Test
	void testDeleteBidList() {
		//Given
		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Account1 Test for mock");
		bid1.setType("Type1 Test for mock");
		bid1.setBidQuantity(1);
		bid1.setAskQuantity(1);
		bid1.setBid(1);
		bid1.setAsk(1);
		bid1.setBenchmark("Benchmark1 Test for mock");
		bid1.setCommentary("Commentary1 Test for mock");
		bid1.setSecurity("Security1 Test for mock");
		bid1.setStatus("Status1 Test for mock");
		bid1.setTrader("Trader1 Test for mock");
		bid1.setBook("Book1 Test for mock");
		bid1.setCreationName("CreationName1 Test for mock");
		bid1.setRevisionName("RevisionName1 Test for mock");
		bid1.setDealName("DealName1 Test for mock");
		bid1.setDealType("DealType1 Test for mock");
		bid1.setSourceListId("SourceListId1 Test for mock");
		bid1.setSide("Side1 Test for mock");

		//When
		ArgumentCaptor<BidList> capturedBidListWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(BidList.class);
		Mockito.doNothing().when(bidListRepository).delete(capturedBidListWhenDeleteMethodIsCalled.capture());
		bidListService.deleteBidList(bid1);
		
		//Then
		BidList capturedBidList = capturedBidListWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedBidList.getBidListId());
		Assertions.assertEquals("Account1 Test for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type1 Test for mock", capturedBidList.getType());
		Assertions.assertEquals(1, capturedBidList.getBidQuantity());
		Assertions.assertEquals(1, capturedBidList.getAskQuantity());
		Assertions.assertEquals(1, capturedBidList.getBid());
		Assertions.assertEquals(1, capturedBidList.getAsk());
		Assertions.assertEquals("Benchmark1 Test for mock", capturedBidList.getBenchmark());
		Assertions.assertEquals("Commentary1 Test for mock", capturedBidList.getCommentary());
		Assertions.assertEquals("Security1 Test for mock", capturedBidList.getSecurity());
		Assertions.assertEquals("Status1 Test for mock", capturedBidList.getStatus());
		Assertions.assertEquals("Trader1 Test for mock", capturedBidList.getTrader());
		Assertions.assertEquals("Book1 Test for mock", capturedBidList.getBook());
		Assertions.assertEquals("CreationName1 Test for mock", capturedBidList.getCreationName());
		Assertions.assertEquals("RevisionName1 Test for mock", capturedBidList.getRevisionName());
		Assertions.assertEquals("DealName1 Test for mock", capturedBidList.getDealName());
		Assertions.assertEquals("DealType1 Test for mock", capturedBidList.getDealType());
		Assertions.assertEquals("SourceListId1 Test for mock", capturedBidList.getSourceListId());
		Assertions.assertEquals("Side1 Test for mock", capturedBidList.getSide());
	}
}