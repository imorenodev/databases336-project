package org.moreno.wolak.project.repository.drinkers;

import java.util.List;

import org.moreno.wolak.project.dtos.DrinkerDto;


public interface IDrinkerDao {
	DrinkerDto getDrinkerById(int drinkerId);
	DrinkerDto updateDrinkerById(int drinkerId, DrinkerDto drinker);
	int deleteDrinkerById(int drinkerId);
	List<DrinkerDto> getAllDrinkers();
	DrinkerDto createDrinker(DrinkerDto drinker);
}
