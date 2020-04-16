package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Project/index.master":
			Controller.logout();
			return Controller.home();
		case "/Project1/signin.master":
			return Controller.login(req, res);
		case "/Project1/reimb.master":
			Controller.formSubmit(req, res);
			return "html/employee.html";
		default:
			return "";
		}
	}
	
	public static void directProcess(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Project1/viewer.json":
			new Controller().viewMyReimb(req, res);
			break;
		case "/Project1/view-all.json":
			new Controller().viewAllReimb(req, res);
			break;
		case "/Project1/view-all-sorted.json":
			new Controller().viewAllReimbSorted(req, res);
			break;
		}
	}

}
