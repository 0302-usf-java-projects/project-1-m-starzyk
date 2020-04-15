package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.service.AccountService;

public class Controller {

	public static String home() {
		return "html/index.html";
	}
	
	public static String login(HttpServletRequest req) {
		String username = req.getParameter("userSub");
		String password = req.getParameter("passSub");
		//TODO: add authentication method
		User u = new User();
		boolean logIt = u.authenticate(username, password);
		if(logIt) {
			AccountService as = new AccountService();
			int role = as.employeeType(username);
			if(role == 2) {
				return "html/manager-page.html";
			} else {
				return "html/employee-page.html";
			}
			//TODO: make these two pages
			
			
		} else {
			return "html/failedLogin.html";
		}
	}
	
	
}
