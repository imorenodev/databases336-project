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

import org.moreno.wolak.project.dtos.SoftDrinkDto;
import org.moreno.wolak.project.repository.items.ItemsRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/softdrinks")
public class SoftDrinksResource {

	private ItemsRepository _repository = null;
	
	public SoftDrinksResource() {
		this._repository = ItemsRepository.getSingletonInstance();
	}
	
	public ItemsRepository getItemsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<SoftDrinkDto> getSoftDrinks() {
		return getItemsRepository().getAllSoftDrinks();
	}
	
	@GET
	@Path("/{softDrinkId}")
	public SoftDrinkDto getSoftDrinks(@PathParam("softDrinkId") int softDrinkId) {
		return getItemsRepository().getSoftDrinkById(softDrinkId);
	}
	
	@DELETE
	@Path("/{softDrinkId}")
	public int deleteSoftDrinkById(@PathParam("softDrinkId") int softDrinkId) {
		return getItemsRepository().deleteSoftDrinkById(softDrinkId);
	}
	
	@PUT
	@Path("/{softDrinkId}")
	public SoftDrinkDto updateSoftDrinkById(@PathParam("softDrinkId") int softDrinkId, SoftDrinkDto softDrink) {
		return getItemsRepository().updateSoftDrinkById(softDrinkId, softDrink);
	}
	
	@POST
	@Path("/")
	public SoftDrinkDto createSoftDrink(SoftDrinkDto softDrink) {
		return getItemsRepository().createSoftDrink(softDrink);
	}
}
