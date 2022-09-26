package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class BidListController {

	@Autowired
	BidListService bidListService;
	
	private static final Logger log = LogManager.getLogger(); 

	@RequestMapping("/bidList/list")
	public String home(Model model)
	{
		// call service find all bids to show to the view OK
		Iterable<BidList> bidLists = bidListService.getBidLists();
		model.addAttribute("bidLists", bidLists);
		log.info("all bids are found and returned to view");
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid, Model model) {
		model.addAttribute("bidList", bid);
		log.info("The display of the addBidList page of a bid is functional");
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		// check data valid and save to db, after saving return bid list OK
		if(result.hasErrors()) {
			log.info("The validation of the bid as well as its backup in the database could not be carried out");
			return "bidList/add" ; 
		}
		bidListService.saveBidList(bid);
		model.addAttribute(bid);
		model.addAttribute("bidLists", bid);
		log.info("The validation of the bid is carried out as well as the backup in the database");
		return "bidList/list";	
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// get Bid by Id and to model then show to the form 
		BidList bidListFoundById = bidListService.getBidListById(id).get() ;
		model.addAttribute(bidListFoundById);
		log.info("the update of the bid found by its id has been carried out");
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
			BindingResult result, Model model) {
		//check required fields, if valid call service to update Bid and return list Bid
		if(result.hasErrors()) {
			log.info("bid update could not be performed");
			return "bidList/update" ;    	
		}
		BidList bidListFoundById = bidListService.getBidListById(id).get();
		bidListFoundById.setAccount(bidList.getAccount());
		bidListFoundById.setType(bidList.getType());
		bidListFoundById.setBidQuantity(bidList.getBidQuantity());
		bidListService.saveBidList(bidListFoundById);
		log.info("The update of the bid has been carried out");
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// Find Bid by Id and delete the bid, return to Bid list 
		bidListService.deleteBidListById(id);
		Iterable<BidList> bidLists = bidListService.getBidLists();
		model.addAttribute(bidLists);
		log.info("The bid found by its id has been deleted from the list");
		return "redirect:/bidList/list";
	}
}
