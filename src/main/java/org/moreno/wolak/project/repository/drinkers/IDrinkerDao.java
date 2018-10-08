package org.moreno.wolak.project.repository.drinkers;

import java.util.List;

import org.moreno.wolak.project.dtos.DrinkerDto;


public interface IDrinkerDao {
	DrinkerDto getDrinkerById(int drinkerId);
	List<DrinkerDto> getAllDrinkers();
}
