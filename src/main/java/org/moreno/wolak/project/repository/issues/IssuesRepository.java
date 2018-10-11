package org.moreno.wolak.project.repository.issues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.bills.BillsRepository;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;
import org.moreno.wolak.project.repository.items.ItemsRepository;

public class IssuesRepository implements IIssuesDao {

	private static IssuesRepository issuesRepositorySingletonInstance = null;


	public static IssuesRepository getSingletonInstance() {
		if (issuesRepositorySingletonInstance == null) {
			issuesRepositorySingletonInstance = new IssuesRepository();
		}
		return issuesRepositorySingletonInstance;
	}
	
	private ItemsRepository getItemsRepository() {
		return ItemsRepository.getSingletonInstance();
	}
	
	private DrinkersRepository getDrinkersRepository() {
		return DrinkersRepository.getSingletonInstance();
	}

	private BillsRepository getBillsRepository() {
		return BillsRepository.getSingletonInstance();
	}

	@Override
	public List<IssuesResponseDto> getAllBillsIssuedByBars() {
		List<IssuesResponseDto> issuesList = new ArrayList<>();
		
		String queryString = "SELECT * FROM issues";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				IssuesResponseDto issues = new IssuesResponseDto();

				issues.setBarId(rs.getInt(1));
				issues.setBill(getBillsRepository().getBillById(rs.getInt(2)));
				
				issuesList.add(issues);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBillsIssuedByBars");
			e.printStackTrace();
		}

		return issuesList;
	}

	@Override
	public List<IssuesResponseDto> getAllBillsIssuedByBarById(int barId) {
		List<IssuesResponseDto> issuesList = new ArrayList<>();
		
		String queryString = "SELECT * FROM issues WHERE bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				IssuesResponseDto issues = new IssuesResponseDto();

				issues.setBarId(rs.getInt(1));
				issues.setBill(getBillsRepository().getBillById(rs.getInt(2)));
				
				issuesList.add(issues);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBillsIssuedByBarById");
			e.printStackTrace();
		}

		return issuesList;
	}
	
	@Override
	public IssuesResponseDto createBillIssuedByBar(int barId, int billId) {
		String insertString = "INSERT INTO issues (bar_id, bill_id) VALUES(?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, barId);
			insertStatement.setInt(2, billId);

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBillIssuedByBar");
			e.printStackTrace();
		} 
		
		// upon successful creation of issues relation, return new issues record
		return getBillIssuedByBarById(barId, billId);
	}
	
	@Override
	public IssuesResponseDto getBillIssuedByBarById(int barId, int billId) {
		IssuesResponseDto issues = new IssuesResponseDto();
		String queryString = "SELECT * FROM issues WHERE bar_id=? AND bill_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			queryStatement.setInt(2, billId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				issues.setBarId(rs.getInt(1));
				issues.setBill(getBillsRepository().getBillById(rs.getInt(2)));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBillIssuedByBarById");
			e.printStackTrace();
		}

		return issues;
	}

	@Override
	public IssuesResponseDto deleteBillIssuedByBar(int barId, int billId) {
		IssuesResponseDto issues = getBillIssuedByBarById(barId, billId);
		String deleteString = "DELETE FROM issues WHERE bar_id=? AND bill_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, barId);
			deleteStatement.setInt(2, billId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteBillIssuedByBar");
			e.printStackTrace();
		} 

		return issues;
	}

}
