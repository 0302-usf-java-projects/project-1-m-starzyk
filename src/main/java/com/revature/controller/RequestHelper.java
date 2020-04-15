package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO: edit this for the project 1 urls
public class RequestHelper {
	
	public static String process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/project-1-m-starzyk/index.master":
			return Controller.home();
		case "/project-1-m-starzyk/signin.json":
			return Controller.login(req);
		case "/FrontControllerProject/gonext.master":
			return "html/second.html";
		default:
			return "";
		}
	}
	
//	public static void directProcess(HttpServletRequest req, HttpServletResponse res) {
//		switch(req.getRequestURI()) {
//		case "/FrontControllerProject/clown.json":
//			new ClownController().getById(req, res);
//		}
//	}

}
