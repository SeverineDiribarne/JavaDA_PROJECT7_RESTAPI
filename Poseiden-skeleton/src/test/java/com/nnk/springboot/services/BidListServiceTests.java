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
		bid1.setBenchmark("benchmark1");
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
		bid2.setBenchmark("benchmark2");
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
		bid3.setBenchmark("benchmark3");
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
		Assertions.assertEquals(true, bidListResult.isPresent());
		Assertions.assertEquals(BID_ID.intValue(), bidListResult.get().getBidListId() );
		Assertions.assertEquals("Account1 Test for Mock", bidListResult.get().getAccount());
		Assertions.assertEquals("Type1 test for mock", bidListResult.get().getType());
		Assertions.assertEquals(12,bidListResult.get().getBidQuantity());
		Assertions.assertEquals(6,bidListResult.get().getAskQuantity());
		Assertions.assertEquals("benchmark1", bidListResult.get().getBenchmark());
		Assertions.assertEquals("Test1", bidListResult.get().getSide());
	}
	
	/**
	 * This test checks when we call saveCurvePoint method of the CurvePointService
	 * the save method of the repository is called
	 * 
	 * We mock the repository in order to capture the arguments of the saveCurvePoint method
	 * And we check if the captured value is equal to argument value (here bid4 instance)
	 * 
	 * @param bidList
	 * @return save or update bidList in BDD
	 */
	@Test
	void testSaveBidList() throws Exception {
		//Given
		//TODO Créer un element et l'ajouter a mon repo par mon service + appeler méthode saveBidList puis appeler getBidLists et verifier que element est dedans
		BidList bid4 = new BidList();
		bid4.setBidListId(4);
		bid4.setAccount("Account test 4 for mock");
		bid4.setType("Type test 4 for mock");
		bid4.setBidQuantity(14);

		//When
		ArgumentCaptor<BidList> capturedBidListWhenSaveMethodIsCalled = ArgumentCaptor.forClass(BidList.class);
		when(bidListRepository.save(capturedBidListWhenSaveMethodIsCalled.capture())).thenReturn(bid4);

		bidListService.saveBidList(bid4);

		//Then
		BidList capturedBidList = capturedBidListWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedBidList.getBidListId());
		Assertions.assertEquals("Account test 4 for mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type test 4 for mock", capturedBidList.getType());
		Assertions.assertEquals(14, capturedBidList.getBidQuantity());
	}	
	
	/**
	 * delete bidList
	 * @param bidList
	 */
	@Test
	void testDeleteBidList() {
		//TODO appeler le delete et verifier que l'element n'existe plus
		//given
		BidList bid1 = new BidList();
		bid1.setBidListId(1);
		bid1.setAccount("Account1 Test for Mock");
		bid1.setType("Type1 test for mock");
		bid1.setBidQuantity(12);
		bid1.setAskQuantity(6);
		bid1.setBid(9);
		bid1.setAsk(4);
		bid1.setBenchmark("benchmark1");
		bid1.setSide("Test1");
		bid1.setSourceListId("1");
		//when
		ArgumentCaptor<BidList> capturedBidListWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(BidList.class);
		Mockito.doNothing().when(bidListRepository).delete(capturedBidListWhenDeleteMethodIsCalled.capture());
		
		bidListService.deleteBidList(bid1);
		//then
		BidList capturedBidList = capturedBidListWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedBidList.getBidListId());
		Assertions.assertEquals("Account1 Test for Mock", capturedBidList.getAccount());
		Assertions.assertEquals("Type1 test for mock", capturedBidList.getType());
		Assertions.assertEquals( 12, capturedBidList.getBidQuantity());
	}
}