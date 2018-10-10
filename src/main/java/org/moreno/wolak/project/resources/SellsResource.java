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

import org.moreno.wolak.project.dtos.SellDto;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/sells")
public class SellsResource {
	
	private SellsRepository _repository = null;
	
	public SellsResource() {
		this._repository = SellsRepository.getSingletonInstance();
	}
	
	public SellsRepository getSellsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<SellDto> getSells() {
		return getSellsRepository().getAllSells();
	}
	
	@GET
	@Path("/{sellId}")
	public SellDto getSells(@PathParam("sellId") int sellId) {
		return getSellsRepository().getSellById(sellId);
	}
	
	@DELETE
	@Path("/{sellId}")
	public int deleteSellById(@PathParam("sellId") int sellId) {
		return getSellsRepository().deleteSellById(sellId);
	}
	
	@PUT
	@Path("/{sellId}")
	public SellDto updateSellById(@PathParam("sellId") int sellId, SellDto sell) {
		return getSellsRepository().updateSellById(sellId, sell);
	}
	
	@POST
	@Path("/")
	public SellDto createSell(SellDto sell) {
		return getSellsRepository().createSell(sell);
	}
}