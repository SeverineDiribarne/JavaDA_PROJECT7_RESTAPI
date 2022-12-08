package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
@Service
public class RuleNameService {

	@Autowired
	RuleNameRepository ruleNameRepository;
	
	/**
	 * get RuleNames
	 * @return list of ruleName
	 */
	public Iterable<RuleName> getRuleNames() {
		return ruleNameRepository.findAll();
	}
	
	/**
	 * get ruleName by id
	 * @param id
	 * @return ruleName
	 */
	public Optional<RuleName> getRuleNameById(Integer id) {
		return ruleNameRepository.findById(id);
	}
	
	/**
	 * save RuleName
	 * @param ruleName
	 * @return ruleName 
	 */
	public RuleName saveRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}
	
	/**
	 * Delete ruleName
	 * @param ruleName
	 */
	public void deleteRuleName(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);
	}
}