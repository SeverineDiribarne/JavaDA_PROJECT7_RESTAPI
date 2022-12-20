package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


	
	@Autowired
    private MyUserDetailsService userDetailsService;
	/**
	 * configure HTTP security
	 * @param http
	 */
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.csrf()
		.and()
		.authorizeRequests()
		.antMatchers("/home", "/login").permitAll()
		.antMatchers("/bidList", "/curvePoint", "/rating", "/ruleName", "/trade", "/user").authenticated()

		.and()
		
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/bidList/list")
		
		.and()
		
		.logout()
		.logoutUrl("/app-logout")
		.logoutSuccessUrl("/home")
		.permitAll()
		.invalidateHttpSession(true)

		.and()
		.oauth2Login()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/bidList/list");
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	/**
	 * setPassWordEncoder Method
	 * @return instance of password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}