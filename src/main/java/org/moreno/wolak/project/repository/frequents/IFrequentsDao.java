package org.moreno.wolak.project.repository.frequents;

import java.util.List;

import org.moreno.wolak.project.dtos.FrequentsResponseDto;
import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.dtos.PaysRequestDto;
import org.moreno.wolak.project.dtos.PaysResponseDto;

public interface IFrequentsDao {

	List<FrequentsResponseDto> getAllBarsFrequentedByDrinkers();
	List<FrequentsResponseDto> getAllBarsFrequentedByDrinker(int drinkerId);
	FrequentsResponseDto getBarFrequentedByDrinker(int drinkerId, int barId);
	FrequentsResponseDto createDrinkerFrequentsBarById(int drinkerId, int barId);
	FrequentsResponseDto deleteDrinkerFrequentsBarById(int drinkerId, int barId);

}
