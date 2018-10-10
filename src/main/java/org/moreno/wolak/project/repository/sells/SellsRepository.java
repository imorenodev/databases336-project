package org.moreno.wolak.project.repository.sells;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.BarDto;
import org.moreno.wolak.project.dtos.OrderDto;
import org.moreno.wolak.project.dtos.SellDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.orders.IOrdersDao;

public class SellsRepository implements IOrdersDao {

	private static SellsRepository repositorySingletonInstance = null;
	

	public static SellsRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new SellsRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderDto> orders = new ArrayList<>();
		String queryString = "SELECT * FROM orders";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				OrderDto order = new OrderDto();
				order.setOrderNumber(rs.getInt(1));
				order.setBillId(rs.getInt(2));
				order.setDrinkerId(rs.getInt(3));
				order.setItemId(rs.getInt(4));
				order.setQuantity(rs.getInt(5));
				order.setOrderDate(rs.getString(6));
				
				orders.add(order);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllOrders");
			e.printStackTrace();
		}
		
		return orders;
	}


	@Override
	public OrderDto getOrderById(int orderId) {
		OrderDto order = new OrderDto();
		String queryString = "SELECT * FROM orders WHERE order_number=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, orderId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				order.setOrderNumber(rs.getInt(1));
				order.setBillId(rs.getInt(2));
				order.setDrinkerId(rs.getInt(3));
				order.setItemId(rs.getInt(4));
				order.setQuantity(rs.getInt(5));
				order.setOrderDate(rs.getString(6));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getOrderById");
			e.printStackTrace();
		} 
		
		return order;
	}

	public OrderDto createOrder(OrderDto order) {
		String insertString = "INSERT INTO orders (bill_id, drinker_id, item_id, quantity, order_date) VALUES(?, ?, ?, ?, now())";

		try (Connection connection = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, order.getBillId());
			insertStatement.setInt(2, order.getDrinkerId());
			insertStatement.setInt(3, order.getItemId());
			insertStatement.setInt(4, order.getQuantity());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createOrder");
			e.printStackTrace();
		} 
		
		return order;
	}

	public int deleteOrderById(int orderId) {
		String deleteString = "DELETE FROM orders WHERE order_number=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, orderId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteOrderById");
			e.printStackTrace();
		} 

		return orderId;
	}
	
	public OrderDto updateOrderById(int orderId, OrderDto order) {
		String updateString = "UPDATE orders SET bill_id=?, drinker_id=?, item_id=?, quantity=? WHERE order_number=?";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setInt(1, order.getBillId());
			updateStatement.setInt(2, order.getDrinkerId());
			updateStatement.setInt(3, order.getItemId());
			updateStatement.setInt(4, order.getQuantity());
			updateStatement.setInt(5, orderId);

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateOrderById");
			e.printStackTrace();
		} 
		
		return order;
	}

	/** START BAR SELLS ITEMS **/

	public List<SellDto> getAllItemsSoldByBar(int barId) {
		List<SellDto> sells = new ArrayList<>();
		String queryString = "SELECT * FROM sells WHERE bar_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				SellDto sell = new SellDto();
				sell.setBarId(rs.getInt(1));
				sell.setItemId(rs.getInt(2));
				sell.setPrice(rs.getDouble(3));
				
				sells.add(sell);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItemsSoldByBar");
			e.printStackTrace();
		}
		
		return sells;
	}

	public SellDto getItemSoldByBar(int barId, int itemId) {
		SellDto sell = new SellDto();
		String queryString = "SELECT * FROM sells WHERE sell_number=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, sellId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				sell.setSellNumber(rs.getInt(1));
				sell.setBillId(rs.getInt(2));
				sell.setDrinkerId(rs.getInt(3));
				sell.setItemId(rs.getInt(4));
				sell.setQuantity(rs.getInt(5));
				sell.setSellDate(rs.getString(6));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getSellById");
			e.printStackTrace();
		} 
		
		return sell;
	}

}