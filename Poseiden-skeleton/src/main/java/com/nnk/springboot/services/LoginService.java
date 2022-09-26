package com.nnk.springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.utils.ValidityPasswordRules;
@Service
public class LoginService {

	@PostMapping("/login/validate")
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