package org.moreno.wolak.project.repository.items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.SoftDrinkDto;
import org.moreno.wolak.project.repository.ConnectionFactory;


public class ItemsRepository implements IItemsDao {

	private static ItemsRepository repositorySingletonInstance = null;
	

	public static ItemsRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new ItemsRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	/*********************************************************************************
	* 								CRUD ON ITEMS								   *
	*********************************************************************************/


	@Override
	public List<ItemDto> getAllItems() {
		List<ItemDto> items = new ArrayList<>();
		String queryString = "SELECT * FROM items";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				ItemDto item = new ItemDto();
				item.setItemId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setManufacturer(rs.getString(3));
				item.setCalories(rs.getInt(4));
				item.setType(ItemType.valueOf(rs.getString(5)));
				
				items.add(item);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItems");
			e.printStackTrace();
		}
		
		return items;
	}


	@Override
	public ItemDto getItemById(int itemId) {
		ItemDto item = new ItemDto();
		String queryString = "SELECT * FROM items WHERE item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, itemId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				item.setItemId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setManufacturer(rs.getString(3));
				item.setCalories(rs.getInt(4));
				item.setType(ItemType.valueOf(rs.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getItemById");
			e.printStackTrace();
		} 
		
		return item;
	}

	@Override
	public ItemDto createItem(ItemDto item) {
		String insertString = "INSERT INTO items (name, manufacturer, calories, type) VALUES(?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setString(1, item.getName());
			insertStatement.setString(2, item.getManufacturer());
			insertStatement.setInt(3, item.getCalories());
			insertStatement.setString(4, item.getType().toString());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createItem");
			e.printStackTrace();
		} 
		
		return item;
	}

	@Override
	public int deleteItemById(int itemId) {
		String deleteString = "DELETE FROM items WHERE item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, itemId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteItemById");
			e.printStackTrace();
		} 

		return itemId;
	}
	
	@Override
	public ItemDto updateItemById(int itemId, ItemDto item) {
		String updateString = "UPDATE items SET name=?, manufacturer=?, calories=? WHERE item_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, item.getName());
			updateStatement.setString(2, item.getManufacturer());
			updateStatement.setInt(3, item.getCalories());
			updateStatement.setInt(4, itemId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateItemById");
			e.printStackTrace();
		} 
		
		return item;
	}
	

	/*********************************************************************************
	* 								CRUD ON BEERS								   *
	*********************************************************************************/

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
	
	/*********************************************************************************
	* 								CRUD ON FOODS							   	   *
	*********************************************************************************/

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

	@Override
	public int deleteFoodById(int foodId) {
		String deleteString = "DELETE FROM items WHERE item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, foodId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteFoodById");
			e.printStackTrace();
		} 

		return foodId;
	}
	
	@Override
	public FoodDto updateFoodById(int foodId, FoodDto food) {
		String updateString = "UPDATE items SET name=?, manufacturer=?, calories=? WHERE item_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, food.getName());
			updateStatement.setString(2, food.getManufacturer());
			updateStatement.setInt(3, food.getCalories());
			updateStatement.setInt(4, foodId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateFoodById");
			e.printStackTrace();
		} 
		
		return food;
	}


	/*********************************************************************************
	* 								CRUD ON SOFTDRINKS						   	   *
	*********************************************************************************/


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

	@Override
	public int deleteSoftDrinkById(int softDrinkId) {
		String deleteString = "DELETE FROM items WHERE item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, softDrinkId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteSoftDrinkById");
			e.printStackTrace();
		} 

		return softDrinkId;
	}

	@Override
	public SoftDrinkDto updateSoftDrinkById(int softDrinkId, SoftDrinkDto softDrink) {
		String updateString = "UPDATE items SET name=?, manufacturer=?, calories=? WHERE item_id=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, softDrink.getName());
			updateStatement.setString(2, softDrink.getManufacturer());
			updateStatement.setInt(3, softDrink.getCalories());
			updateStatement.setInt(4, softDrinkId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateSoftDrinkById");
			e.printStackTrace();
		} 
		
		return softDrink;
	}
}
