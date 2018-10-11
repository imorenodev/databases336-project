package org.moreno.wolak.project.repository.pays;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.dtos.PaysRequestDto;
import org.moreno.wolak.project.dtos.PaysResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.bills.BillsRepository;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;
import org.moreno.wolak.project.repository.issues.IIssuesDao;
import org.moreno.wolak.project.repository.items.ItemsRepository;

public class PaysRepository implements IPaysDao {

	private static PaysRepository paysRepositorySingletonInstance = null;


	public static PaysRepository getSingletonInstance() {
		if (paysRepositorySingletonInstance == null) {
			paysRepositorySingletonInstance = new PaysRepository();
		}
		return paysRepositorySingletonInstance;
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
	public List<PaysResponseDto> getAllPaymentsByDrinkers() {
		List<PaysResponseDto> paysList = new ArrayList<>();
		
		String queryString = "SELECT * FROM pays";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				PaysResponseDto pays = new PaysResponseDto();

				pays.setBillId(rs.getInt(1));
				pays.setDrinkerId(rs.getInt(2));
				pays.setPaymentDate(rs.getString(3));
				pays.setTotalPaid(rs.getDouble(4));
				
				paysList.add(pays);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllPaymentsByDrinkers");
			e.printStackTrace();
		}

		return paysList;
	}

	@Override
	public List<PaysResponseDto> getAllPaymentsByDrinkerById(int drinkerId) {
		List<PaysResponseDto> paymentsList = new ArrayList<>();
		
		String queryString = "SELECT * FROM pays WHERE drinker_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				PaysResponseDto pays = new PaysResponseDto();

				pays.setBillId(rs.getInt(1));
				pays.setDrinkerId(rs.getInt(2));
				pays.setPaymentDate(rs.getString(3));
				pays.setTotalPaid(rs.getDouble(4));
				
				paymentsList.add(pays);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllPaymentsByDrinkerById");
			e.printStackTrace();
		}

		return paymentsList;
	}
	
	@Override
	public PaysResponseDto createPaymentByDrinker(int drinkerId, PaysRequestDto pays) {
		String insertString = "INSERT INTO pays (bill_id, drinker_id, paid_date, total) VALUES(?, ?, now(), ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, pays.getBillId());
			insertStatement.setInt(2, drinkerId);
			insertStatement.setDouble(4, pays.getTotalPaid());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createPaymentByDrinker");
			e.printStackTrace();
		} 
		
		// upon successful creation of pays relation, return new pays record
		return getPaymentByDrinkerById(drinkerId, pays.getBillId());
	}

	@Override
	public PaysResponseDto getPaymentByDrinkerById(int drinkerId, int billId) {
		PaysResponseDto pays = new PaysResponseDto();
		
		String queryString = "SELECT * FROM pays WHERE drinker_id=? AND bill_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			queryStatement.setInt(1, billId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				pays.setBillId(rs.getInt(1));
				pays.setDrinkerId(rs.getInt(2));
				pays.setPaymentDate(rs.getString(3));
				pays.setTotalPaid(rs.getDouble(4));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllPaymentsByDrinkerById");
			e.printStackTrace();
		}

		return pays;
	}
	
	@Override
	public PaysResponseDto updatePaymentByDrinkerById(int drinkerId, int billId, PaysRequestDto pays) {
		String updateString = "UPDATE pays SET paid_date=now(), total=? WHERE bill_id=? AND drinker_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setDouble(1, pays.getTotalPaid());
			updateStatement.setInt(2, billId);
			updateStatement.setInt(3, drinkerId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updatePaymentByDrinkerById");
			e.printStackTrace();
		} 
		
		return getPaymentByDrinkerById(drinkerId, billId);
	}

	@Override
	public PaysResponseDto deletePaymentByDrinkerById(int drinkerId, int billId) {
		PaysResponseDto pays = getPaymentByDrinkerById(drinkerId, billId);
		String deleteString = "DELETE FROM pays WHERE bill_id=? AND drinker_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, billId);
			deleteStatement.setInt(2, drinkerId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deletePaymentByDrinkerById");
			e.printStackTrace();
		} 

		return pays;
	}

}
