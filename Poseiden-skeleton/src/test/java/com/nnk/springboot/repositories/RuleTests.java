package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
 class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	void ruleNameTest() {		
		List<RuleName> listResult = ruleNameRepository.findAll();
		Assertions.assertEquals(0, listResult.size());
		RuleName rule = new RuleName(2, "RuleName", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		Assertions.assertEquals(2, rule.getId());
		Assertions.assertEquals("RuleName", rule.getName());

		// Update
		rule.setName("RuleName Update");
		rule = ruleNameRepository.save(rule);
		Assertions.assertEquals("RuleName Update", rule.getName());

		// Find
		listResult = ruleNameRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		Assertions.assertFalse(ruleList.isPresent());
		}
}