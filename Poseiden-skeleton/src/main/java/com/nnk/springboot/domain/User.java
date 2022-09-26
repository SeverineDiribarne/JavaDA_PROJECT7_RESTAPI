package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = "users")
public class User {

	//Attributes

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Id")
	private Integer id;

	@NotBlank(message = "Username is mandatory")
	@Column(name="username")
	private String username;

	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,}$",
            message = "Password must contains minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character")
	@NotBlank(message = "Password is mandatory")
	@Column(name="password")
	private String password;

	@NotBlank(message = "FullName is mandatory")
	@Column(name="fullname")
	private String fullname;

	@NotBlank(message = "Role is mandatory")
	@Column(name="role")
	private String role;


	/**
	 * Complete constructor
	 * @param id
	 * @param username
	 * @param password
	 * @param fullname
	 * @param role
	 */
	public User(Integer id, @NotBlank(message = "Username is mandatory") String username,
			@NotBlank(message = "Password is mandatory") String password,
			@NotBlank(message = "FullName is mandatory") String fullname,
			@NotBlank(message = "Role is mandatory") String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	/**
	 * Empty constructor
	 */
	public User() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", role=" + role + "]";
	}
}