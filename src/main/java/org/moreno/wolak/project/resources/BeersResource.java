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

import org.moreno.wolak.project.dtos.BarDto;
import org.moreno.wolak.project.dtos.BeerDto;
import org.moreno.wolak.project.dtos.DrinkerDto;
import org.moreno.wolak.project.repository.items.beers.BeersRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/beers")
public class BeersResource {

	private BeersRepository _repository = null;
	
	public BeersResource() {
		this._repository = BeersRepository.getSingletonInstance();
	}
	
	public BeersRepository getBeersRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<BeerDto> getBeers() {
		return getBeersRepository().getAllBeers();
	}
	
	@GET
	@Path("/{beerId}")
	public BeerDto getBeers(@PathParam("beerId") int beerId) {
		return getBeersRepository().getBeerById(beerId);
	}
	
	@DELETE
	@Path("/{beerId}")
	public int deleteBeerById(@PathParam("beerId") int beerId) {
		return getBeersRepository().deleteBeerById(beerId);
	}
	
	@PUT
	@Path("/{beerId}")
	public BeerDto updateBeerById(@PathParam("beerId") int beerId, BeerDto beer) {
		return getBeersRepository().updateBeerById(beerId, beer);
	}
	
	@POST
	@Path("/")
	public BeerDto createBeer(BeerDto beer) {
		return getBeersRepository().createBeer(beer);
	}
}
