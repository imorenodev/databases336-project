package org.moreno.wolak.project.repository.sells;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.OrderDto;
import org.moreno.wolak.project.dtos.SellsRequestDto;
import org.moreno.wolak.project.dtos.SellsResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.items.ItemsRepository;
import org.moreno.wolak.project.repository.orders.IOrdersDao;

public class SellsRepository implements ISellsDao {

	private static SellsRepository sellsRepositorySingletonInstance = null;


	public static SellsRepository getSingletonInstance() {
		if (sellsRepositorySingletonInstance == null) {
			sellsRepositorySingletonInstance = new SellsRepository();
		}
		return sellsRepositorySingletonInstance;
	}
	
	private ItemsRepository getItemsRepository() {
		return ItemsRepository.getSingletonInstance();
	}

	
	/** START: BAR SELLS ITEMS **/
	@Override
	public List<SellsResponseDto> getAllItemsSoldByBar(int barId) {
		List<SellsResponseDto> sells = new ArrayList<>();
		String queryString = "SELECT * FROM sells WHERE bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				SellsResponseDto sell = new SellsResponseDto();
				sell.setBarId(rs.getInt(1));

				// get itemId
				int itemId = rs.getInt(2);
				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				// set item object
				sell.setItem(item);

				sell.setPrice(rs.getDouble(3));
				
				sells.add(sell);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItemsSoldByBar");
			e.printStackTrace();
		}
		
		return sells;
	}

	@Override
	public List<SellsResponseDto> getAllItemsOfTypeSoldByBar(int barId, ItemType type) {
		List<SellsResponseDto> sells = new ArrayList<>();
		String queryString = "SELECT * FROM sells WHERE bar_id=? AND item_id IN (SELECT item_id FROM items WHERE type=?)";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			queryStatement.setString(2, type.toString());
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				SellsResponseDto sell = new SellsResponseDto();
				sell.setBarId(rs.getInt(1));

				// get itemId
				int itemId = rs.getInt(2);
				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				System.out.println("item id " + itemId);
				// set item object
				sell.setItem(item);

				sell.setPrice(rs.getDouble(3));
				
				sells.add(sell);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItemsOfTypeSoldByBar");
			e.printStackTrace();
		}
		
		return sells;
	}

	@Override
	public SellsResponseDto getItemSoldByBar(int barId, int itemId) {
		SellsResponseDto sell = new SellsResponseDto();
		String queryString = "SELECT * FROM sells WHERE bar_id=? AND item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, barId);
			queryStatement.setInt(2, itemId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				sell.setBarId(rs.getInt(1));

				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				// set item object
				sell.setItem(item);

				sell.setPrice(rs.getDouble(3));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getItemSoldByBar");
			e.printStackTrace();
		} 
		
		return sell;
	}

	@Override
	public SellsResponseDto createItemSoldByBar(int barId, SellsRequestDto sells) {
		String insertString = "INSERT INTO sells (bar_id, item_id, price) VALUES(?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, barId);
			insertStatement.setInt(2, sells.getItemId());
			insertStatement.setDouble(3, sells.getPrice());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBarSellsItem");
			e.printStackTrace();
		} 
		
		// upon successful creation of sells relation, return new sells record
		return getItemSoldByBar(barId, sells.getItemId());
	}

	@Override
	public SellsResponseDto deleteItemSoldByBar(int barId, int itemId) {
		SellsResponseDto sells = getItemSoldByBar(barId, itemId);
		String deleteString = "DELETE FROM sells WHERE bar_id=? AND item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, barId);
			deleteStatement.setInt(2, itemId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteItemById");
			e.printStackTrace();
		} 

		// return the sells record that was deleted
		return sells;
	}

}
