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
		AccountService as = new AccountService();
		boolean logIt = as.authenticate(username, password);
		if(logIt) {
			int role = as.employeeType(username);
			if(role == 2) {
				return "html/manager.html";
			} else {
				return "html/employee.html";
			}
			
		} else {
			return "html/failedLogin.html";
		}
	}
	
	
}
