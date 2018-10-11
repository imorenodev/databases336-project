package org.moreno.wolak.project.repository.frequents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.FrequentsResponseDto;
import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.dtos.PaysRequestDto;
import org.moreno.wolak.project.dtos.PaysResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.bars.BarsRepository;
import org.moreno.wolak.project.repository.bills.BillsRepository;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;
import org.moreno.wolak.project.repository.issues.IIssuesDao;
import org.moreno.wolak.project.repository.items.ItemsRepository;
import org.moreno.wolak.project.repository.pays.IPaysDao;

public class FrequentsRepository implements IFrequentsDao {

	private static FrequentsRepository frequentsRepositorySingletonInstance = null;


	public static FrequentsRepository getSingletonInstance() {
		if (frequentsRepositorySingletonInstance == null) {
			frequentsRepositorySingletonInstance = new FrequentsRepository();
		}
		return frequentsRepositorySingletonInstance;
	}
	
	private ItemsRepository getItemsRepository() {
		return ItemsRepository.getSingletonInstance();
	}
	
	private DrinkersRepository getDrinkersRepository() {
		return DrinkersRepository.getSingletonInstance();
	}

	private BarsRepository getBarsRepository() {
		return BarsRepository.getSingletonInstance();
	}

	@Override
	public List<FrequentsResponseDto> getAllBarsFrequentedByDrinkers() {
		List<FrequentsResponseDto> frequentsList = new ArrayList<>();
		
		String queryString = "SELECT * FROM frequents";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				FrequentsResponseDto frequents = new FrequentsResponseDto();

				frequents.setBar(getBarsRepository().getBarById(rs.getInt(1)));
				frequents.setDrinkerId(rs.getInt(2));
				
				frequentsList.add(frequents);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBarsFrequentedByDrinkers");
			e.printStackTrace();
		}

		return frequentsList;
	}

	@Override
	public List<FrequentsResponseDto> getAllBarsFrequentedByDrinker(int drinkerId) {
		List<FrequentsResponseDto> frequentsList = new ArrayList<>();
		
		String queryString = "SELECT * FROM frequents WHERE drinker_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				FrequentsResponseDto frequents = new FrequentsResponseDto();

				frequents.setBar(getBarsRepository().getBarById(rs.getInt(1)));
				frequents.setDrinkerId(rs.getInt(2));
				
				frequentsList.add(frequents);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBarsFrequentedByDrinker");
			e.printStackTrace();
		}

		return frequentsList;
	}
	
	@Override
	public FrequentsResponseDto createDrinkerFrequentsBarById(int drinkerId, int barId) {
		String insertString = "INSERT INTO frequents (bar_id, drinker_id) VALUES(?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, barId);
			insertStatement.setInt(2, drinkerId);

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createDrinkerFrequentsBarById");
			e.printStackTrace();
		} 
		
		// upon successful creation of frequents relation, return new frequents record
		return getBarFrequentedByDrinker(drinkerId, barId);
	}

	@Override
	public FrequentsResponseDto getBarFrequentedByDrinker(int drinkerId, int barId) {
		FrequentsResponseDto frequents = new FrequentsResponseDto();
		
		String queryString = "SELECT * FROM frequents WHERE drinker_id=? AND bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				frequents.setBar(getBarsRepository().getBarById(rs.getInt(1)));
				frequents.setDrinkerId(rs.getInt(2));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBarFrequentedByDrinker");
			e.printStackTrace();
		}

		return frequents;
	}
	
	@Override
	public FrequentsResponseDto deleteDrinkerFrequentsBarById(int drinkerId, int barId) {
		FrequentsResponseDto frequents = getBarFrequentedByDrinker(drinkerId, barId);
		String deleteString = "DELETE FROM frequents WHERE drinker_id=? AND bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, drinkerId);
			deleteStatement.setInt(2, barId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteDrinkerFrequentsBarById");
			e.printStackTrace();
		} 

		return frequents;
	}

}
