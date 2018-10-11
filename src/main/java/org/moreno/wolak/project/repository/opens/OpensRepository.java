package org.moreno.wolak.project.repository.opens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.DayDto;
import org.moreno.wolak.project.dtos.DayDto.DayName;
import org.moreno.wolak.project.dtos.OpenDto;
import org.moreno.wolak.project.dtos.OpenRequestDto;
import org.moreno.wolak.project.dtos.OpenResponseDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.bars.BarsRepository;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;

public class OpensRepository implements IOpensDao {

	private static OpensRepository opensRepositorySingletonInstance = null;


	public static OpensRepository getSingletonInstance() {
		if (opensRepositorySingletonInstance == null) {
			opensRepositorySingletonInstance = new OpensRepository();
		}
		return opensRepositorySingletonInstance;
	}
	
	private DrinkersRepository getDrinkersRepository() {
		return DrinkersRepository.getSingletonInstance();
	}
	
	private BarsRepository getBarsRepository() {
		return BarsRepository.getSingletonInstance();
	}



	@Override
	public List<OpenResponseDto> getAllBarsHours() {
		List<OpenResponseDto> openHours = new ArrayList<>();
		String queryString = "SELECT * FROM opens"; 
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				OpenResponseDto open = new OpenResponseDto();
				open.setBar(getBarsRepository().getBarById(rs.getInt(1)));
				open.setDayName(rs.getString(2));
				open.setOpenTime(rs.getString(3));
				open.setCloseTime(rs.getString(4));
				
				openHours.add(open);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBarsHours");
			e.printStackTrace();
		}
		
		return openHours;
	}

	@Override
	public List<OpenDto> getBarHoursById(int barId) {
		List<OpenDto> openHours = new ArrayList<>();
		String queryString = "SELECT * FROM opens WHERE bar_id=?"; 
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				OpenDto open = new OpenDto();
				open.setBarId(rs.getInt(1));
				open.setDayName(rs.getString(2));
				open.setOpenTime(rs.getString(3));
				open.setCloseTime(rs.getString(4));
				
				openHours.add(open);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBarsHoursById");
			e.printStackTrace();
		}
		
		return openHours;
	}

	@Override
	public List<OpenResponseDto> getAllBarsHoursWhoAreOpenOnDayName(DayName dayName) {
		List<OpenResponseDto> barHours = new ArrayList<>();
		String queryString = "SELECT * FROM opens WHERE day_name=?"; 
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setString(1, dayName.toString());
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				OpenResponseDto open = new OpenResponseDto();

				open.setBar(getBarsRepository().getBarById(rs.getInt(1)));
				open.setDayName(rs.getString(2));
				open.setOpenTime(rs.getString(3));
				open.setCloseTime(rs.getString(4));

				barHours.add(open);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllBarsHoursWhoAreOpenOnDayName");
			e.printStackTrace();
		}
		
		return barHours;
	}

	@Override
	public OpenDto getBarHoursByDayName(int barId, DayName dayName) {
		OpenDto openHours = new OpenDto();
		String queryString = "SELECT * FROM opens WHERE bar_id=? AND day_name=?"; 
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			queryStatement.setInt(1, barId);
			queryStatement.setString(2, dayName.toString());
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				openHours.setBarId(rs.getInt(1));
				openHours.setDayName(rs.getString(2));
				openHours.setOpenTime(rs.getString(3));
				openHours.setCloseTime(rs.getString(4));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getBarsHoursByDayName");
			e.printStackTrace();
		}
		
		return openHours;
	}

	@Override
	public OpenDto createBarHoursByDayName(int barId, OpenRequestDto opens) {
		String insertString = "INSERT INTO opens (bar_id, day_name, open_time, close_time) VALUES(?, ?, ?, ?)";

		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement insertStatement = connection.prepareStatement(insertString)) {

			insertStatement.setInt(1, barId);
			insertStatement.setString(2, opens.getDayName());
			insertStatement.setString(3, opens.getOpenTime());
			insertStatement.setString(4, opens.getCloseTime());

			insertStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: createBarSellsItem");
			e.printStackTrace();
		} 
		
		// upon successful creation of opens relation, return new opens record
		return getBarHoursByDayName(barId, DayDto.DayName.valueOf(opens.getDayName()));
	}

	@Override
	public OpenDto updateBarHoursByDayName(int barId, DayName dayName, OpenRequestDto opens) {
		String updateString = "UPDATE opens SET open_time=?, close_time=? WHERE bar_id=? AND day_name=?";

		try (Connection connection = ConnectionFactory.getConnection();
			PreparedStatement updateStatement = connection.prepareStatement(updateString)) {

			updateStatement.setString(1, opens.getOpenTime());
			updateStatement.setString(2, opens.getCloseTime());
			updateStatement.setInt(3, barId);
			updateStatement.setString(4, dayName.toString());

			updateStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FAILED: updateItemById");
			e.printStackTrace();
		} 
		
		// upon successful creation of opens relation, return new opens record
		return getBarHoursByDayName(barId, DayDto.DayName.valueOf(opens.getDayName()));
	}

	@Override
	public OpenDto deleteBarHoursByDayName(int barId, DayName dayName) {
		OpenDto opens = getBarHoursByDayName(barId, dayName);
		String deleteString = "DELETE FROM opens WHERE bar_id=? AND day_name=?";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
			
			deleteStatement.setInt(1, barId);
			deleteStatement.setString(2, dayName.toString());
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("FAILED: deleteBarHoursByDayName");
			e.printStackTrace();
		} 

		// return the opens record that was deleted
		return opens;
	}

}
