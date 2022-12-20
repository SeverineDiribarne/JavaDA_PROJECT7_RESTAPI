package com.nnk.springboot.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.domain.User;

public class MyMainUser implements UserDetails {
   
	private static final long serialVersionUID = 1L;
	private transient User user;

    public MyMainUser(User user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Map<String,Object> attributes()
	{
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("name", user.getUsername());
		
		return attributes;
	}
}