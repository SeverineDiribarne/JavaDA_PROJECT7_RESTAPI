package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RuleNameControllerTest {

	@InjectMocks
	private RuleNameController ruleNameController;

	private MockMvc mockMvc;
	
	@MockBean
	RuleNameService ruleNameService;
	
	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}

	private Model modelTest = new ConcurrentModel();

	private static RuleName ruleNameForMock() {
		RuleName ruleName4 = new RuleName();
		ruleName4.setId(4);
		ruleName4.setName("RuleName4 Test");
		ruleName4.setDescription("RuleName4 test for mock");
		ruleName4.setJson("Json4 test");
		ruleName4.setTemplate("Template4 test");
		ruleName4.setSqlStr("SqlStr4 test");
		ruleName4.setSqlPart("SqlPart4 test");
		return ruleName4;
	}

	private List<RuleName> listForMock() {

		RuleName ruleName1 = new RuleName();
		ruleName1.setId(1);
		ruleName1.setName("RuleName1 Test");
		ruleName1.setDescription("RuleName1 test for mock");
		ruleName1.setJson("Json1 test");
		ruleName1.setTemplate("Template1 test");
		ruleName1.setSqlStr("SqlStr1 test");
		ruleName1.setSqlPart("SqlPart1 test");
		
		RuleName ruleName2 = new RuleName();
		ruleName2.setId(2);
		ruleName2.setName("RuleName2 Test");
		ruleName2.setDescription("RuleName2 test for mock");
		ruleName2.setJson("Json2 test");
		ruleName2.setTemplate("Template2 test");
		ruleName2.setSqlStr("SqlStr2 test");
		ruleName2.setSqlPart("SqlPart2 test");
		
		RuleName ruleName3 = new RuleName();
		ruleName3.setId(3);
		ruleName3.setName("RuleName3 Test");
		ruleName3.setDescription("RuleName3 test for mock");
		ruleName3.setJson("Json3 test");
		ruleName3.setTemplate("Template3 test");
		ruleName3.setSqlStr("SqlStr3 test");
		ruleName3.setSqlPart("SqlPart3 test");
		

		List<RuleName> mockedList = new ArrayList<>();
		mockedList.add(ruleName1);
		mockedList.add(ruleName2);
		mockedList.add(ruleName3);

		return mockedList;
	}
	
	@Test
	void testShouldGetRuleName() throws Exception{
		//given
		when(ruleNameService.getRuleNames()).thenReturn(listForMock());

		//when
		String urlResult = ruleNameController.home(modelTest);
		Object attribute =  modelTest.getAttribute("ruleNames");

		//then
		Iterable<?> ruleNameResults = null;
		if(attribute instanceof Iterable<?>) {
			ruleNameResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of ruleNameResult");
		}
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ruleName/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/list"));
		
		Iterator<?> ruleNameResultsIterator = ruleNameResults.iterator();
		Iterator<RuleName> ruleNameExpectedIterator = listForMock().iterator();

		while(ruleNameResultsIterator.hasNext()){

			RuleName ruleNameResult = (RuleName) ruleNameResultsIterator.next();
			RuleName ruleNameExpected =  ruleNameExpectedIterator.next();

			Assertions.assertEquals(ruleNameResult.getId(), ruleNameExpected.getId());
			Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameExpected.getName()));
			Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameExpected.getDescription()));
			Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameExpected.getJson()));
			Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameExpected.getTemplate()));
			Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameExpected.getSqlStr()));
			Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameExpected.getSqlPart()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("ruleName/list"));
	}
	
	static class AddRuleNameFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			RuleName ruleName4 = ruleNameForMock();
			return Stream.of(Arguments.of(ruleName4));
		}
	}

	@ParameterizedTest
	@ArgumentsSource(AddRuleNameFormArgumentsProvider.class)
	void testShouldAddRuleNameForm(RuleName ruleNameForTest) throws Exception{
		//given
		String urlResult = ruleNameController.addRuleForm(ruleNameForTest, modelTest);

		Object attribute =  modelTest.getAttribute("ruleName");

		//then
		RuleName ruleNameResult = null;
		if(attribute instanceof RuleName) {
			ruleNameResult = (RuleName) attribute;
		}
		else {
			Assertions.fail("Bad type of ruleNameResult");
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ruleName/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/add"));
		Assertions.assertEquals(ruleNameResult.getId() , ruleNameForTest.getId());
		Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameForTest.getName()));
		Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameForTest.getDescription()));
		Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameForTest.getJson()));
		Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameForTest.getTemplate()));
		Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameForTest.getSqlStr()));
		Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameForTest.getSqlPart()));

		
		Assertions.assertEquals(0, urlResult.compareTo("ruleName/add"));	
	}

	@Test
	void testShouldValidateRuleName() throws Exception{
		//given
		//When
		when(ruleNameService.saveRuleName(any())).thenReturn(new RuleName());
		//then
		mockMvc.perform(
				post("/ruleName/validate")
				.param("name","name test")
				.param("description","description test")
				.param("json","Json test")
				.param("template","template test")
				.param("sqlStr","sql str test")
				.param("sqlPart","sql part test")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/list"))
		;

	}

	@Test
	void testShouldShowUpdateForm() throws Exception {
		//given
		//When
		when(ruleNameService.getRuleNameById(any())).thenReturn(Optional.of(ruleNameForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ruleName/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/update"));
	}

	@Test
	void testShouldUpdateRuleName() throws Exception {
		//given
		//when	
		when(ruleNameService.getRuleNameById(any())).thenReturn(Optional.of(ruleNameForMock()));
		when(ruleNameService.saveRuleName(any())).thenReturn(new RuleName());
		//then
		mockMvc.perform(
				post("/ruleName/update/{id}",4)
				.param("name","name test")
				.param("description","description test")
				.param("json","Json test")
				.param("template","template test")
				.param("sqlStr","sql str test")
				.param("sqlPart","sql part test")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ruleName/list"));
	}

	@Test
	void testShouldDeleteRuleName() throws Exception {
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ruleName/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ruleName/list"));
	}
}
