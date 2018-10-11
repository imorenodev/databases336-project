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
import org.moreno.wolak.project.dtos.DayDto;
import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.OpenDto;
import org.moreno.wolak.project.dtos.OpenRequestDto;
import org.moreno.wolak.project.dtos.OpenResponseDto;
import org.moreno.wolak.project.dtos.SellsRequestDto;
import org.moreno.wolak.project.dtos.SellsResponseDto;
import org.moreno.wolak.project.repository.bars.BarsRepository;
import org.moreno.wolak.project.repository.issues.IssuesRepository;
import org.moreno.wolak.project.repository.opens.OpensRepository;
import org.moreno.wolak.project.repository.sells.SellsRepository;

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

	public SellsRepository getSellsRepository() {
		return SellsRepository.getSingletonInstance();
	}
	
	public OpensRepository getOpensRepository() {
		return OpensRepository.getSingletonInstance();
	}
	
	public IssuesRepository getIssuesRepository() {
		return IssuesRepository.getSingletonInstance();
	}


	/*********************************************************************************
	* 								CRUD ON BARS									   *
	*********************************************************************************/

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


	/*********************************************************************************
	* 						CRUD ON BAR SELLS ITEMS RELATION					   	   *
	*********************************************************************************/

	
	@GET
	@Path("/sells/beers")
	public List<BarDto> getAllBarsWhoSellBeers() {
		return getSellsRepository().getAllBarsWhoSellItemType(ItemDto.ItemType.beer);
	}

	@GET
	@Path("/sells/softdrinks")
	public List<BarDto> getAllBarsWhoSellSoftDrinks() {
		return getSellsRepository().getAllBarsWhoSellItemType(ItemDto.ItemType.soft_drink);
	}

	@GET
	@Path("/sells/foods")
	public List<BarDto> getAllBarsWhoSellFoods() {
		return getSellsRepository().getAllBarsWhoSellItemType(ItemDto.ItemType.food);
	}
	
	@GET
	@Path("/sells/{itemId}")
	public List<BarDto> getAllBarsWhoSellItemById(@PathParam("itemId") int itemId) {
		return getSellsRepository().getAllBarsWhoSellItemById(itemId);
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
	
	@PUT
	@Path("/{barId}/sells/{itemId}")
	public SellsResponseDto updateItemSoldByBar(@PathParam("barId") int barId, @PathParam("itemId") int itemId, SellsRequestDto sells) {
		return getSellsRepository().updateItemSoldByBar(barId, itemId, sells);
	}
	
	@DELETE
	@Path("/{barId}/sells/{itemId}")
	public SellsResponseDto deleteItemSoldByBar(@PathParam("barId") int barId, @PathParam("itemId") int itemId) {
		return getSellsRepository().deleteItemSoldByBar(barId, itemId);
	}
	
	

	/*********************************************************************************
	* 						CRUD ON BAR OPENS RELATION						   	   *
	*********************************************************************************/

	@GET
	@Path("/opens")
	public List<OpenResponseDto> getAllBarsHours() {
		return getOpensRepository().getAllBarsHours();
	}

	@GET
	@Path("/opens/{dayName}")
	public List<OpenResponseDto> getAllBarsHoursWhoAreOpenOnDayName(@PathParam("dayName") DayDto.DayName dayName) {
		return getOpensRepository().getAllBarsHoursWhoAreOpenOnDayName(dayName);
	}
	
	@GET
	@Path("/{barId}/opens")
	public List<OpenDto> getBarHoursById(@PathParam("barId") int barId) {
		return getOpensRepository().getBarHoursById(barId);
	}
	
	@GET
	@Path("/{barId}/opens/{dayName}")
	public OpenDto getBarHoursByDayName(@PathParam("barId") int barId, @PathParam("dayName") DayDto.DayName dayName) {
		return getOpensRepository().getBarHoursByDayName(barId, dayName);
	}

	@POST
	@Path("/{barId}/opens")
	public OpenDto createBarHoursByDayName(@PathParam("barId") int barId, OpenRequestDto opens) {
		return getOpensRepository().createBarHoursByDayName(barId, opens);
	}
	
	@PUT
	@Path("/{barId}/opens/{dayName}")
	public OpenDto createBarHoursByDayName(@PathParam("barId") int barId, @PathParam("dayName") DayDto.DayName dayName, OpenRequestDto opens) {
		return getOpensRepository().updateBarHoursByDayName(barId, dayName, opens);
	}
	
	@DELETE
	@Path("/{barId}/opens/{dayName}")
	public OpenDto deleteBarHoursByDayName(@PathParam("barId") int barId, @PathParam("dayName") DayDto.DayName dayName) {
		return getOpensRepository().deleteBarHoursByDayName(barId, dayName);
	}
	

	/*********************************************************************************
	* 						CRUD ON BAR ISSUES BILL RELATION						   	   *
	*********************************************************************************/

	@GET
	@Path("/issues")
	public List<IssuesResponseDto> getAllBillsIssuedByBars() {
		return getIssuesRepository().getAllBillsIssuedByBars();
	}

	@GET
	@Path("/{barId}/issues")
	public List<IssuesResponseDto> getAllBillsIssuedByBarById(@PathParam("barId") int barId) {
		return getIssuesRepository().getAllBillsIssuedByBarById(barId);
	}
	
	@GET
	@Path("/{barId}/issues/{billId}")
	public IssuesResponseDto getBillIssuedByBarById(@PathParam("barId") int barId, @PathParam("billId") int billId) {
		return getIssuesRepository().getBillIssuedByBarById(barId, billId);
	}
	
	@POST
	@Path("/{barId}/issues/{billId}")
	public IssuesResponseDto createBillIssuedByBar(@PathParam("barId") int barId, @PathParam("billId") int billId) {
		return getIssuesRepository().createBillIssuedByBar(barId, billId);
	}
	
	@DELETE
	@Path("/{barId}/issues/{billId}")
	public IssuesResponseDto deleteBillIssuedByBar(@PathParam("barId") int barId, @PathParam("billId") int billId) {
		return getIssuesRepository().deleteBillIssuedByBar(barId, billId);
	}
	
}
