package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class BidTests {

	@Autowired
	private BidListRepository bidListRepository;
	
	private BidList bid;
	
	@BeforeEach
	public void setUp() {
		 bid = new BidList(0, "Account Test", "Type Test", 10d, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Test
	void bidListSaveTest() {
		
		// Save
		BidList bidSaved = bidListRepository.save(bid);
		 int id = bidSaved.getBidListId();
		Assertions.assertNotEquals(0, id);
		Assertions.assertEquals(10d, bidSaved.getBidQuantity(), 10d);

	}

	@Test
	void bidListFindTest() {
		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);
	}

	@Test
	void bidListUpdateTest() {
		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assertions.assertEquals(20d, bid.getBidQuantity(), 20d);

	}

	@Test
	void bidListDeleteTest() {
		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assertions.assertFalse(bidList.isPresent());
	}
}
