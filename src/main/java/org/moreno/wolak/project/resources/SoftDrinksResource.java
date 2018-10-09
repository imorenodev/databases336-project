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

import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.FoodDto;
import org.moreno.wolak.project.dtos.SoftDrinkDto;
import org.moreno.wolak.project.repository.items.softdrinks.SoftDrinksRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/softdrinks")
public class SoftDrinksResource {

	private SoftDrinksRepository _repository = null;
	
	public SoftDrinksResource() {
		this._repository = SoftDrinksRepository.getSingletonInstance();
	}
	
	public SoftDrinksRepository getSoftDrinksRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<SoftDrinkDto> getSoftDrinks() {
		return getSoftDrinksRepository().getAllSoftDrinks();
	}
	
	@GET
	@Path("/{softDrinkId}")
	public SoftDrinkDto getSoftDrinks(@PathParam("softDrinkId") int softDrinkId) {
		return getSoftDrinksRepository().getSoftDrinkById(softDrinkId);
	}
	
	@DELETE
	@Path("/{softDrinkId}")
	public int deleteSoftDrinkById(@PathParam("softDrinkId") int softDrinkId) {
		return getSoftDrinksRepository().deleteSoftDrinkById(softDrinkId);
	}
	
	@PUT
	@Path("/{softDrinkId}")
	public SoftDrinkDto updateSoftDrinkById(@PathParam("softDrinkId") int softDrinkId, SoftDrinkDto softDrink) {
		return getSoftDrinksRepository().updateSoftDrinkById(softDrinkId, softDrink);
	}
	
	@POST
	@Path("/")
	public SoftDrinkDto createSoftDrink(SoftDrinkDto softDrink) {
		return getSoftDrinksRepository().createSoftDrink(softDrink);
	}
}
