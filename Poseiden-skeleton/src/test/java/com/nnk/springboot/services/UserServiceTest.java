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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

	private static final Integer USER_ID = 1;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	UserService userService = new UserService();

	public List<User> listForMock() {

		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Username1 Test for mock");
		user1.setPassword("Password1 Test for mock");
		user1.setFullname("Fullname1 Test for mock");
		user1.setRole("Role1 Test for mock");

		User user2 = new User();
		user2.setId(1);
		user2.setUsername("Username1 Test for mock");
		user2.setPassword("Password1 Test for mock");
		user2.setFullname("Fullname1 Test for mock");
		user2.setRole("Role1 Test for mock");

		User user3 = new User();
		user3.setId(1);
		user3.setUsername("Username1 Test for mock");
		user3.setPassword("Password1 Test for mock");
		user3.setFullname("Fullname1 Test for mock");
		user3.setRole("Role1 Test for mock");

		List<User> mockedList = new ArrayList<>();
		mockedList.add(user1);
		mockedList.add(user2);
		mockedList.add(user3);

		return mockedList;
	}
	
	/**
	 * Call GetUsers method and verify that elements are presents
	 * @return all list of user
	 */
	@Test
	void testGetUsers() {
		//Given
		when(userRepository.findAll()).thenReturn(listForMock());

		//When
		Iterable<User> userResults = userService.getUsers();

		//Then
		Iterator<User> userResultsIterator = userResults.iterator();
		Iterator<User> userExpectedIterator = listForMock().iterator();

		while(userResultsIterator.hasNext()){
			User userResult = userResultsIterator.next();
			User userExpected =  userExpectedIterator.next();
			Assertions.assertEquals(userResult.getId(), userExpected.getId());
			Assertions.assertEquals(0, userResult.getUsername().compareTo(userExpected.getUsername()));
			Assertions.assertEquals(0, userResult.getPassword().compareTo(userExpected.getPassword()));
			Assertions.assertEquals(0, userResult.getFullname().compareTo(userExpected.getFullname()));
			Assertions.assertEquals(0, userResult.getRole().compareTo(userExpected.getRole()));
		}
	}
	
	/**
	 * Method findById
	 * @param id
	 * @return user
	 */
	private Optional<User> findById(Integer id){
		for(User user : listForMock()) {
			if(user.getId()== id.intValue()) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Call GetUserById method and verify that the element is the correct one
	 * @param id
	 * @return user by his id
	 */
	@Test
	void testGetUserById() {
		//Given
		when(userRepository.findById(USER_ID)).thenReturn(findById(USER_ID));

		//When
		Optional<User> userResult = userService.getUserById(USER_ID);
		
		//Then
		Assertions.assertEquals(true, userResult.isPresent());
		Assertions.assertEquals(USER_ID.intValue(), userResult.get().getId() );
		Assertions.assertEquals("Username1 Test for mock", userResult.get().getUsername());
		Assertions.assertEquals("Password1 Test for mock", userResult.get().getPassword());
		Assertions.assertEquals("Fullname1 Test for mock", userResult.get().getFullname());
		Assertions.assertEquals("Role1 Test for mock", userResult.get().getRole());
	}
	
	/**
	 * Call saveUser method and verify element is saved in DB
	 * @param user
	 * @return save or update user in BDD
	 */
	@Test
	void testSaveUser() throws Exception {
		//Given
		User user4 = new User();
		user4.setId(4);
		user4.setUsername("Username4 Test for mock");
		user4.setPassword("Password4 Test for mock");
		user4.setFullname("Fullname4 Test for mock");
		user4.setRole("Role4 Test for mock");

		//When
		ArgumentCaptor<User> capturedUserWhenSaveMethodIsCalled = ArgumentCaptor.forClass(User.class);
		when(userRepository.save(capturedUserWhenSaveMethodIsCalled.capture())).thenReturn(user4);
		userService.saveUser(user4);

		//Then
		User capturedUser = capturedUserWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedUser.getId());
		Assertions.assertEquals("Username4 Test for mock", capturedUser.getUsername());
		Assertions.assertEquals("Password4 Test for mock", capturedUser.getPassword());
		Assertions.assertEquals("Fullname4 Test for mock", capturedUser.getFullname());
		Assertions.assertEquals("Role4 Test for mock", capturedUser.getRole());
	}	
	
	/**
	 * Call deleteUser method and verify element don't exist
	 */
	@Test
	void testDeleteUser() {
		//Given
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Username1 Test for mock");
		user1.setPassword("Password1 Test for mock");
		user1.setFullname("Fullname1 Test for mock");
		user1.setRole("Role1 Test for mock");
		
		//When
		ArgumentCaptor<User> capturedUserWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(User.class);
		Mockito.doNothing().when(userRepository).delete(capturedUserWhenDeleteMethodIsCalled.capture());
		userService.deleteUser(user1);
		
		//Then
		User capturedUser = capturedUserWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedUser.getId());
		Assertions.assertEquals("Username1 Test for mock", capturedUser.getUsername());
		Assertions.assertEquals("Password1 Test for mock", capturedUser.getPassword());
		Assertions.assertEquals("Fullname1 Test for mock", capturedUser.getFullname());
		Assertions.assertEquals("Role1 Test for mock", capturedUser.getRole());
	}
}