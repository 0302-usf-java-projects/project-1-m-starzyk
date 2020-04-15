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
	
	@Test
	public void recieveUserRoleOfStarz() {
		int rank = ad.findEmployeeRank("mhstarz");
		System.out.println(rank);
	}
	
	@Test
	public void recieveUserRoleOftest2() {
		int rank = ad.findEmployeeRank("test2");
		System.out.println(rank);
	}
	
//	@Test
//	public void rejectTicketAsAdmin() {
//		ad.rejectTicket(1, 4);
//	}
	
	@Test
	public void authenticateUser() {
		boolean test = ad.authenticateHashPass("mhstar1z", "password");
		System.out.println(test);
	}
	
}
