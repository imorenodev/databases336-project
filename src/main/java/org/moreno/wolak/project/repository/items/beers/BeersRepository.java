package org.moreno.wolak.project.repository.items.beers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BarDto;
import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.repository.ConnectionFactory;

public class BeersRepository implements IBeersDao {

	private static BeersRepository repositorySingletonInstance = null;
	

	public static BeersRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new BeersRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<BeerDto> getAllBeers() {
		List<BeerDto> beers = new ArrayList<>();
		String queryString = "SELECT * FROM items WHERE type='beer'";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				BeerDto beer = new BeerDto();
				beer.setItemId(rs.getInt(1));
				beer.setName(rs.getString(2));
				beer.setManufacturer(rs.getString(3));
				beer.setCalories(rs.getInt(4));
				beer.setType(ItemType.valueOf(rs.getString(5)));
				
				beers.add(beer);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBeers");
			e.printStackTrace();
		}
		
		return beers;
	}


	@Override
	public BeerDto getBeerById(int beerId) {
		BeerDto beer = new BeerDto();
		String queryString = "SELECT * FROM items WHERE type='beer' AND item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, beerId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				beer.setItemId(rs.getInt(1));
				beer.setName(rs.getString(2));
				beer.setManufacturer(rs.getString(3));
				beer.setCalories(rs.getInt(4));
				beer.setType(ItemType.valueOf(rs.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBeerById");
			e.printStackTrace();
		} 
		
		return beer;
	}

	@Override
	public BeerDto createBeer(BeerDto beer) {
		String insertString = "INSERT INTO items (name, manufacturer, calories, type) VALUES(?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, beer.getName());
			insertStatement.setString(2, beer.getManufacturer());
			insertStatement.setInt(3, beer.getCalories());
			insertStatement.setString(4, ItemDto.ItemType.beer.toString());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBeer");
			e.printStackTrace();
		} 
		
		return beer;
	}

	@Override
	public int deleteBeerById(int beerId) {
		String deleteString = "DELETE FROM items WHERE item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, beerId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteBeerById");
			e.printStackTrace();
		} 

		return beerId;
	}
	
	@Override
	public BeerDto updateBeerById(int beerId, BeerDto beer) {
		String updateString = "UPDATE items SET name=?, manufacturer=?, calories=? WHERE item_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, beer.getName());
			updateStatement.setString(2, beer.getManufacturer());
			updateStatement.setInt(3, beer.getCalories());
			updateStatement.setInt(4, beerId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateBeerById");
			e.printStackTrace();
		} 
		
		return beer;
	}

}
