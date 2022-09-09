package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * get Users
	 * @return list of users
	 */
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	/**
	 * get User by id
	 * @param id
	 * @return user
	 */
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}
	
	/**
	 * save User
	 * @param user
	 * @return user 
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * Save all users
	 * @param users
	 * @return list of user
	 */
	public List<User> saveAllUsers(Iterable<User> users) {
		return userRepository.saveAll(users);
	}
	/**
	 * delete user by id
	 * @param id
	 */
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * Delete user
	 * @param user
	 */
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
