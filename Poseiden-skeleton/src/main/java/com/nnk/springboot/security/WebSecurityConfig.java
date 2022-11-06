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
			.antMatchers("/home", "/login").permitAll()
			.antMatchers("/bidList", "/curvePoint", "/rating", "/ruleName", "/trade", "/user").authenticated()
			
			.and()
			.formLogin()
			.loginPage("/login.html")
		//	.permitAll()
		//	.defaultSuccessUrl("/bidList/list",true)
			.failureUrl("/login?error=true")
			
			.and()
			.logout()
			.logoutUrl("/app-logout")
			.logoutSuccessUrl("/")
			.permitAll()
			.invalidateHttpSession(true)
			
			.and()
			.oauth2Login(); //.defaultSuccessUrl("/bidList/list")
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