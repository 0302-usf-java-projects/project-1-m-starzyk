package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.service.AccountService;

public class AccountDao implements DaoContract<Reimbursement> {

	@Override
	public List<Reimbursement> findTicketsByUsername(int i) {
		try (Connection conn = ConnectionUtil.connect()) {
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reimbursement> findAllTickets() {
		try (Connection conn = ConnectionUtil.connect()) {
			String sql = "select * from ers_reimbursement";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			//ResultSet rs = ps.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void rejectTicket(int res, int id) {
		try (Connection conn = ConnectionUtil.connect()) {
			Statement s = conn.createStatement();
			String sql = "update ers_reimbursement set reimb_status_id = 3, reimb_resolved = current_timestamp, reimb_resolver = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, res);
			ps.setInt(2, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void acceptTicket(int res, int id) {
		try (Connection conn = ConnectionUtil.connect()) {
			Statement s = conn.createStatement();
			String sql = "update ers_reimbursement set reimb_status_id = 2, reimb_resolved = current_timestamp, reimb_resolver = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, res);
			ps.setInt(2, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int findEmployeeRank(String username) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from ers_users where ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int role = 0;
			while (rs.next()) {
				role = rs.getInt(7);			
			}
			return role;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

//	public String findPasswordFromUsername(String username) {
//		String pass = null;
//		try (Connection conn = ConnectionUtil.connect()) {
//			String sql = "select ers_password from ers_users where username = '?'";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, username);
//			ResultSet rs = ps.executeQuery();
//			pass = rs.getString(3);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return pass;
//	}

	public boolean authenticateHashPass(String username, String password) {
		String foundUser = null;
		try (Connection conn = ConnectionUtil.connect()) {
			String sql = "select ers_username from ers_users where ers_password = md5(?||?||'space_truckin')";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				foundUser = rs.getString(1);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username.equals(foundUser);
	}
	
	public String findAuthor(String username) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from ers_users where ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);			
			}
			String idAuthor = Integer.toString(id);
			return idAuthor;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void makeNewReimb(int amount, String description, int type, int author) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) " + 
					"values (?, current_timestamp, ?, ?, 1,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setInt(3, author);
			ps.setInt(4, type);
			ResultSet rs = ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
