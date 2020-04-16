package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AccountService;

public class Controller {
	static String username = null;
	//final static Logger logger = Logger.getLogger(Controller.class);
	
	public static String home() {
		return "html/index.html";
	}
	
	public static String login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		username = req.getParameter("userSub");
		String password = req.getParameter("passSub");
		AccountService as = new AccountService();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		boolean logIt = as.authenticate(username, password);
		if(logIt) {
			int role = as.employeeType(username);
			
			if(role == 2) {
				return "html/manager.html";
			} else {
				return "html/employee.html";
			}
		} else {
			//PrintWriter out = res.getWriter()
			out.println("<p>loginfail</p>");
			RequestDispatcher rd = req.getRequestDispatcher("html/index.html");
			rd.include(req, res);
			out.close();
			return "html/index.html";
		}
	}
	public static String formSubmit(HttpServletRequest req, HttpServletResponse res) {
		String money = req.getParameter("money");
		String description = req.getParameter("desc");
		String rType = req.getParameter("rType");
		AccountService as = new AccountService();
		String auther = as.getAuthorNumber(username);
		
		int amount = Integer.parseInt(money);
		int type = Integer.parseInt(rType);
		int author = Integer.parseInt(auther);
		System.out.println(amount + type + author + description);
		//Reimbursement reimb = new Reimbursement();
		as.newReimb(amount,description,type,author);
		
		return null;
	}
	
	
}
