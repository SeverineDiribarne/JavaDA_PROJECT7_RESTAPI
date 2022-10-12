package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BidListServiceTests {

	private static final Integer BID_ID = 1;

	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	BidListService bidListService = new BidListService();


	public List<BidList> listForMock() {

		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Account1 Test for Mock");
		bid1.setType("Type1 test for mock");
		bid1.setBidQuantity(12);
		bid1.setAskQuantity(6);
		bid1.setBid(9);
		bid1.setAsk(4);
		bid1.setBenchmark("null");
		bid1.setSide("Test1");
		bid1.setSourceListId("1");

		BidList bid2 = new BidList();
		bid2.setBidListId(2);
		bid2.setAccount("Account2 Test for Mock");
		bid2.setType("Type2 test for mock");
		bid2.setBidQuantity(4);
		bid2.setAskQuantity(2);
		bid2.setBid(3);
		bid2.setAsk(5);
		bid2.setBenchmark("benchmark1");
		bid2.setSide("Test2");
		bid2.setSourceListId("10");

		BidList bid3 = new BidList();
		bid3.setBidListId(3);
		bid3.setAccount("Account3 Test for Mock");
		bid3.setType("Type3 test for mock");
		bid3.setBidQuantity(10);
		bid3.setAskQuantity(5);
		bid3.setBid(6);
		bid3.setAsk(2);
		bid3.setBenchmark("benchmark2");
		bid3.setSide("Test3");
		bid3.setSourceListId("20");

		List<BidList> mockedList = new ArrayList<>();
		mockedList.add(bid1);
		mockedList.add(bid2);
		mockedList.add(bid3);

		return mockedList;
	}
	/**
	 * Get bid lists tests
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
		Iterator<BidList> BidListExpectedIterator = listForMock().iterator();

		while(bidListResultsIterator.hasNext()){
			BidList bidListResult = bidListResultsIterator.next();
			BidList bidListExpected =  BidListExpectedIterator.next();
			Assertions.assertEquals(0, bidListResult.getAccount().compareTo(bidListExpected.getAccount()));
			Assertions.assertEquals(0, bidListResult.getType().compareTo(bidListExpected.getType()));
			Assertions.assertEquals(bidListResult.getBidQuantity(), bidListExpected.getBidQuantity());
			Assertions.assertEquals(bidListResult.getAskQuantity(),(bidListExpected.getAskQuantity()));
			Assertions.assertEquals(0, bidListResult.getBenchmark().compareTo(bidListExpected.getBenchmark()));
			Assertions.assertEquals(0, bidListResult.getSide().compareTo(bidListExpected.getSide()));
		}
	}

	private Optional<BidList> findById(Integer id){
		for(BidList bid :listForMock()) {
			if(bid.getBidListId()== id.intValue()) {
				return Optional.of(bid);
			}
		}
		return Optional.empty();
	}
	/**
	 * get bid by id
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
		Assertions.assertTrue(bidListResult.isEmpty()!=true);
		Assertions.assertEquals(BID_ID.intValue(), bidListResult.get().getBidListId() );
		Assertions.assertEquals("Account1 Test for Mock", bidListResult.get().getAccount());
		Assertions.assertEquals("Type1 test for mock", bidListResult.get().getType());
		Assertions.assertEquals(12,bidListResult.get().getBidQuantity());
		Assertions.assertEquals(6,bidListResult.get().getAskQuantity());
		Assertions.assertEquals("benchmark1", bidListResult.get().getBenchmark());
		Assertions.assertEquals("Test1", bidListResult.get().getSide());


	}

	/**
	 * Save bidList
	 * @param bidList
	 * @return save or update bidList in BDD
	 */
	@Test
	void testSaveBidList() throws Exception {
		//TODO Créer un element et l'ajouter a mon repo par mon service + appeler méthode saveBidList puis appeler getBidLists et verifier que element est dedans

		//Given
//		when(bidListRepository.save();
//		when(bidListRepository.findAll()).thenReturn(listForMock());
//
//		BidList bid4 = new BidList();
//		bid4.setBidListId(4);
//		bid4.setAccount("Account4 Test for Mock");
//		bid4.setType("Type4 test for mock");
//		bid4.setBidQuantity(15);
//		bid4.setAskQuantity(3);
//		bid4.setBid(2);
//		bid4.setAsk(1);
//		bid4.setBenchmark("benchmark4");
//		bid4.setBidListDate(null);
//		bid4.setCommentary(null);
//		bid4.setSecurity(null);
//		bid4.setStatus(null);
//		bid4.setTrader(null);
//		bid4.setBook(null);
//		bid4.setCreationName(null);
//		bid4.setCreationDate(null);
//		bid4.setDealName(null);
//		bid4.setDealType(null);
//		bid4.setRevisionName(null);
//		bid4.setRevisionDate(null);
//		bid4.setSide("test4");
//		bid4.setSourceListId("6");
//
//
//		//When
//		bidListService.saveBidList(bid4);
//
//		//Then
//		Iterable<BidList> bidListResult = bidListService.getBidLists();
//		boolean bidFound = false;
//		for(BidList bid : bidListResult) {
//			if(bid.getBidListId()== 4) {
//				bidFound = true;
//			}
//		}
//		Assertions.assertEquals(true, bidFound);
	}	

	/**
	 * Save all list of BidList
	 * @param bidLists
	 * @return save all bidLists
	 */
	@Test
	void testSaveAllBidList() {
		//TODO create an element and add it to the repo by service + call saveAllBidList and call getBidLists and verify element is in it
		//	fail("not yet implemented");
	}

//	private void deleteById(Integer id) {
//		
//		BidList bidToDelete = null;
//		for(BidList bid :listForMock()) {
//			if(bid.getBidListId() == id.intValue()) {
//				bidToDelete = bid;
//			}
//		}
//		listForMock().remove(bidToDelete);
//	}
	/**
	 * delete bidList by id
	 * @param id
	 */
	@Test
	void testDeleteBidListById() {
//		//TODO call deleteById and verify element don't exist
//		//Given
//		when(bidListRepository.deleteById(BID_ID)).thenCall(deleteById(BID_ID));
//
//		//When
//		bidListService.deleteBidListById(BID_ID);
//
//		//Then
//		Iterable<BidList> result = bidListService.getBidLists();
//		boolean bidFound = false;
//
//		for(BidList bid : result) {
//			if(bid.getBidListId()==1) {
//				bidFound = true;
//			}
//		}
//		Assertions.assertFalse(bidFound);
	}

	
	/**
	 * delete bidList
	 * @param bidList
	 */
	@Test
	void testDeleteBidList() {
		//TODO appeler le delete et verifier que l'element n'existe plus
		fail("not yet implemented");
	}
}
