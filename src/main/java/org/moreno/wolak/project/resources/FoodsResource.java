package org.moreno.wolak.project.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.repository.items.foods.FoodsRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/foods")
public class FoodsResource {

	private FoodsRepository _repository = null;
	
	public FoodsResource() {
		this._repository = FoodsRepository.getSingletonInstance();
	}
	
	public FoodsRepository getFoodsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<FoodDto> getFoods() {
		return getFoodsRepository().getAllFoods();
	}
	
	@GET
	@Path("/{foodId}")
	public FoodDto getFoods(@PathParam("foodId") int foodId) {
		return getFoodsRepository().getFoodById(foodId);
	}
	
	@POST
	@Path("/")
	public FoodDto createFood(FoodDto food) {
		return getFoodsRepository().createFood(food);
	}
}
