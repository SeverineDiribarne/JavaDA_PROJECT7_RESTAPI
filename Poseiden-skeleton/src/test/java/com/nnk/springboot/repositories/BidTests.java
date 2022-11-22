package com.nnk.springboot.repositories;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class BidTests {

	@Autowired
	private BidListRepository bidListRepository;
	
	
	@Test
	void bidListTest() {
		BidList bid = new BidList(1, "Account1 Test", "Type1 Test", 10d, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

		 // Save
		BidList bidSaved = bidListRepository.save(bid);
		 int id = bidSaved.getBidListId();
		Assertions.assertNotEquals(0, id);
		Assertions.assertEquals(10d, bidSaved.getBidQuantity(), 10d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assertions.assertEquals(20d, bid.getBidQuantity(), 20d);

		// Delete
		id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assertions.assertFalse(bidList.isPresent());
	}
}
