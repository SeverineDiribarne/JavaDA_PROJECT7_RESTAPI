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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.utils.ValidityPasswordRules;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}
	
	private BindingResult resultTest = new  BeanPropertyBindingResult(userController, "userForTest");
	private Model modelTest = new ConcurrentModel();
	
	private static User userForMock() {
		User user4 = new User();
		user4.setId(4);
		user4.setFullname("fullName4");
		user4.setUsername("username4");
		user4.setPassword("password4");
		user4.setRole("USER");
		return user4;
	}
	
	private List<User> listForMock() {

		User user1 = new User();
		user1.setId(1);
		user1.setFullname("fullName1");
		user1.setUsername("username1");
		user1.setPassword("password1");
		user1.setRole("USER");

		User user2 = new User();
		user2.setId(2);
		user2.setFullname("fullName2");
		user2.setUsername("username2");
		user2.setPassword("password2");
		user2.setRole("USER");

		User user3 = new User();
		user3.setId(4);
		user3.setFullname("fullName3");
		user3.setUsername("username3");
		user3.setPassword("password3");
		user3.setRole("USER");
		
		List<User> mockedList = new ArrayList<>();
		mockedList.add(user1);
		mockedList.add(user2);
		mockedList.add(user3);

		return mockedList;
	}
	
	@Test
	void testShouldGetUser(){
		//given
		when(userService.getUsers()).thenReturn(listForMock());

		//when
		String urlResult = userController.home(modelTest);
		Object attribute =  modelTest.getAttribute("users");

		//then
		Iterable<?> userResults = null;
		if(attribute instanceof Iterable<?>) {
			userResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of userResult");
		}
		
		Iterator<?> userResultsIterator = userResults.iterator();
		Iterator<User> userExpectedIterator = listForMock().iterator();

		while(userResultsIterator.hasNext()){

			User userResult = (User) userResultsIterator.next();
			User userExpected =  userExpectedIterator.next();

			Assertions.assertEquals(userResult.getId(),userExpected.getId());
			Assertions.assertEquals(0, userResult.getFullname().compareTo(userExpected.getFullname()));
			Assertions.assertEquals(userResult.getUsername(), userExpected.getUsername());
			Assertions.assertEquals(0, userResult.getPassword().compareTo(userExpected.getPassword()));
			Assertions.assertEquals(0, userResult.getRole().compareTo(userExpected.getRole()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("user/list"));
	}
	
	static class AddUserFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			User user4 = userForMock();
			return Stream.of(Arguments.of(user4));
		}
	}

	@ParameterizedTest
	@ArgumentsSource(AddUserFormArgumentsProvider.class)
	void testShouldAddUserForm(User userForTest) throws Exception{
		//given
		String urlResult = userController.addUser(userForTest, modelTest);

		Object attribute =  modelTest.getAttribute("user");

		//then
		User userResult = null;
		if(attribute instanceof User) {
			userResult = (User) attribute;
		}
		else {
			Assertions.fail("Bad type of userResult");
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("/user/add"));
		Assertions.assertEquals(userResult.getId() , userForTest.getId());
		Assertions.assertEquals(0, userResult.getFullname().compareTo(userForTest.getFullname()));
		Assertions.assertEquals(0, userResult.getUsername().compareTo(userForTest.getUsername()));
		Assertions.assertEquals(userResult.getPassword(), userForTest.getPassword());
		Assertions.assertEquals(userResult.getRole(),(userForTest.getRole()));
		
		Assertions.assertEquals(0, urlResult.compareTo("/user/add"));	
	}

	@Test
	void testShouldValidateUser() throws Exception{
		//given
		ValidityPasswordRules validity = new ValidityPasswordRules();
		validity.containsAtLeastEightCharacters = true;
		validity.containsAtLeastOneLetter = true;
		validity.containsAtLeastOneLowercaseLetter = true;
		validity.containsAtLeastOneNumber= true;
		validity.containsAtLeastOneSymbol =true;
		validity.containsAtLeastOneUppercaseLetter= true;
		
		//When
		when(userService.saveUser(any())).thenReturn(new User());
		when(userService.validatePassword(any())).thenReturn(validity);
		when(userService.buildErrorMessage(any())).thenReturn(new ArrayList<>());
		//then
		mockMvc.perform(
				post("/user/validate")
				.param("fullname","fullname1")
				.param("username","username1")
				.param("password","@Password1")
				.param("role", "USER")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/user/list"));
	}
	
	private static User emptyUsernameUserForMock() {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("");
		user1.setFullname("fullname1");
		user1.setPassword("password1");
		user1.setRole("role1");

		return user1;
	}
	static class EmptyUsernameUserFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			User user1 = emptyUsernameUserForMock();
			return Stream.of(Arguments.of(user1));
		}
	}

	//Functional Test ShouldValidateUserEmptyUsername
	@ParameterizedTest
	@ArgumentsSource(EmptyUsernameUserFormArgumentsProvider.class)
	void testShouldValidateUserEmptyUsername(User userForTest) throws Exception{
		//given
		//when
		String urlResult = userController.validate(userForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgUsername";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("/user/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your username is empty"));
	}
	
	private static User emptyFullnameUserForMock() {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("username1");
		user1.setFullname("");
		user1.setPassword("password1");
		user1.setRole("role1");

		return user1;
	}
	static class EmptyFullnameUserFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			User user1 = emptyFullnameUserForMock();
			return Stream.of(Arguments.of(user1));
		}
	}

	//Functional Test ShouldValidateUserEmptyFullname
		@ParameterizedTest
		@ArgumentsSource(EmptyFullnameUserFormArgumentsProvider.class)
		void testShouldValidateUserEmptyFullname(User userForTest) throws Exception{
			//given
			//when
			String urlResult = userController.validate(userForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgFullname";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("/user/add"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your fullname is empty"));
		}
		
		private static User emptyRoleUserForMock() {
			User user1 = new User();
			user1.setId(1);
			user1.setUsername("username1");
			user1.setFullname("fullname1");
			user1.setPassword("password1");
			user1.setRole(null);

			return user1;
		}
		static class EmptyRoleUserFormArgumentsProvider implements ArgumentsProvider{
			@Override
			public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
				User user1 = emptyRoleUserForMock();
				return Stream.of(Arguments.of(user1));
			}
		}

		//Functional Test ShouldValidateUserEmptyRole
			@ParameterizedTest
			@ArgumentsSource(EmptyRoleUserFormArgumentsProvider.class)
			void testShouldValidateUserEmptyRole(User userForTest) throws Exception{
				//given
				//when
				String urlResult = userController.validate(userForTest, resultTest, modelTest);
				//then
				String attributeKey = "msgRole";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);

				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("/user/add"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your role is empty"));
			}

	@Test
	void testShouldShowUpdateForm() throws Exception {
		//given
		//When
		when(userService.getUserById(any())).thenReturn(Optional.of(userForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("/user/update"));
	}

	@Test
	void testShouldUpdateUser() throws Exception {
		//given
		//when	
		when(userService.getUserById(any())).thenReturn(Optional.of(userForMock()));
		when(userService.saveUser(any())).thenReturn(new User());
		//then
		mockMvc.perform(
				post("/user/update/{id}",4)
				.param("fullname","fullname1")
				.param("username","username1")
				.param("password","password1")
				.param("role", "USER")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/user/list"));
	}
	
	//Functional Test ShouldUpdateUserEmptyUsername
	@ParameterizedTest
	@ArgumentsSource(EmptyUsernameUserFormArgumentsProvider.class)
	void testShouldUpdateUserEmptyUsername(User userForTest) throws Exception{
		//given
		//when
		String urlResult = userController.updateUser(1, userForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgUsername";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("/user/update"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your username is empty"));
	}

	//Functional Test ShouldUpdateUserEmptyFullname
		@ParameterizedTest
		@ArgumentsSource(EmptyFullnameUserFormArgumentsProvider.class)
		void testShouldUpdateUserEmptyFullname(User userForTest) throws Exception{
			//given
			//when
			String urlResult = userController.updateUser(1, userForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgFullname";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("/user/update"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your fullname is empty"));
		}

		//Functional Test ShouldUpdateUserEmptyRole
			@ParameterizedTest
			@ArgumentsSource(EmptyRoleUserFormArgumentsProvider.class)
			void testShouldUpdateUserEmptyRole(User userForTest) throws Exception{
				//given
				//when
				String urlResult = userController.updateUser(1, userForTest, resultTest, modelTest);
				//then
				String attributeKey = "msgRole";
				Object errorMessageReturned = modelTest.getAttribute(attributeKey);

				String errorMessageResult = null;
				if(errorMessageReturned instanceof String) {
					errorMessageResult = (String) errorMessageReturned;
				}
				else {
					Assertions.fail("Bad type of errorMessageResult");
				}

				Assertions.assertEquals(0, urlResult.compareTo("/user/update"));
				Assertions.assertEquals(0, errorMessageResult.compareTo("Your role is empty"));
			}


	@Test
	void testShouldDeleteUser() throws Exception {
		//given
		//when
		when(userService.getUserById(any())).thenReturn(Optional.of(userForMock()));
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/user/list"));
	}

}
