package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;
	
	/**
	 * get Trades
	 * @return list of trade
	 */
	public Iterable<Trade> getTrades() {
		return tradeRepository.findAll();
	}
	
	/**
	 * get trade by id
	 * @param id
	 * @return trade
	 */
	public Optional<Trade> getTradeById(Integer id) {
		return tradeRepository.findById(id);
	}
	
	/**
	 * save Trade
	 * @param trade
	 * @return trade 
	 */
	public Trade saveTrade(Trade trade) {
		return tradeRepository.save(trade);
	}
	
	/**
	 * Delete trade
	 * @param trade
	 */
	public void deleteTrade(Trade trade) {
		tradeRepository.delete(trade);
	}
}