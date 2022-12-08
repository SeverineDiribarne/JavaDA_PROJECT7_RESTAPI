package com.nnk.springboot.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RuleNameServiceTest {

	private static final Integer RULE_ID = 1;

	@Mock
	private RuleNameRepository ruleNameRepository;

	@InjectMocks
	RuleNameService ruleNameService = new RuleNameService();

	public List<RuleName> listForMock() {

		RuleName ruleName1 = new RuleName();
		ruleName1.setId(1);
		ruleName1.setName("Name1 Test for mock");
		ruleName1.setDescription("Description1 Test for mock");
		ruleName1.setJson("Json1 Test for mock");
		ruleName1.setTemplate("Template1 Test for mock");
		ruleName1.setSqlStr("SqlStr1 Test for mock");
		ruleName1.setSqlPart("SqlPart1 Test for mock");

		RuleName ruleName2 = new RuleName();
		ruleName2.setId(1);
		ruleName2.setName("Name2 Test for mock");
		ruleName2.setDescription("Description2 Test for mock");
		ruleName2.setJson("Json2 Test for mock");
		ruleName2.setTemplate("Template2 Test for mock");
		ruleName2.setSqlStr("SqlStr2 Test for mock");
		ruleName2.setSqlPart("SqlPart2 Test for mock");

		RuleName ruleName3 = new RuleName();
		ruleName3.setId(1);
		ruleName3.setName("Name3 Test for mock");
		ruleName3.setDescription("Description3 Test for mock");
		ruleName3.setJson("Json3 Test for mock");
		ruleName3.setTemplate("Template3 Test for mock");
		ruleName3.setSqlStr("SqlStr3 Test for mock");
		ruleName3.setSqlPart("SqlPart3 Test for mock");

		List<RuleName> mockedList = new ArrayList<>();
		mockedList.add(ruleName1);
		mockedList.add(ruleName2);
		mockedList.add(ruleName3);

		return mockedList;
	}

	/**
	 * Call GetRuleNames method and verify that elements are presents
	 * @return all list of ruleName
	 */
	@Test
	void testGetRuleNames() {
		//Given
		when(ruleNameRepository.findAll()).thenReturn(listForMock());
		//When
		Iterable<RuleName> ruleNameResults = ruleNameService.getRuleNames();
		//Then
		Iterator<RuleName> ruleNameResultsIterator = ruleNameResults.iterator();
		Iterator<RuleName> ruleNameExpectedIterator = listForMock().iterator();

		while(ruleNameResultsIterator.hasNext()){
			RuleName ruleNameResult = ruleNameResultsIterator.next();
			RuleName ruleNameExpected =  ruleNameExpectedIterator.next();
			Assertions.assertEquals(ruleNameResult.getId(),ruleNameExpected.getId());
			Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameExpected.getName()));
			Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameExpected.getDescription()));
			Assertions.assertEquals(0,ruleNameResult.getJson().compareTo(ruleNameExpected.getJson()));
			Assertions.assertEquals(0,ruleNameResult.getTemplate().compareTo(ruleNameExpected.getTemplate()));
			Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameExpected.getSqlStr()));
			Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameExpected.getSqlPart()));
		}
	}

	/**
	 * Method findById
	 * @param id
	 * @return ruleName
	 */
	private Optional<RuleName> findById(Integer id){
		for(RuleName ruleName : listForMock()) {
			if(ruleName.getId()== id.intValue()) {
				return Optional.of(ruleName);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Call GetRuleNameById method and verify that the element is the correct one
	 * @param id
	 * @return ruleName by his id
	 */
	@Test
	void testGetRuleNameById() {
		//Given
		when(ruleNameRepository.findById(RULE_ID)).thenReturn(findById(RULE_ID));

		//When
		Optional<RuleName> ruleNameResult = ruleNameService.getRuleNameById(RULE_ID);

		//Then
		Assertions.assertEquals(true, ruleNameResult.isPresent());
		Assertions.assertEquals(RULE_ID.intValue(), ruleNameResult.get().getId() );
		Assertions.assertEquals("Name1 Test for mock", ruleNameResult.get().getName());
		Assertions.assertEquals("Description1 Test for mock", ruleNameResult.get().getDescription());
		Assertions.assertEquals("Json1 Test for mock",ruleNameResult.get().getJson());
		Assertions.assertEquals("Template1 Test for mock",ruleNameResult.get().getTemplate());
		Assertions.assertEquals("SqlStr1 Test for mock", ruleNameResult.get().getSqlStr());
		Assertions.assertEquals("SqlPart1 Test for mock", ruleNameResult.get().getSqlPart());
	}

	/*
	 * Call saveRuleName method and verify element is saved in DB
	 * @param ruleName
	 * @return save or update ruleName in BDD
	 */
	@Test
	void testSaveRuleName() throws Exception {
		//Given
		RuleName ruleName4 = new RuleName();
		ruleName4.setId(4);
		ruleName4.setName("Name4 Test for mock");
		ruleName4.setDescription("Description4 Test for mock");
		ruleName4.setJson("Json4 Test for mock");
		ruleName4.setTemplate("Template4 Test for mock");
		ruleName4.setSqlStr("SqlStr4 Test for mock");
		ruleName4.setSqlPart("SqlPart4 Test for mock");

		//When
		ArgumentCaptor<RuleName> capturedRuleNameWhenSaveMethodIsCalled = ArgumentCaptor.forClass(RuleName.class);
		when(ruleNameRepository.save(capturedRuleNameWhenSaveMethodIsCalled.capture())).thenReturn(ruleName4);
		ruleNameService.saveRuleName(ruleName4);

		//Then
		RuleName capturedRuleName = capturedRuleNameWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedRuleName.getId());
		Assertions.assertEquals("Name4 Test for mock", capturedRuleName.getName());
		Assertions.assertEquals("Description4 Test for mock", capturedRuleName.getDescription());
		Assertions.assertEquals("Json4 Test for mock", capturedRuleName.getJson());
		Assertions.assertEquals("Template4 Test for mock", capturedRuleName.getTemplate());
		Assertions.assertEquals("SqlStr4 Test for mock", capturedRuleName.getSqlStr());
		Assertions.assertEquals("SqlPart4 Test for mock", capturedRuleName.getSqlPart());
	}	

	/**
	 * Call deleteRating method and verify element don't exist
	 */
	@Test
	void testDeleteRuleName() {
		//Given
		RuleName ruleName1 = new RuleName();
		ruleName1.setId(1);
		ruleName1.setName("Name1 Test for mock");
		ruleName1.setDescription("Description1 Test for mock");
		ruleName1.setJson("Json1 Test for mock");
		ruleName1.setTemplate("Template1 Test for mock");
		ruleName1.setSqlStr("SqlStr1 Test for mock");
		ruleName1.setSqlPart("SqlPart1 Test for mock");
		//When
		ArgumentCaptor<RuleName> capturedRuleNameWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(RuleName.class);
		Mockito.doNothing().when(ruleNameRepository).delete(capturedRuleNameWhenDeleteMethodIsCalled.capture());
		ruleNameService.deleteRuleName(ruleName1);
		
		//Then
		RuleName capturedRuleName = capturedRuleNameWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedRuleName.getId());
		Assertions.assertEquals("Name1 Test for mock", capturedRuleName.getName());
		Assertions.assertEquals("Description1 Test for mock", capturedRuleName.getDescription());
		Assertions.assertEquals( "Json1 Test for mock", capturedRuleName.getJson());
		Assertions.assertEquals( "Template1 Test for mock", capturedRuleName.getTemplate());
		Assertions.assertEquals( "SqlStr1 Test for mock", capturedRuleName.getSqlStr());
		Assertions.assertEquals( "SqlPart1 Test for mock", capturedRuleName.getSqlPart());
	}
}