package org.moreno.wolak.project.repository.drinkers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.DrinkerDto;
import org.moreno.wolak.project.repository.ConnectionFactory;

public class DrinkersRepository implements IDrinkerDao {

	private static DrinkersRepository repositorySingletonInstance = null;
	

	public static DrinkersRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new DrinkersRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<DrinkerDto> getAllDrinkers() {
		List<DrinkerDto> drinkers = new ArrayList<>();
		String queryString = "SELECT * FROM drinkers";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				DrinkerDto drinker = new DrinkerDto();
				drinker.setDrinkerId(Integer.parseInt(rs.getString(1)));
				drinker.setName(rs.getString(2));
				drinker.setPhone(rs.getString(3));
				drinker.setCity(rs.getString(4));
				drinker.setAddress(rs.getString(5));
				drinker.setState(rs.getString(6));
				
				drinkers.add(drinker);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllDrinkers");
			e.printStackTrace();
		}
		
		return drinkers;
	}


	@Override
	public DrinkerDto getDrinkerById(int drinkerId) {
		DrinkerDto drinker = new DrinkerDto();
		String queryString = "SELECT * FROM drinkers WHERE drinker_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, drinkerId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				drinker.setDrinkerId(Integer.parseInt(rs.getString(1)));
				drinker.setName(rs.getString(2));
				drinker.setPhone(rs.getString(3));
				drinker.setCity(rs.getString(4));
				drinker.setAddress(rs.getString(5));
				drinker.setState(rs.getString(6));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getDrinkerById");
			e.printStackTrace();
		} 
		
		return drinker;
	}

	public DrinkerDto createDrinker(DrinkerDto drinker) {
		String insertString = "INSERT INTO drinkers (name, phone, city, address, state) VALUES(?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, drinker.getName());
			insertStatement.setString(2, drinker.getPhone());
			insertStatement.setString(3, drinker.getCity());
			insertStatement.setString(4, drinker.getAddress());
			insertStatement.setString(5, drinker.getState());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createDrinker");
			e.printStackTrace();
		} 
		
		return drinker;
	}
	
	public int deleteDrinkerById(int drinkerId) {
		String deleteString = "DELETE FROM drinkers WHERE drinker_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, drinkerId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteDrinkerById");
			e.printStackTrace();
		} 

		return drinkerId;
	}
	
	public DrinkerDto updateDrinkerById(int drinkerId, DrinkerDto drinker) {
		String updateString = "UPDATE drinkers SET name=?, phone=?, city=?, address=?, state=? WHERE drinker_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, drinker.getName());
			updateStatement.setString(2, drinker.getPhone());
			updateStatement.setString(3, drinker.getCity());
			updateStatement.setString(4, drinker.getAddress());
			updateStatement.setString(5, drinker.getState());
			updateStatement.setInt(6, drinkerId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateDrinkerById");
			e.printStackTrace();
		} 
		
		return drinker;
	}

}
