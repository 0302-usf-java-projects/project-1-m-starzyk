package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.User;
import com.revature.service.AccountService;

public class Controller {
	
	//final static Logger logger = Logger.getLogger(Controller.class);
	
	public static String home() {
		return "html/index.html";
	}
	
	public static String login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String username = req.getParameter("userSub");
		String password = req.getParameter("passSub");
		//TODO: add authentication method
		AccountService as = new AccountService();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		boolean logIt = as.authenticate(username, password);
		System.out.println(logIt);
		if(logIt) {
			int role = as.employeeType(username);
			System.out.println(role);
			if(role == 2) {
				return "html/manager.html";
			} else {
				return "html/employee.html";
			}
			
		} else {
			//PrintWriter out = res.getWriter()
			
			out.println("<p>loginfail</p>");

			RequestDispatcher rd = req.getRequestDispatcher("html/failedlogin.html");
			rd.include(req, res);
			out.close();
			return "html/failedlogin.html";
			
		}
	}
	
	
}
