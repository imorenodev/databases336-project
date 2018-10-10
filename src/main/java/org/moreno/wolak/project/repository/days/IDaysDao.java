package org.moreno.wolak.project.repository.days;

import java.util.List;

import org.moreno.wolak.project.dtos.DayDto;

public interface IDaysDao {
	DayDto getDayByName(String dayName);
	List<DayDto> getAllDays();
}
