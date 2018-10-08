package org.moreno.wolak.project.repository.items.softdrinks;

import java.util.List;

import org.moreno.wolak.project.dtos.SoftDrinkDto;

public interface ISoftDrinksDao {
	SoftDrinkDto getSoftDrinkById(int softDrinkId);
	List<SoftDrinkDto> getAllSoftDrinks();
	SoftDrinkDto createSoftDrink(SoftDrinkDto softDrink);
}
