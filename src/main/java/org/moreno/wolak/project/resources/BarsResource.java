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
import org.moreno.wolak.project.repository.bars.BarsRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/bars")
public class BarsResource {
	
	private BarsRepository _repository = null;
	
	public BarsResource() {
		this._repository = BarsRepository.getSingletonInstance();
	}
	
	public BarsRepository getBarsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<BarDto> getBars() {
		return getBarsRepository().getAllBars();
	}
	
	@GET
	@Path("/{barId}")
	public BarDto getBars(@PathParam("barId") int barId) {
		return getBarsRepository().getBarById(barId);
	}
	
	@DELETE
	@Path("/{barId}")
	public int deleteBarById(@PathParam("barId") int barId) {
		return getBarsRepository().deleteBarById(barId);
	}
	
	@PUT
	@Path("/{barId}")
	public BarDto updateBarById(@PathParam("barId") int barId, BarDto bar) {
		return getBarsRepository().updateBarById(barId, bar);
	}
	
	@POST
	@Path("/")
	public BarDto createBar(BarDto bar) {
		return getBarsRepository().createBar(bar);
	}
}
