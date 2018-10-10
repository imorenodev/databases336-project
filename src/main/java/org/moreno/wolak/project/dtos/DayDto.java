package org.moreno.wolak.project.dtos;


public class DayDto {

	public enum DayName {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	private int dayId;
	private String name;

	
	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
