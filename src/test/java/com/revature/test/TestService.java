package com.revature.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Reimbursement;
import com.revature.repository.AccountDao;

public class TestService {
	
	
	private static AccountDao ad;
	
	@Before 
	public void setUp() {
		ad = new AccountDao();
		System.out.println("start");
	}
	
	@After
	public void tearDown() {
		ad = null;
		System.out.println("end");
	}
	
	@Test
	public void getAllUsersFromSQL() {
		List<Reimbursement> list = ad.findAllTickets();
		System.out.println(list);
	}
	
}
