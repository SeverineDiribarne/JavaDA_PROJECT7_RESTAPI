package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BidListService {

	@Autowired
	BidListRepository bidListRepository;
	
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BidListService.class);

	/**
	 * Get bid lists
	 * @return all list of bid
	 */
	public Iterable<BidList> getBidLists() {
		List <BidList> bidList = bidListRepository.findAll();
		log.info("Get BidList is executed and return " + bidList.size() + " rows.");
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
	@Transactional(rollbackFor = Exception.class)
	public BidList saveBidList(BidList bidList) {
		return bidListRepository.save(bidList);
	}
	
	/**
	 * Save all list of BidList
	 * @param bidLists
	 * @return save all bidLists
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<BidList> saveAllBidList(Iterable<BidList> bidLists) {
		return bidListRepository.saveAll(bidLists);
	}
	
	/**
	 * delete bidList by id
	 * @param id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteBidListById(Integer id) {
		bidListRepository.deleteById(id);
	}
	
	/**
	 * delete bidList
	 * @param bidList
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteBidList(BidList bidList) {
		bidListRepository.delete(bidList);
	}
}