package org.moreno.wolak.project.repository.bills;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BillDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.bills.IBillsDao;

public class BillsRepository implements IBillsDao {

	private static BillsRepository repositorySingletonInstance = null;
	

	public static BillsRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new BillsRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<BillDto> getAllBills() {
		List<BillDto> bills = new ArrayList<>();
		String queryString = "SELECT * FROM bills";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				BillDto bill = new BillDto();
				bill.setBillId(rs.getInt(1));
				bill.setDate(rs.getString(2));
				bill.setTip(rs.getDouble(3));
				bill.setTax(rs.getDouble(4));
				bill.setTotal(rs.getDouble(5));
				
				bills.add(bill);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBills");
			e.printStackTrace();
		}
		
		return bills;
	}


	@Override
	public BillDto getBillById(int billId) {
		BillDto bill = new BillDto();
		String queryString = "SELECT * FROM bills WHERE bill_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, billId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				bill.setBillId(rs.getInt(1));
				bill.setDate(rs.getString(2));
				bill.setTip(rs.getDouble(3));
				bill.setTax(rs.getDouble(4));
				bill.setTotal(rs.getDouble(5));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBillById");
			e.printStackTrace();
		} 
		
		return bill;
	}

	public BillDto createBill(BillDto bill) {
		String insertString = "INSERT INTO bills (date, tip, tax, total) VALUES(now(), ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setDouble(1, bill.getTip());
			insertStatement.setDouble(2, bill.getTax());
			insertStatement.setDouble(3, bill.getTotal());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBill");
			e.printStackTrace();
		} 
		
		return bill;
	}

	public int deleteBillById(int billId) {
		String deleteString = "DELETE FROM bills WHERE bill_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, billId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteBillById");
			e.printStackTrace();
		} 

		return billId;
	}
	
	public BillDto updateBillById(int billId, BillDto bill) {
		String updateString = "UPDATE bills SET tip=?, tax=?, total=? WHERE bill_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setDouble(1, bill.getTip());
			updateStatement.setDouble(2, bill.getTax());
			updateStatement.setDouble(3, bill.getTotal());
			updateStatement.setInt(4, billId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateBillById");
			e.printStackTrace();
		} 
		
		return bill;
	}

}
