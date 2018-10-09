package org.moreno.wolak.project.repository.bars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BarDto;
import org.moreno.wolak.project.repository.ConnectionFactory;

public class BarsRepository implements IBarsDao {

	private static BarsRepository repositorySingletonInstance = null;
	

	public static BarsRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new BarsRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<BarDto> getAllBars() {
		List<BarDto> bars = new ArrayList<>();
		String queryString = "SELECT * FROM bars";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				BarDto bar = new BarDto();
				bar.setBarId(rs.getInt(1));
				bar.setName(rs.getString(2));
				bar.setLicense(rs.getString(3));
				bar.setCity(rs.getString(4));
				bar.setState(rs.getString(5));
				bar.setAddress(rs.getString(6));
				bar.setPhone(rs.getString(7));
				
				bars.add(bar);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBars");
			e.printStackTrace();
		}
		
		return bars;
	}


	@Override
	public BarDto getBarById(int barId) {
		BarDto bar = new BarDto();
		String queryString = "SELECT * FROM bars WHERE bar_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				bar.setBarId(Integer.parseInt(rs.getString(1)));
				bar.setName(rs.getString(2));
				bar.setLicense(rs.getString(3));
				bar.setCity(rs.getString(4));
				bar.setState(rs.getString(5));
				bar.setAddress(rs.getString(6));
				bar.setPhone(rs.getString(7));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBarById");
			e.printStackTrace();
		} 
		
		return bar;
	}

	public BarDto createBar(BarDto bar) {
		String insertString = "INSERT INTO bars (name, license, city, state, address, phone) VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, bar.getName());
			insertStatement.setString(2, bar.getLicense());
			insertStatement.setString(3, bar.getCity());
			insertStatement.setString(4, bar.getState());
			insertStatement.setString(5, bar.getAddress());
			insertStatement.setString(6, bar.getPhone());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBar");
			e.printStackTrace();
		} 
		
		return bar;
	}

	public int deleteBarById(int barId) {
		String deleteString = "DELETE FROM bars WHERE bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, barId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteBarById");
			e.printStackTrace();
		} 

		return barId;
	}
	
	public BarDto updateBarById(int barId, BarDto bar) {
		String updateString = "UPDATE bars SET name=?, license=?, city=?, state=?, address=?, phone=? WHERE bar_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, bar.getName());
			updateStatement.setString(2, bar.getLicense());
			updateStatement.setString(3, bar.getCity());
			updateStatement.setString(4, bar.getState());
			updateStatement.setString(5, bar.getAddress());
			updateStatement.setString(6, bar.getPhone());
			updateStatement.setInt(7, barId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateBarById");
			e.printStackTrace();
		} 
		
		return bar;
	}

}
