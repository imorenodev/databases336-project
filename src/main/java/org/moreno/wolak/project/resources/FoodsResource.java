package org.moreno.wolak.project.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.repository.items.ItemsRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/foods")
public class FoodsResource {

	private ItemsRepository _repository = null;
	
	public FoodsResource() {
		this._repository = ItemsRepository.getSingletonInstance();
	}
	
	public ItemsRepository getItemsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<FoodDto> getFoods() {
		return getItemsRepository().getAllFoods();
	}
	
	@GET
	@Path("/{foodId}")
	public FoodDto getFoods(@PathParam("foodId") int foodId) {
		return getItemsRepository().getFoodById(foodId);
	}
	
	@DELETE
	@Path("/{foodId}")
	public int deleteFoodById(@PathParam("foodId") int foodId) {
		return getItemsRepository().deleteFoodById(foodId);
	}
	
	@PUT
	@Path("/{foodId}")
	public FoodDto updateFoodById(@PathParam("foodId") int foodId, FoodDto food) {
		return getItemsRepository().updateFoodById(foodId, food);
	}
	
	@POST
	@Path("/")
	public FoodDto createFood(FoodDto food) {
		return getItemsRepository().createFood(food);
	}
}
