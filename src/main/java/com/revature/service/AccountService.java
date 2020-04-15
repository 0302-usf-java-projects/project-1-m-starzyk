package com.revature.service;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.repository.AccountDao;

public class AccountService {
	
	private AccountDao adao;
	{
		adao = new AccountDao();
	}
	
	public List<Reimbursement> getAllAccounts(){
		return adao.findAllTickets();
	}
	
//	public String getPassword(String username) {
//		return adao.findPasswordFromUsername(username);
//	}
	
	public int employeeType(String username) {
		return adao.findEmployeeRank(username);
	}
	
	public boolean authenticate(String username, String password) {
		return adao.authenticateHashPass(username, password);
	}
	
}
