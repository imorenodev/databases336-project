package org.moreno.wolak.project.repository.likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.DrinkerDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.LikesRequestDto;
import org.moreno.wolak.project.dtos.LikesResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;
import org.moreno.wolak.project.repository.items.ItemsRepository;

public class LikesRepository implements ILikesDao {

	private static LikesRepository likesRepositorySingletonInstance = null;


	public static LikesRepository getSingletonInstance() {
		if (likesRepositorySingletonInstance == null) {
			likesRepositorySingletonInstance = new LikesRepository();
		}
		return likesRepositorySingletonInstance;
	}
	
	private ItemsRepository getItemsRepository() {
		return ItemsRepository.getSingletonInstance();
	}
	
	private DrinkersRepository getDrinkersRepository() {
		return DrinkersRepository.getSingletonInstance();
	}


	public List<LikesResponseDto> getAllLikes() {
		List<LikesResponseDto> likesList = new ArrayList<>();
		
		String queryString = "SELECT * FROM likes";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				LikesResponseDto likes = new LikesResponseDto();

				likes.setDrinkerId(rs.getInt(1));
				likes.setItem(getItemsRepository().getItemById(rs.getInt(2)));
				
				likesList.add(likes);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllLikes");
			e.printStackTrace();
		}

		return likesList;
	}
	
	/** START: DRINKER LIKES ITEMS **/
	@Override
	public List<LikesResponseDto> getAllItemsLikedByDrinker(int drinkerId) {
		List<LikesResponseDto> likes = new ArrayList<>();
		String queryString = "SELECT * FROM likes WHERE drinker_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				LikesResponseDto sell = new LikesResponseDto();
				sell.setDrinkerId(rs.getInt(1));

				// get itemId
				int itemId = rs.getInt(2);
				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				// set item object
				sell.setItem(item);

				likes.add(sell);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItemsLikedByDrinker");
			e.printStackTrace();
		}
		
		return likes;
	}

	@Override
	public List<LikesResponseDto> getAllItemsOfTypeLikedByDrinker(int drinkerId, ItemType type) {
		List<LikesResponseDto> likes = new ArrayList<>();
		String queryString = "SELECT * FROM likes WHERE drinker_id=? AND item_id IN (SELECT item_id FROM items WHERE type=?)";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, drinkerId);
			queryStatement.setString(2, type.toString());
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				LikesResponseDto sell = new LikesResponseDto();
				sell.setDrinkerId(rs.getInt(1));

				// get itemId
				int itemId = rs.getInt(2);
				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				System.out.println("item id " + itemId);
				// set item object
				sell.setItem(item);
				
				likes.add(sell);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllItemsOfTypeLikedByDrinker");
			e.printStackTrace();
		}
		
		return likes;
	}

	@Override
	public LikesResponseDto getItemLikedByDrinker(int drinkerId, int itemId) {
		LikesResponseDto like = new LikesResponseDto();
		String queryString = "SELECT * FROM likes WHERE drinker_id=? AND item_id=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setInt(1, drinkerId);
			queryStatement.setInt(2, itemId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				like.setDrinkerId(rs.getInt(1));

				// get item object
				ItemDto item = getItemsRepository().getItemById(itemId);
				// set item object
				like.setItem(item);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getItemLikedByDrinker");
			e.printStackTrace();
		} 
		
		return like;
	}

	@Override
	public LikesResponseDto createItemLikedByDrinker(int drinkerId, LikesRequestDto likes) {
		String insertString = "INSERT INTO likes (drinker_id, item_id) VALUES(?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, drinkerId);
			insertStatement.setInt(2, likes.getItemId());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createDrinkerLikesItem");
			e.printStackTrace();
		} 
		
		// upon successful creation of likes relation, return new likes record
		return getItemLikedByDrinker(drinkerId, likes.getItemId());
	}
	
	@Override
	public LikesResponseDto deleteItemLikedByDrinker(int drinkerId, int itemId) {
		LikesResponseDto likes = getItemLikedByDrinker(drinkerId, itemId);
		String deleteString = "DELETE FROM likes WHERE drinker_id=? AND item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, drinkerId);
			deleteStatement.setInt(2, itemId);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteItemById");
			e.printStackTrace();
		} 

		// return the likes record that was deleted
		return likes;
	}

	public List<DrinkerDto> getAllDrinkersWhoLikeItemType(ItemType itemType) {
		List<DrinkerDto> drinkers = new ArrayList<>();
		String queryString = "SELECT drinker_id FROM likes WHERE item_id IN (SELECT item_id FROM items WHERE type=?)";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setString(1, itemType.toString());
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				DrinkerDto drinker = getDrinkersRepository().getDrinkerById(rs.getInt(1));
				drinkers.add(drinker);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllDrinkersWhoLikeItemType");
			e.printStackTrace();
		}
		
		return drinkers;
	}
	
	@Override
	public List<DrinkerDto> getAllDrinkersWhoLikeItemById(int itemId) {
		List<DrinkerDto> drinkers = new ArrayList<>();
		String queryString = "SELECT drinker_id FROM likes WHERE item_id=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, itemId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				DrinkerDto drinker = getDrinkersRepository().getDrinkerById(rs.getInt(1));
				drinkers.add(drinker);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllDrinkersWhoLikeItemById");
			e.printStackTrace();
		}
		
		return drinkers;
	}

}
