package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BidListControllerTest {

	@InjectMocks
	private BidListController bidListController;

	@Mock
	BidListService bidListService;

	private Model modelTest = new ConcurrentModel();


	private List<BidList> listForMock() {

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

	@Test
	void testShouldGetBidList(){
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
		
		Iterator<BidList> BidListExpectedIterator = listForMock().iterator();

		while(bidListResultsIterator.hasNext()){

			BidList bidListResult = (BidList) bidListResultsIterator.next();
			BidList bidListExpected =  BidListExpectedIterator.next();

			Assertions.assertEquals(0, bidListResult.getAccount().compareTo(bidListExpected.getAccount()));
			Assertions.assertEquals(0, bidListResult.getType().compareTo(bidListExpected.getType()));
			Assertions.assertEquals(bidListResult.getBidQuantity(), bidListExpected.getBidQuantity());
			Assertions.assertEquals(bidListResult.getAskQuantity(),(bidListExpected.getAskQuantity()));
			Assertions.assertEquals(0, bidListResult.getBenchmark().compareTo(bidListExpected.getBenchmark()));
			Assertions.assertEquals(0, bidListResult.getSide().compareTo(bidListExpected.getSide()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("bidList/list"));
	}
}
