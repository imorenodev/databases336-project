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
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.SellsRequestDto;
import org.moreno.wolak.project.dtos.SellsResponseDto;
import org.moreno.wolak.project.repository.bars.BarsRepository;
import org.moreno.wolak.project.repository.sells.SellsRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/bars")
public class BarsResource {
	
	private BarsRepository _repository = null;
	private SellsRepository _sellsRepository = null;
	
	public BarsResource() {
		this._repository = BarsRepository.getSingletonInstance();
		this._sellsRepository = SellsRepository.getSingletonInstance();
	}
	
	public BarsRepository getBarsRepository() {
		return this._repository;
	}

	public SellsRepository getSellsRepository() {
		return this._sellsRepository;
	}


	@GET
	@Path("/")
	public List<BarDto> getBars() {
		return getBarsRepository().getAllBars();
	}
	
	@POST
	@Path("/")
	public BarDto createBar(BarDto bar) {
		return getBarsRepository().createBar(bar);
	}


	@GET
	@Path("/{barId}")
	public BarDto getBarById(@PathParam("barId") int barId) {
		return getBarsRepository().getBarById(barId);
	}	

	@PUT
	@Path("/{barId}")
	public BarDto updateBarById(@PathParam("barId") int barId, BarDto bar) {
		return getBarsRepository().updateBarById(barId, bar);
	}
	
	@DELETE
	@Path("/{barId}")
	public int deleteBarById(@PathParam("barId") int barId) {
		return getBarsRepository().deleteBarById(barId);
	}

	
	@GET
	@Path("/{barId}/sells")
	public List<SellsResponseDto> getAllItemsSoldByBar(@PathParam("barId") int barId) {
		return getSellsRepository().getAllItemsSoldByBar(barId);
	}
	
	@POST
	@Path("/{barId}/sells")
	public SellsResponseDto createBarSellsItem(@PathParam("barId") int barId, SellsRequestDto sells) {
		return getSellsRepository().createItemSoldByBar(barId, sells);
	}
	
	@GET
	@Path("/{barId}/sells/beers")
	public List<SellsResponseDto> getAllBeersSoldByBar(@PathParam("barId") int barId) {
		return getSellsRepository().getAllItemsOfTypeSoldByBar(barId, ItemType.beer);
	}

	@GET
	@Path("/{barId}/sells/foods")
	public List<SellsResponseDto> getAllFoodsSoldByBar(@PathParam("barId") int barId) {
		return getSellsRepository().getAllItemsOfTypeSoldByBar(barId, ItemType.food);
	}

	@GET
	@Path("/{barId}/sells/softdrinks")
	public List<SellsResponseDto> getAllSoftDrinksSoldByBar(@PathParam("barId") int barId) {
		return getSellsRepository().getAllItemsOfTypeSoldByBar(barId, ItemType.soft_drink);
	}
	
	@GET
	@Path("/{barId}/sells/{itemId}")
	public SellsResponseDto getItemSoldByBar(@PathParam("barId") int barId, @PathParam("itemId") int itemId) {
		return getSellsRepository().getItemSoldByBar(barId, itemId);
	}
	

	@DELETE
	@Path("/{barId}/sells/{itemId}")
	public SellsResponseDto deleteItemSoldByBar(@PathParam("barId") int barId, @PathParam("itemId") int itemId) {
		return getSellsRepository().deleteItemSoldByBar(barId, itemId);
	}
	
}
