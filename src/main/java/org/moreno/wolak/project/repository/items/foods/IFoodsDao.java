package org.moreno.wolak.project.repository.items.foods;

import java.util.List;

import org.moreno.wolak.project.dtos.FoodDto;

public interface IFoodsDao {
	FoodDto getFoodById(int foodId);
	FoodDto updateFoodById(int foodId, FoodDto food);
	int deleteFoodById(int foodId);
	List<FoodDto> getAllFoods();
	FoodDto createFood(FoodDto food);
}
