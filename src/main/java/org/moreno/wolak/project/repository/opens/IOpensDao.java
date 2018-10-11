package org.moreno.wolak.project.repository.opens;

import java.util.List;

import org.moreno.wolak.project.dtos.DayDto.DayName;
import org.moreno.wolak.project.dtos.OpenDto;
import org.moreno.wolak.project.dtos.OpenRequestDto;
import org.moreno.wolak.project.dtos.OpenResponseDto;

public interface IOpensDao {

	List<OpenResponseDto> getAllBarsHoursWhoAreOpenOnDayName(DayName dayName);
	List<OpenDto> getBarHoursById(int barId);
	List<OpenResponseDto> getAllBarsHours();
	OpenDto getBarHoursByDayName(int barId, DayName dayName);
	OpenDto createBarHoursByDayName(int barId, OpenRequestDto opens);
	OpenDto updateBarHoursByDayName(int barId, DayName dayName, OpenRequestDto opens);
	OpenDto deleteBarHoursByDayName(int barId, DayName dayName);

}
