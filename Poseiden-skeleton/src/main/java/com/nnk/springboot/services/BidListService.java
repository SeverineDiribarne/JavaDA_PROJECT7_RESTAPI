package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

public class BidListService {

	@Autowired
	BidListRepository bidListRepository;

	/**
	 * Get bid lists
	 * @return all list of bid
	 */
	public Iterable<BidList> getBidLists() {
		return bidListRepository.findAll();
	}

	/**
	 * get bid by id
	 * @param id
	 * @return bid by his id
	 */
	public Optional<BidList> getBidListById(Integer id) {
		return bidListRepository.findById(id);
	}

	/**
	 * Save bidList
	 * @param bidList
	 * @return save or update bidList in BDD
	 */
	public BidList saveBidList(BidList bidList) {
		return bidListRepository.save(bidList);
	}
	/**
	 * Save all list of BidList
	 * @param bidLists
	 * @return save all bidLists
	 */
	public List<BidList> saveAllBidList(Iterable<BidList> bidLists) {
		return bidListRepository.saveAll(bidLists);
	}
	
	/**
	 * delete bidList by id
	 * @param id
	 */
	public void deleteBidListById(Integer id) {
		bidListRepository.deleteById(id);
	}
	
	/**
	 * delete bidList
	 * @param bidList
	 */
	public void deleteBidList(BidList bidList) {
		bidListRepository.delete(bidList);
	}
}
