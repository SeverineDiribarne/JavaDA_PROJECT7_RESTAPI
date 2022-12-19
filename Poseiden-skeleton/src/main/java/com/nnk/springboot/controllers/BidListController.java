package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;

import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import javax.validation.Valid;

@Slf4j
@Controller
public class BidListController {

	@Autowired
	BidListService bidListService;
	@Autowired
	UserService userService;
	
	private static final String LOG_ERROR = "The validation of the bid as well as its backup in the database could not be carried out because the bid is empty";
	private static final String REDIRECT_BIDLIST_LIST = "redirect:/bidList/list";
	private static final String BIDLIST_ADD = "bidList/add";
	private static final String BIDLIST_UPDATE = "bidList/update";

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BidListController.class);

	@RequestMapping("/bidList/list")
	public String home(Model model){
		// call service find all bids to show to the view OK
//		SecurityContext context = SecurityContextHolder.getContext();
//		String name = context.getAuthentication().getName();
		Iterable<BidList> bidLists = bidListService.getBidLists();
		model.addAttribute("bidLists", bidLists);
		log.info("all bids are found and returned to view");
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid, Model model) {
		model.addAttribute("bidList", bid);
		log.info("The display of the addBidList page of a bid is functional");
		return BIDLIST_ADD;
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		// check data valid and save to db, after saving return bid list OK
		if( bid.getAccount().isEmpty() ) {
			log.error(LOG_ERROR);
			model.addAttribute("msgAccount" , "Your account is empty");
			return BIDLIST_ADD;
		}
		if(bid.getType().isEmpty() ) {
			log.error(LOG_ERROR);
			model.addAttribute("msgType", "Your type is empty");
			return BIDLIST_ADD;
		}
		if(bid.getBidQuantity()==0) {
			log.error(LOG_ERROR);
			model.addAttribute("msgQuantity", "Your bidQuantity is equal to 0");
			return BIDLIST_ADD;
		}
		if(result.hasErrors()){
			log.error("The validation of the bid as well as its backup in the database could not be carried out");
			model.addAttribute( "msgerror", "Your bid is not found");
			return BIDLIST_ADD;
		}
		bidListService.saveBidList(bid);
		model.addAttribute("bidLists", bid);
		log.info("The validation of the bid is carried out as well as the backup in the database");
		return REDIRECT_BIDLIST_LIST;	
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// get Bid by Id and to model then show to the form 
		BidList bidListFoundById = bidListService.getBidListById(id).get() ;
		model.addAttribute(bidListFoundById);
		log.info("the update of the bid found by its id has been carried out");
		return BIDLIST_UPDATE;
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
			BindingResult result, Model model) {
		//check required fields, if valid call service to update Bid and return list Bid
		if( bidList.getAccount().isEmpty() ) {
			log.error(LOG_ERROR);
			model.addAttribute("msgAccount" , "Your account is empty");
			return BIDLIST_UPDATE;
		}
		if(bidList.getType().isEmpty() ) {
			log.error(LOG_ERROR);
			model.addAttribute("msgType", "Your type is empty");
			return BIDLIST_UPDATE;
		}
		if(bidList.getBidQuantity()==0) {
			log.error(LOG_ERROR);
			model.addAttribute("msgQuantity", "Your bidQuantity is equal to 0");
			return BIDLIST_UPDATE;
		}
		if(result.hasErrors()) {
			log.error("bid update could not be performed");
			return BIDLIST_UPDATE ;    	
		}
		BidList bidListFoundById = bidListService.getBidListById(id).get();
		bidListFoundById.setAccount(bidList.getAccount());
		bidListFoundById.setType(bidList.getType());
		bidListFoundById.setBidQuantity(bidList.getBidQuantity());
		bidListService.saveBidList(bidListFoundById);
		model.addAttribute(bidListFoundById);
		log.info("The update of the bid has been carried out");
		return REDIRECT_BIDLIST_LIST;
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// Find Bid by Id and delete the bid, return to Bid list 
		BidList bidListFoundById = bidListService.getBidListById(id).get();
		bidListService.deleteBidList(bidListFoundById);
		Iterable<BidList> bidLists = bidListService.getBidLists();
		model.addAttribute(bidLists);
		log.info("The bid found by its id has been deleted from the list");
		return REDIRECT_BIDLIST_LIST;
	}
}