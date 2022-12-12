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
			Assertions.assertEquals(0, urlResult.compareTo("redirect:/ruleName/list"));

			RuleName capturedRuleName = capturedRuleNameWhenSaveMethodIsCalled.getValue();
			Assertions.assertEquals(4, capturedRuleName.getId());
			Assertions.assertEquals("Name4 Test for mock", capturedRuleName.getName());
			Assertions.assertEquals("Description4 Test for mock", capturedRuleName.getDescription());
			Assertions.assertEquals("Json4 Test for mock", capturedRuleName.getJson());
			Assertions.assertEquals("Template4 Test for mock", capturedRuleName.getTemplate());
			Assertions.assertEquals("SqlStr4 Test for mock", capturedRuleName.getSqlStr());
			Assertions.assertEquals("SqlPart4 Test for mock", capturedRuleName.getSqlPart());
		}
		
		private static RuleName emptyNameRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("");
			ruleName1.setDescription("Your description is empty");
			ruleName1.setJson("Your Json is empty");
			ruleName1.setTemplate("Your Template  is empty");
			ruleName1.setSqlStr("Your SqlStr  is empty");
			ruleName1.setSqlPart("Your SqlPart  is empty");
			return ruleName1;
		}
		static class EmptyNameRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptyNameRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptyName
		@ParameterizedTest
		@ArgumentsSource(EmptyNameRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptyName(RuleName emptyNameRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptyNameRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgName";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your name is empty"));
		}
		
		private static RuleName emptyDescriptionRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("Your name is empty");
			ruleName1.setDescription("");
			ruleName1.setJson("Your Json is empty");
			ruleName1.setTemplate("Your Template is empty");
			ruleName1.setSqlStr("Your SqlStr is empty");
			ruleName1.setSqlPart("Your SqlPart is empty");
			return ruleName1;
		}
		
		static class EmptyDescriptionRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptyDescriptionRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptyDescription
		@ParameterizedTest
		@ArgumentsSource(EmptyDescriptionRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptyDescription(RuleName emptyDescriptionRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptyDescriptionRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgDescription";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your description is empty"));
		}
		
		private static RuleName emptyJsonRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("Your name is empty");
			ruleName1.setDescription("Your description is empty");
			ruleName1.setJson("");
			ruleName1.setTemplate("Your Template is empty");
			ruleName1.setSqlStr("Your SqlStr is empty");
			ruleName1.setSqlPart("Your SqlPart is empty");
			return ruleName1;
		}
		
		static class EmptyJsonRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptyJsonRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptyJson
		@ParameterizedTest
		@ArgumentsSource(EmptyJsonRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptyJson(RuleName emptyJsonRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptyJsonRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgJson";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your Json is empty"));
		}
		
		private static RuleName emptyTemplateRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("Your name is empty");
			ruleName1.setDescription("Your description is empty");
			ruleName1.setJson("Your Json is empty");
			ruleName1.setTemplate("");
			ruleName1.setSqlStr("Your SqlStr is empty");
			ruleName1.setSqlPart("Your SqlPart is empty");
			return ruleName1;
		}
		
		static class EmptyTemplateRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptyTemplateRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptyTemplate
		@ParameterizedTest
		@ArgumentsSource(EmptyTemplateRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptyTemplate(RuleName emptyTemplateRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptyTemplateRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgTemplate";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your Template is empty"));
		}
		
		private static RuleName emptySqlStrRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("Your name is empty");
			ruleName1.setDescription("Your description is empty");
			ruleName1.setJson("Your Json is empty");
			ruleName1.setTemplate("Your Template is empty");
			ruleName1.setSqlStr("");
			ruleName1.setSqlPart("Your SqlPart is empty");
			return ruleName1;
		}
		static class EmptySqlStrRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptySqlStrRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptySqlStr
		@ParameterizedTest
		@ArgumentsSource(EmptySqlStrRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptySqlStr(RuleName emptySqlStrRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptySqlStrRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgSqlStr";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your SqlStr is empty"));
		}
		
		private static RuleName emptySqlPartRuleNameForMock() {
			RuleName ruleName1 = new RuleName();
			ruleName1.setId(1);
			ruleName1.setName("Your name is empty");
			ruleName1.setDescription("Your description is empty");
			ruleName1.setJson("Your Json is empty");
			ruleName1.setTemplate("Your Template is empty");
			ruleName1.setSqlStr("Your SqlStr is empty");
			ruleName1.setSqlPart("");
			return ruleName1;
		}
		static class EmptySqlPartRuleNameFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				RuleName ruleName1 = emptySqlPartRuleNameForMock();
				return Stream.of(Arguments.of(ruleName1));
			}
		}

		//Functional Test ShouldValidateRuleNameEmptySqlPart
		@ParameterizedTest
		@ArgumentsSource(EmptySqlPartRuleNameFormArgumentsProvider.class)
		void testShouldValidateRuleNameEmptySqlPart(RuleName emptySqlPartRuleNameForTest) throws Exception{
			//Given
			//When
			String urlResult = ruleNameController.validate(emptySqlPartRuleNameForTest, resultTest, modelTest);
			
			//Then
			String attributeKey = "msgSqlPart";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);
			
			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("rulename/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your SqlPart is empty"));
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

		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/ruleName/list"));
	}
	
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
	void testShouldUpdateRuleName(RuleName ruleNameForTest) throws Exception{
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

	//Functional Test ShouldUpdateRuleNameEmptyName
			@ParameterizedTest
			@ArgumentsSource(EmptyNameRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptyName(RuleName emptyNameRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptyNameRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgName";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your name is empty"));
			}

			//Functional Test ShouldUpdateRuleNameEmptyDescription
			@ParameterizedTest
			@ArgumentsSource(EmptyDescriptionRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptyDescription(RuleName emptyDescriptionRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptyDescriptionRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgDescription";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your description is empty"));
			}

			//Functional Test ShouldUpdateRuleNameEmptyJson
			@ParameterizedTest
			@ArgumentsSource(EmptyJsonRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptyJson(RuleName emptyJsonRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptyJsonRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgJson";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your Json is empty"));
			}
			
			//Functional Test ShouldUpdateRuleNameEmptyTemplate
			@ParameterizedTest
			@ArgumentsSource(EmptyTemplateRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptyTemplate(RuleName emptyTemplateRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptyTemplateRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgTemplate";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your Template is empty"));
			}
			
			//Functional Test ShouldUpdateRuleNameEmptySqlStr
			@ParameterizedTest
			@ArgumentsSource(EmptySqlStrRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptySqlStr(RuleName emptySqlStrRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptySqlStrRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgSqlStr";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your SqlStr is empty"));
			}

			//Functional Test ShouldUpdateRuleNameEmptySqlPart
			@ParameterizedTest
			@ArgumentsSource(EmptySqlPartRuleNameFormArgumentsProvider.class)
			void testShouldUpdateRuleNameEmptySqlPart(RuleName emptySqlPartRuleNameForTest) throws Exception{
				//Given
				//When
				String urlResult = ruleNameController.updateRuleName(1, emptySqlPartRuleNameForTest, resultTest, modelTest);
				
				//Then
				String attributeKey = "msgSqlPart";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);
				
				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("rulename/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your SqlPart is empty"));
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
