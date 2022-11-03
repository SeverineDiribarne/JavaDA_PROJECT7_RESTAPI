package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.utils.ValidityPasswordRules;
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

	/**
	 * get User by User name
	 * @param username
	 * @return user
	 */
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Validate Password
	 * @param user
	 * @return validity password rules
	 */
	@PostMapping("/user/validate")
	public ValidityPasswordRules validatePassword(User user) {
		// check password valid
		ValidityPasswordRules validityPasswordRules = new ValidityPasswordRules();

		if (user.getPassword().length()>=8) {
			validityPasswordRules.containsAtLeastEightCharacters = true;
		}

		for(int i = 0; i < user.getPassword().length(); i++) {
			char passwordCharacter = user.getPassword().charAt(i);

			if(Character.isDigit(passwordCharacter)) {
				validityPasswordRules.containsAtLeastOneNumber = true; 	
			}
			else if (Character.isLetter(passwordCharacter)) {
				validityPasswordRules.containsAtLeastOneLetter = true;

				if(Character.isLowerCase(passwordCharacter)) {
					validityPasswordRules.containsAtLeastOneLowercaseLetter = true;
				}
				else {
					validityPasswordRules.containsAtLeastOneUppercaseLetter = true;
				}
			}
			else {
				validityPasswordRules.containsAtLeastOneSymbol = true;
			}
		}
		return validityPasswordRules;
	}
	/**
	 * Build Error Message
	 * @param validityPasswordRules
	 * @return error Message
	 */
	public String buildErrorMessage(ValidityPasswordRules validityPasswordRules) {
		String errorMessage = "";
		if(!validityPasswordRules.containsAtLeastEightCharacters) {
			errorMessage = "Your password must contain at least eight characters.";
		}
		if(!validityPasswordRules.containsAtLeastOneNumber) {
			errorMessage ="Your password must contain at least one number.";
		}
		if(!validityPasswordRules.containsAtLeastOneLetter) {
			errorMessage ="Your password must contain at least one letter.";
		}
		if(!validityPasswordRules.containsAtLeastOneLowercaseLetter) {
			errorMessage ="Your password must contain at least one lowercase letter.";
		}
		if(!validityPasswordRules.containsAtLeastOneUppercaseLetter) {
			errorMessage ="Your password must contain at least one uppercase letter.";
		}
		if(!validityPasswordRules.containsAtLeastOneSymbol) {
			errorMessage ="Your password must contain at least one symbol.";
		}	
		return errorMessage;
	}
}