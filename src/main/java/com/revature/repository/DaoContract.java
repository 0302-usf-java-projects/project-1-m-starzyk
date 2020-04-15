package com.revature.repository;

import java.util.List;

public interface DaoContract <T>{
	
	List<T> findTicketsByUsername(int i);
	List<T> findAllTickets();
	void rejectTicket(int res, int id);
	void acceptTicket(int res, int id);
	int findEmployeeRank(String username);
	boolean authenticateHashPass(String username, String password);
	

}
