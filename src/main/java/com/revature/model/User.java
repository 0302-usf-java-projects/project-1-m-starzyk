package com.revature.model;

import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordTooLongException;
import com.revature.exception.PasswordTooShortException;
import com.revature.service.AccountService;

public class User {
	
	private static final int REQUIRED_PASSWORD_LENGTH = 8;
	private static final int MAXIMUM_PASSWORD_LENGTH = 50;
	private static final int MAXIMUM_USERNAME_LENGTH = 50;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) throws DuplicateUsernameException {
		//TODO pull from data base
	    if(Account.usernames.contains(username)) {
	        throw new DuplicateUsernameException();
	      }
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws PasswordTooShortException, PasswordTooLongException {
		if(password.length() < REQUIRED_PASSWORD_LENGTH) {
			throw new PasswordTooShortException();
		} else if (password.length()>MAXIMUM_PASSWORD_LENGTH) {
			throw new PasswordTooLongException();
		}	
		this.password = password;
	}
	
	public boolean authenticate(String username, String password) {
		
		AccountService as = new AccountService();
		String userPass = as.getPassword(username);
		return userPass.equals(password);
		//return this.username.equals(username) && this.password.equals(password);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public User(String username, String password, String firstName, String lastName, String email, int roleId) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", roleId=" + roleId + "]";
	}
	
	
	
	
	
}
