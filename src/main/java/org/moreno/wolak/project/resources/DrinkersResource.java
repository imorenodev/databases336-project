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

import org.moreno.wolak.project.dtos.DrinkerDto;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/drinkers")
public class DrinkersResource {
	
	private DrinkersRepository _repository = null;
	
	public DrinkersResource() {
		this._repository = DrinkersRepository.getSingletonInstance();
	}
	
	public DrinkersRepository getDrinkersRepository() {
		return this._repository;
	}
	
	@GET
	@Path("/")
	public List<DrinkerDto> getDrinkers() {
		return getDrinkersRepository().getAllDrinkers();
	}

	@GET
	@Path("/{drinkerId}")
	public DrinkerDto getDrinkers(@PathParam("drinkerId") int drinkerId) {
		return getDrinkersRepository().getDrinkerById(drinkerId);
	}
	
	@DELETE
	@Path("/{drinkerId}")
	public int deleteDrinkerById(@PathParam("drinkerId") int drinkerId) {
		return getDrinkersRepository().deleteDrinkerById(drinkerId);
	}
	
	@PUT
	@Path("/{drinkerId}")
	public DrinkerDto updateDrinkerById(@PathParam("drinkerId") int drinkerId, DrinkerDto drinker) {
		return getDrinkersRepository().updateDrinkerById(drinkerId, drinker);
	}

	@POST
	@Path("/")
	public DrinkerDto createDrinker(DrinkerDto drinker) {
		return getDrinkersRepository().createDrinker(drinker);
	}
}
