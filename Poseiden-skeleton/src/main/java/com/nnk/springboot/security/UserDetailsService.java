package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

@Service
public class UserDetailsService {

	@Autowired
	private UserService userService;

		/**
		 * load user by username
		 * @param username
		 * @return new MyMainUser 
		 */
		public UserDetails loadUserByUsername(String username) {
			User user = userService.getUserByUsername(username);
			if(user == null) {
				throw new UsernameNotFoundException(username);
			}
			return new MyMainUser(user);
		}


//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userService.getUserByUsername(username);
//		user.orElseThrow(() -> newUsernameNotFoundException("Username : "+username+" not found"));
//		return user.map(CustomUserDetails::new).get();
//		   } 
}
