package com.revature.controller;




public class Controller {

	public static String home() {
		return "html/index.html";
	}
	
//	public static String login(HttpServletRequest req) {
//		String username = req.getParameter("username");
//		Sting password = req.getParameter("password");
//		SomeServiceClass ss = new SomeServiceClass();
//		User u = ss.checkCreds(username, password);
//		if(u != null) {
//			return "html/homepage.html";
//		} else {
//			return "html/failedLogin.html";
//		}
//	}
}
