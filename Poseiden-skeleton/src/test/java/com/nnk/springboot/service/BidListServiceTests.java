package com.nnk.springboot.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
public class BidListServiceTests {
	
	@Autowired
	private BidListService bidListService;
	
	private BidList bid;
	
	@BeforeEach
	public void setUp() {
		//TODO voir sur internet des tests unitaires qui teste JPA (base de données memoire H2??)
		 bid = new BidList(0, "Account Test", "Type Test", 10d, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
//	/**
//	 * Get bid lists tests
//	 * @return all list of bid
//	 */
//	@Test
//	public void getBidListsTests() {
//		Iterable<BidList> bidListResult = bidListService.getBidLists();
//		Iterator<BidList> iterator = bidListResult.iterator();
//				BidList element = iterator.next();
//		Assertions.assertTrue(element.getAccount() == "Account Test");
//		Assertions.assertTrue(element.getBidListId() == 0);
//		Assertions.assertTrue(element.getType() == "Type Test");
//	//	Assertions.assertTrue(element == "");
//		
//		BidList element2 = iterator.next();
//		Assertions.assertTrue(element2.getAccount() == "Account Test");
//		Assertions.assertTrue(element2.getBidListId() == 0);
//		Assertions.assertTrue(element2.getType() == "Type Test");
//	//	Assertions.assertTrue(element2 == "");
//		
//		Assertions.assertFalse(iterator.hasNext());
//		
//	}

	/**
	 * get bid by id
	 * @param id
	 * @return bid by his id
	 */
	@Test
	public void getBidListByIdTests() {
		//TODO appeler la methode serviceBidListById (1) faire un assert qui verifie que element.getBidListId() == 1
		
	}

	/**
	 * Save bidList
	 * @param bidList
	 * @return save or update bidList in BDD
	 */
	@Test
	public void saveBidListTests() {
		//TODO Créer un element et l'ajouter a mon repo par mon service + appeler méthode saveBidList puis appeler getBidLists et verifier que element est dedans
	}
	
	/**
	 * Save all list of BidList
	 * @param bidLists
	 * @return save all bidLists
	 */
	@Test
	public void saveAllBidListTests() {
		//TODO Créer un element et l'ajouter a mon repo par mon service + appeler méthode saveAllBidList puis appeler getBidLists et verifier que element est dedans
	}
	
	/**
	 * delete bidList by id
	 * @param id
	 */
	@Test
	public void deleteBidListByIdTests() {
		//TODO appeler le deleteById et verifier que l'element n'existe plus
	}
	
	/**
	 * delete bidList
	 * @param bidList
	 */
	@Test
	public void deleteBidListTests() {
		//TODO appeler le delete et verifier que l'element n'existe plus

	}
}
