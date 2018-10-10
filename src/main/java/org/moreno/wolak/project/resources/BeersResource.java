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
import org.moreno.wolak.project.repository.items.ItemsRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/beers")
public class BeersResource {

	private ItemsRepository _repository = null;
	
	public BeersResource() {
		this._repository = ItemsRepository.getSingletonInstance();
	}
	
	public ItemsRepository getItemsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<BeerDto> getBeers() {
		return getItemsRepository().getAllBeers();
	}
	
	@GET
	@Path("/{beerId}")
	public BeerDto getBeers(@PathParam("beerId") int beerId) {
		return getItemsRepository().getBeerById(beerId);
	}
	
	@DELETE
	@Path("/{beerId}")
	public int deleteBeerById(@PathParam("beerId") int beerId) {
		return getItemsRepository().deleteBeerById(beerId);
	}
	
	@PUT
	@Path("/{beerId}")
	public BeerDto updateBeerById(@PathParam("beerId") int beerId, BeerDto beer) {
		return getItemsRepository().updateBeerById(beerId, beer);
	}
	
	@POST
	@Path("/")
	public BeerDto createBeer(BeerDto beer) {
		return getItemsRepository().createBeer(beer);
	}
}
