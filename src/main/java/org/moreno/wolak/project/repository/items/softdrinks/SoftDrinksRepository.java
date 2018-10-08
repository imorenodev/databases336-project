package org.moreno.wolak.project.repository.items.softdrinks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.SoftDrinkDto;
import org.moreno.wolak.project.repository.ConnectionFactory;

public class SoftDrinksRepository implements ISoftDrinksDao {

	private static SoftDrinksRepository repositorySingletonInstance = null;
	

	public static SoftDrinksRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new SoftDrinksRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<SoftDrinkDto> getAllSoftDrinks() {
		List<SoftDrinkDto> softDrinks = new ArrayList<>();
		String queryString = "SELECT * FROM items WHERE type='soft_drink'";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				SoftDrinkDto softDrink = new SoftDrinkDto();
				softDrink.setItemId(rs.getInt(1));
				softDrink.setName(rs.getString(2));
				softDrink.setManufacturer(rs.getString(3));
				softDrink.setCalories(rs.getInt(4));
				softDrink.setType(ItemType.valueOf(rs.getString(5)));
				
				softDrinks.add(softDrink);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllSoftDrinks");
			e.printStackTrace();
		}
		
		return softDrinks;
	}


	@Override
	public SoftDrinkDto getSoftDrinkById(int softDrinkId) {
		SoftDrinkDto softDrink = new SoftDrinkDto();
		String queryString = "SELECT * FROM items WHERE type='soft_drink' AND item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, softDrinkId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				softDrink.setItemId(rs.getInt(1));
				softDrink.setName(rs.getString(2));
				softDrink.setManufacturer(rs.getString(3));
				softDrink.setCalories(rs.getInt(4));
				softDrink.setType(ItemType.valueOf(rs.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getSoftDrinkById");
			e.printStackTrace();
		} 
		
		return softDrink;
	}

	@Override
	public SoftDrinkDto createSoftDrink(SoftDrinkDto softDrink) {
		String insertString = "INSERT INTO items (name, manufacturer, calories, type) VALUES(?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, softDrink.getName());
			insertStatement.setString(2, softDrink.getManufacturer());
			insertStatement.setInt(3, softDrink.getCalories());
			insertStatement.setString(4, ItemDto.ItemType.soft_drink.toString());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createSoftDrink");
			e.printStackTrace();
		} 
		
		return softDrink;
	}

}
