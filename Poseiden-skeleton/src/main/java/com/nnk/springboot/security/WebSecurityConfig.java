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
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

//	 @Autowired
//	    private UserDetailsService personDetailsService;
	
	 @Autowired
	 private DataSource dataSource;
		
		/**
		 * configure HTTP security
		 * @param http
		 */
		@Override
		protected void configure (HttpSecurity http) throws Exception {
			http

			.authorizeRequests()
			.antMatchers("/bidList", "/curvePoint", "/rating", "/ruleName", "/trade", "/user").authenticated()
			.and()
	.formLogin()
			.loginPage("/home").permitAll()
			.defaultSuccessUrl("/bidList/list",true)
			.and()
			.rememberMe()
			.and()
	.logout()
			.permitAll();
		}
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .jdbcAuthentication()
	                .dataSource(dataSource)
	                .passwordEncoder(passwordEncoder())
	                .usersByUsernameQuery(
	                        "select username,password,'true' as enabled from users where username=?")
	                .authoritiesByUsernameQuery(
	                        "select username,role from users where username=?");
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