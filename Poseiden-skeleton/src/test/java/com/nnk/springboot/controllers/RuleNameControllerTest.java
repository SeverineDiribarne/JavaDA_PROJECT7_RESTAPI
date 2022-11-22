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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
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
	
	private BindingResult resultTest = new  BeanPropertyBindingResult(ruleNameController, "ruleNameForTest");
	private Model modelTest = new ConcurrentModel();

	private static RuleName ruleNameForMock() {
		RuleName ruleName4 = new RuleName();
		ruleName4.setId(4);
		ruleName4.setName("Name4 Test for mock");
		ruleName4.setDescription("Description4 Test for mock");
		ruleName4.setJson("Json4 Test for mock");
		ruleName4.setTemplate("Template4 Test for mock");
		ruleName4.setSqlStr("SqlStr4 Test for mock");
		ruleName4.setSqlPart("SqlPart4 Test for mock");
		return ruleName4;
	}

	private List<RuleName> listForMock() {

		RuleName ruleName1 = new RuleName();
		ruleName1.setId(1);
		ruleName1.setName("Name1 Test for mock");
		ruleName1.setDescription("Description1 Test for mock");
		ruleName1.setJson("Json1 Test for mock");
		ruleName1.setTemplate("Template1 Test for mock");
		ruleName1.setSqlStr("SqlStr1 Test for mock");
		ruleName1.setSqlPart("SqlPart1 Test for mock");
		
		RuleName ruleName2 = new RuleName();
		ruleName2.setId(2);
		ruleName2.setName("Name2 Test for mock");
		ruleName2.setDescription("Description2 Test for mock");
		ruleName2.setJson("Json2 Test for mock");
		ruleName2.setTemplate("Template2 Test for mock");
		ruleName2.setSqlStr("SqlStr2 Test for mock");
		ruleName2.setSqlPart("SqlPart2 Test for mock");
		
		RuleName ruleName3 = new RuleName();
		ruleName3.setId(3);
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
	
	//Functional Test ShouldGetRuleName
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
	
	//EndPoint Test
		@Test
		void testShouldGetRuleNameForEndPoint() throws Exception{
			//given
			//when
			//then
			mockMvc.perform(MockMvcRequestBuilders
					.get("/ruleName/list")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isOk())
			.andExpect(view().name("ruleName/list"));
		}

	
	static class AddRuleNameFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			RuleName ruleName4 = ruleNameForMock();
			return Stream.of(Arguments.of(ruleName4));
		}
	}

	//Functional Test ShouldAddRuleNameForm
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
		
		Assertions.assertEquals(ruleNameResult.getId() , ruleNameForTest.getId());
		Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameForTest.getName()));
		Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameForTest.getDescription()));
		Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameForTest.getJson()));
		Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameForTest.getTemplate()));
		Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameForTest.getSqlStr()));
		Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameForTest.getSqlPart()));

		
		Assertions.assertEquals(0, urlResult.compareTo("ruleName/add"));	
	}
	
	//EndPoint Test
		@ParameterizedTest
		@ArgumentsSource(AddRuleNameFormArgumentsProvider.class)
		void testShouldAddRuleNameFormForEndPoint(RuleName ruleNameForTest) throws Exception{
			//given
			//when
			//then
			mockMvc.perform(MockMvcRequestBuilders
					.get("/ruleName/add")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED))

			.andExpect(status().isOk())
			.andExpect(view().name("ruleName/add"));
		}

		//Functional Test ShouldValidateRuleName
		@ParameterizedTest
		@ArgumentsSource(AddRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleName(RuleName ruleNameForTest) throws Exception{
			//given
			//when
			ArgumentCaptor<RuleName> capturedRuleNameWhenSaveMethodIsCalled = ArgumentCaptor.forClass(RuleName.class);
			when(ruleNameService.saveRuleName(capturedRuleNameWhenSaveMethodIsCalled.capture())).thenReturn(new RuleName());
			String urlResult = ruleNameController.validate(ruleNameForTest, resultTest, modelTest);
			Object attribute =  modelTest.getAttribute("ruleNames");

			//then
			RuleName ruleNameResult = null;
			if(attribute instanceof RuleName) {
				ruleNameResult = (RuleName) attribute;
			}
			else {
				Assertions.fail("Bad type of ruleNameResult");
			}
			RuleName ruleNameExpected = ruleNameForTest;

			Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameExpected.getName()));
			Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameExpected.getDescription()));
			Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameExpected.getJson()));
			Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameExpected.getTemplate()));
			Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameExpected.getSqlStr()));
			Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameExpected.getSqlPart()));
			Assertions.assertEquals(0, urlResult.compareTo("ruleName/list"));

			RuleName capturedRuleName = capturedRuleNameWhenSaveMethodIsCalled.getValue();
			Assertions.assertEquals(4, capturedRuleName.getId());
			Assertions.assertEquals("Name4 Test for mock", capturedRuleName.getName());
			Assertions.assertEquals("Description4 Test for mock", capturedRuleName.getDescription());
			Assertions.assertEquals("Json4 Test for mock", capturedRuleName.getJson());
			Assertions.assertEquals("Template4 Test for mock", capturedRuleName.getTemplate());
			Assertions.assertEquals("SqlStr4 Test for mock", capturedRuleName.getSqlStr());
			Assertions.assertEquals("SqlPart4 Test for mock", capturedRuleName.getSqlPart());
		}

		//EndPoint Test
	@Test
	void testShouldValidateRuleName() throws Exception{
		//given
		//When
		when(ruleNameService.saveRuleName(any())).thenReturn(new RuleName());
		//then
		mockMvc.perform(
				post("/ruleName/validate")
				.param("name","Name1 Test for mock")
				.param("description","Description1 Test for mock")
				.param("json","Json1 Test for mock")
				.param("template","Template1 Test for mock")
				.param("sqlStr","SqlStr1 Test for mock")
				.param("sqlPart","SqlPart1 Test for mock")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/list"));
	}

	//	static class ErrorArgumentsProvider implements ArgumentsProvider{
	//		@Override
	//		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
	//			RuleName bid = new RuleName();
	//			bid.setAccount("12");
	//			bid.setType("12");
	//			bid.setBidQuantity(-1);
	//			return Stream.of(Arguments.of(bid));
	//		}
	//	}
	//
	//	//Functional Test ShouldValidateBidWithError
	//	@ParameterizedTest
	//	@ArgumentsSource(ErrorArgumentsProvider.class)
	//	void testShouldValidateBidWithError(BidList bidForTest) throws Exception{
	//		//given
	//		String urlResult = null;
	//		//when
	//		ArgumentCaptor<RuleName> capturedRuleNameWhenSaveMethodIsCalled = ArgumentCaptor.forClass(RuleName.class);
	//		when(bidListService.saveBidList(capturedRuleNameWhenSaveMethodIsCalled.capture())).thenReturn(new RuleName());
	//		try {
	//		 urlResult = bidListController.validate(bidForTest, resultTest, modelTest);
	//		}catch(ResponseStatusException response) {
	//			Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
	//			Assertions.assertEquals("Your bid is not found", response.getReason());
	//		}
	//		Object attribute =  modelTest.getAttribute("bidLists");
	//
	//		//then
	//		Assertions.assertNull(attribute);
	//		Assertions.assertNull(urlResult);
	//
	//		RuleName capturedRuleName = capturedRuleNameWhenSaveMethodIsCalled.getValue();
	//		Assertions.assertNull(capturedRuleName);
	//	}


//	//Error Test
//	@Test
//	void testShouldValidateRuleNameWithError() throws Exception{
//		/**
//		 * Voir avec Marc comment dans Thymeleaf on récupère le status 404 (isNotFound), afficher un message d'erreur et retourner sur la page add
//		 */
//		//given
//		//When
//		when(ruleNameService.saveRuleName(any())).thenReturn(new RuleName());
//		//then
//		mockMvc.perform(
//				post("/ruleName/validate")
//				.param("Name", "12")
//				.param("Description", "12.0")
//				.param("Json","12.0" )
//				.param("Template","12.0" )
//				.param("SqlStr","12.0" )
//				.param("SqlPart","12.0" )
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
//
//		.andExpect(status().isOk())
//		.andExpect(view().name("ruleName/add"));
//	}
	
	static class shouldShowUpdateFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			RuleName ruleName4 = ruleNameForMock();
			return Stream.of(Arguments.of(ruleName4));
		}
	}
	
	//Functional Test shouldShowUpdateForm
		@ParameterizedTest
		@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
		void testShouldShowUpdateForm() throws Exception{
			//given
			Integer id = 4;
			when(ruleNameService.getRuleNameById(id)).thenReturn(Optional.of(ruleNameForMock()));

			String urlResult = ruleNameController.showUpdateForm( id,  modelTest);
			Object attribute =  modelTest.getAttribute("ruleName");

			//then
			RuleName ruleNameResult = null;
			if(attribute instanceof RuleName) {
				ruleNameResult = (RuleName) attribute;
			}
			else {
				Assertions.fail("Bad type of ruleNameResult");
			}

			Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameForMock().getName()));
			Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameForMock().getDescription()));
			Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameForMock().getJson()));
			Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameForMock().getTemplate()));
			Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameForMock().getSqlStr()));
			Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameForMock().getSqlPart()));

			Assertions.assertEquals(0, urlResult.compareTo("ruleName/update"));	
		}
	
		//EndPoint Test
	@Test
	void testShouldShowUpdateFormForEndPoint() throws Exception {
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
	
	//Functional test shouldUpdateRuleName
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldUpdateBid(RuleName ruleNameForTest) throws Exception{
		//given
		when(ruleNameService.getRuleNameById(any())).thenReturn(Optional.of(ruleNameForTest));
		ruleNameForTest.setName("Name5 Test for mock");
		ruleNameForTest.setDescription("Description5 Test for mock");
		ruleNameForTest.setJson("Json5 Test for mock");
		ruleNameForTest.setTemplate("Template5 Test for mock");
		ruleNameForTest.setSqlStr("SqlStr5 Test for mock");
		ruleNameForTest.setSqlPart("SqlPart5 Test for mock");
		when(ruleNameService.saveRuleName(any())).thenReturn(ruleNameForTest);

		ArgumentCaptor<RuleName> capturedRuleNameWhenSaveMethodIsCalled = ArgumentCaptor.forClass(RuleName.class);
		when(ruleNameService.saveRuleName(capturedRuleNameWhenSaveMethodIsCalled.capture())).thenReturn(new RuleName());
		
		String urlResult = ruleNameController.updateRuleName(4, ruleNameForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("ruleName");
		//then
		RuleName ruleNameResult = null;
		if(attribute instanceof RuleName) {
			ruleNameResult = (RuleName) attribute;
		}
		else {
			Assertions.fail("Bad type of ruleNameResult");
		}

		Assertions.assertEquals(0, ruleNameResult.getName().compareTo(ruleNameForTest.getName()));
		Assertions.assertEquals(0, ruleNameResult.getDescription().compareTo(ruleNameForTest.getDescription()));
		Assertions.assertEquals(0, ruleNameResult.getJson().compareTo(ruleNameForTest.getJson()));
		Assertions.assertEquals(0, ruleNameResult.getTemplate().compareTo(ruleNameForTest.getTemplate()));
		Assertions.assertEquals(0, ruleNameResult.getSqlStr().compareTo(ruleNameForTest.getSqlStr()));
		Assertions.assertEquals(0, ruleNameResult.getSqlPart().compareTo(ruleNameForTest.getSqlPart()));

		Assertions.assertEquals(0, urlResult.compareTo("redirect:/ruleName/list"));	
		
		RuleName capturedRuleName = capturedRuleNameWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedRuleName.getId());
		Assertions.assertEquals("Name5 Test for mock", capturedRuleName.getName());
		Assertions.assertEquals("Description5 Test for mock", capturedRuleName.getDescription());
		Assertions.assertEquals("Json5 Test for mock", capturedRuleName.getJson());
		Assertions.assertEquals("Template5 Test for mock", capturedRuleName.getTemplate());
		Assertions.assertEquals("SqlStr5 Test for mock", capturedRuleName.getSqlStr());
		Assertions.assertEquals("SqlPart5 Test for mock", capturedRuleName.getSqlPart());
	}

	//EndPoint Test
	@Test
	void testShouldUpdateRuleName() throws Exception {
		//given
		//when	
		when(ruleNameService.getRuleNameById(any())).thenReturn(Optional.of(ruleNameForMock()));
		when(ruleNameService.saveRuleName(any())).thenReturn(new RuleName());
		//then
		mockMvc.perform(
				post("/ruleName/update/{id}",4)
				.param("name","Name5 Test for mock")
				.param("description","Description5 Test for mock")
				.param("json","Json5 Test for mock")
				.param("template","Template5 Test for mock")
				.param("sqlStr","SqlStr5 Test for mock")
				.param("sqlPart","SqlPart5 Test for mock")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ruleName/list"));
	}
	
	private List<RuleName> listForMockDeleted() {

		RuleName ruleName2 = new RuleName();
		ruleName2.setId(2);
		ruleName2.setName("Name2 Test for mock");
		ruleName2.setDescription("Description2 Test for mock");
		ruleName2.setJson("Json2 Test for mock");
		ruleName2.setTemplate("Template2 Test for mock");
		ruleName2.setSqlStr("SqlStr2 Test for mock");
		ruleName2.setSqlPart("SqlPart2 Test for mock");

		RuleName ruleName3 = new RuleName();
		ruleName3.setId(3);
		ruleName3.setName("Name3 Test for Mock");
		ruleName3.setDescription("Description3 Test for mock");
		ruleName3.setJson("Json3 Test for mock");
		ruleName3.setTemplate("Template3 Test for mock");
		ruleName3.setSqlStr("SqlStr3 Test for mock");
		ruleName3.setSqlPart("SqlPart3 Test for mock");

		List<RuleName> mockedList = new ArrayList<>();
		mockedList.add(ruleName2);
		mockedList.add(ruleName3);

		return mockedList;
	}
	
	//Functional Test shouldDeleteRuleName
	@Test
	void testShouldDeleteRuleName() throws Exception{
		//given
		Integer id =1;
		//when
		ArgumentCaptor<RuleName> capturedRuleNameWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(RuleName.class);
		Mockito.doNothing().when(ruleNameService).deleteRuleName(capturedRuleNameWhenDeleteMethodIsCalled.capture());
		
		when(ruleNameService.getRuleNameById(id)).thenReturn(Optional.of(listForMock().get(0)));
		when(ruleNameService.getRuleNames()).thenReturn(listForMockDeleted());
		String urlResult = ruleNameController.deleteRuleName(id, modelTest);
		Object attribute =  modelTest.getAttribute("ruleNameList");

		//then
		Iterable<?> ruleNameResults = null;
		if(attribute instanceof Iterable<?>) {
			ruleNameResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of ruleNameResult");
		}
		Iterator<?> ruleNameResultsIterator = ruleNameResults.iterator();
		Iterator<RuleName> ruleNameExpectedIterator = listForMockDeleted().iterator();

		RuleName ruleNameResult1 = (RuleName) ruleNameResultsIterator.next();
		RuleName ruleNameExpected1 =  ruleNameExpectedIterator.next();
		Assertions.assertEquals(0, ruleNameResult1.getName().compareTo(ruleNameExpected1.getName()));
		Assertions.assertEquals(0, ruleNameResult1.getDescription().compareTo(ruleNameExpected1.getDescription()));
		Assertions.assertEquals(0,ruleNameResult1.getJson().compareTo( ruleNameExpected1.getJson()));
		Assertions.assertEquals(0,ruleNameResult1.getTemplate().compareTo(ruleNameExpected1.getTemplate()));
		Assertions.assertEquals(0, ruleNameResult1.getSqlStr().compareTo(ruleNameExpected1.getSqlStr()));
		Assertions.assertEquals(0, ruleNameResult1.getSqlPart().compareTo(ruleNameExpected1.getSqlPart()));

		RuleName ruleNameResult2 = (RuleName) ruleNameResultsIterator.next();
		RuleName ruleNameExpected2 =  ruleNameExpectedIterator.next();

		Assertions.assertEquals(0, ruleNameResult2.getName().compareTo(ruleNameExpected2.getName()));
		Assertions.assertEquals(0, ruleNameResult2.getDescription().compareTo(ruleNameExpected2.getDescription()));
		Assertions.assertEquals(0,ruleNameResult2.getJson().compareTo( ruleNameExpected2.getJson()));
		Assertions.assertEquals(0,ruleNameResult2.getTemplate().compareTo(ruleNameExpected2.getTemplate()));
		Assertions.assertEquals(0, ruleNameResult2.getSqlStr().compareTo(ruleNameExpected2.getSqlStr()));
		Assertions.assertEquals(0, ruleNameResult2.getSqlPart().compareTo(ruleNameExpected2.getSqlPart()));
		
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/ruleName/list"));	
		
		RuleName capturedRuleName = capturedRuleNameWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals("Name1 Test for mock", capturedRuleName.getName());
		Assertions.assertEquals("Description1 Test for mock", capturedRuleName.getDescription());
		Assertions.assertEquals("Json1 Test for mock", capturedRuleName.getJson());
		Assertions.assertEquals("Template1 Test for mock", capturedRuleName.getTemplate());
		Assertions.assertEquals("SqlStr1 Test for mock", capturedRuleName.getSqlStr());
		Assertions.assertEquals("SqlPart1 Test for mock", capturedRuleName.getSqlPart());
	}

	//EndPoint Test
	@Test
	void testShouldDeleteRuleNameForEndPoint() throws Exception {
		//given
		Integer id =1;
		//when
		when(ruleNameService.getRuleNameById(id)).thenReturn(Optional.of(listForMock().get(1)));
		when(ruleNameService.getRuleNames()).thenReturn(listForMock());
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ruleName/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/ruleName/list"));
	}
}
