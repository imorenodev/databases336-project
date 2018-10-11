package org.moreno.wolak.project.repository.items;

import java.util.List;

import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.SoftDrinkDto;

public interface IItemsDao {

	// ITEMS
	List<ItemDto> getAllItems();
	ItemDto getItemById(int itemId);
	int deleteItemById(int itemId);
	ItemDto updateItemById(int itemId, ItemDto item);
	ItemDto createItem(ItemDto item);

	// BEERS
	BeerDto getBeerById(int beerId);
	BeerDto updateBeerById(int beerId, BeerDto beer);
	int deleteBeerById(int beerId);
	List<BeerDto> getAllBeers();
	BeerDto createBeer(BeerDto beer);

	// FOODS
	FoodDto getFoodById(int foodId);
	FoodDto updateFoodById(int foodId, FoodDto food);
	int deleteFoodById(int foodId);
	List<FoodDto> getAllFoods();
	FoodDto createFood(FoodDto food);

	// SOFTDRINKS
	SoftDrinkDto getSoftDrinkById(int softDrinkId);
	SoftDrinkDto updateSoftDrinkById(int softDrinkId, SoftDrinkDto softDrink);
	int deleteSoftDrinkById(int softDrinkId);
	List<SoftDrinkDto> getAllSoftDrinks();
	SoftDrinkDto createSoftDrink(SoftDrinkDto softDrink);

}
