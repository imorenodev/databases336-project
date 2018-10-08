package org.moreno.wolak.project.repository.items.foods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.repository.ConnectionFactory;

public class FoodsRepository implements IFoodsDao {

	private static FoodsRepository repositorySingletonInstance = null;
	

	public static FoodsRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new FoodsRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<FoodDto> getAllFoods() {
		List<FoodDto> foods = new ArrayList<>();
		String queryString = "SELECT * FROM items WHERE type='food'";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				FoodDto food = new FoodDto();
				food.setItemId(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setManufacturer(rs.getString(3));
				food.setCalories(rs.getInt(4));
				food.setType(ItemType.valueOf(rs.getString(5)));
				
				foods.add(food);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllFoods");
			e.printStackTrace();
		}
		
		return foods;
	}


	@Override
	public FoodDto getFoodById(int foodId) {
		FoodDto food = new FoodDto();
		String queryString = "SELECT * FROM items WHERE type='food' AND item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, foodId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				food.setItemId(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setManufacturer(rs.getString(3));
				food.setCalories(rs.getInt(4));
				food.setType(ItemType.valueOf(rs.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getFoodById");
			e.printStackTrace();
		} 
		
		return food;
	}

	@Override
	public FoodDto createFood(FoodDto food) {
		String insertString = "INSERT INTO items (name, manufacturer, calories, type) VALUES(?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, food.getName());
			insertStatement.setString(2, food.getManufacturer());
			insertStatement.setInt(3, food.getCalories());
			insertStatement.setString(4, ItemDto.ItemType.food.toString());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createFood");
			e.printStackTrace();
		} 
		
		return food;
	}


}
