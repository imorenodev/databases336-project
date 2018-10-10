package org.moreno.wolak.project.repository.days;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moreno.wolak.project.dtos.DayDto;
import org.moreno.wolak.project.repository.ConnectionFactory;
import org.moreno.wolak.project.repository.days.IDaysDao;

public class DaysRepository implements IDaysDao {

	private static DaysRepository repositorySingletonInstance = null;
	

	public static DaysRepository getSingletonInstance() {
		if (repositorySingletonInstance == null) {
			repositorySingletonInstance = new DaysRepository();
		}
		return repositorySingletonInstance;
		
	}
	
	@Override
	public List<DayDto> getAllDays() {
		List<DayDto> days = new ArrayList<>();
		String queryString = "SELECT * FROM days";
		
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {

			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				DayDto day = new DayDto();
				day.setDayId(rs.getInt(1));
				day.setName(rs.getString(2));
				
				days.add(day);
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getAllDays");
			e.printStackTrace();
		}
		
		return days;
	}


	@Override
	public DayDto getDayByName(String dayName) {
		DayDto day = new DayDto();
		String queryString = "SELECT * FROM days WHERE name=?";
		
			
		try (Connection connection = ConnectionFactory.getConnection(); 
			 PreparedStatement queryStatement = connection.prepareStatement(queryString)) {
			
			queryStatement.setString(1, dayName);
			ResultSet rs = queryStatement.executeQuery();
			
			while (rs.next()) {
				day.setDayId(Integer.parseInt(rs.getString(1)));
				day.setName(rs.getString(2));
			}

		} catch (SQLException e) {
			System.out.println("FAILED: getDayByName");
			e.printStackTrace();
		} 
		
		return day;
	}

}
