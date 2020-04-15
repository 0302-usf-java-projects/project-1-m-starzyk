package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;

public class AccountDao implements DaoContract<Reimbursement>{
	
	@Override
	public List<Reimbursement> findTicketsByUsername(int i) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> findAllTickets() {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void rejectTicket(int res, int id) {
		try(Connection conn = ConnectionUtil.connect()){
			Statement s = conn.createStatement();
			String sql = "update ers_reimbursement set reimb_status_id = 3, reimb_resolved = current_timestamp, reimb_resolver = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, res);
			ps.setInt(2,id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void acceptTicket(int res, int id) {
		try(Connection conn = ConnectionUtil.connect()){
			Statement s = conn.createStatement();
			String sql = "update ers_reimbursement set reimb_status_id = 2, reimb_resolved = current_timestamp, reimb_resolver = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, res);
			ps.setInt(2,id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
