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
}
