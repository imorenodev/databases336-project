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
import org.moreno.wolak.project.dtos.FrequentsResponseDto;
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.LikesRequestDto;
import org.moreno.wolak.project.dtos.LikesResponseDto;
import org.moreno.wolak.project.dtos.OrderDto;
import org.moreno.wolak.project.dtos.PaysRequestDto;
import org.moreno.wolak.project.dtos.PaysResponseDto;
import org.moreno.wolak.project.repository.drinkers.DrinkersRepository;
import org.moreno.wolak.project.repository.frequents.FrequentsRepository;
import org.moreno.wolak.project.repository.likes.LikesRepository;
import org.moreno.wolak.project.repository.orders.OrdersRepository;
import org.moreno.wolak.project.repository.pays.PaysRepository;

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
	
	public LikesRepository getLikesRepository() {
		return LikesRepository.getSingletonInstance();
	}
	
	public OrdersRepository getOrdersRepository() {
		return OrdersRepository.getSingletonInstance();
	}
	
	public PaysRepository getPaysRepository() {
		return PaysRepository.getSingletonInstance();
	}

	public FrequentsRepository getFrequentsRepository() {
		return FrequentsRepository.getSingletonInstance();
	}

	
	/*********************************************************************************
	* 								CRUD ON DRINKERS 							   *
	*********************************************************************************/
	
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
	
	
	/*********************************************************************************
	* 						CRUD ON DRINKERS LIKES ITEMS RELATION 				   *
	*********************************************************************************/
	
	@GET
	@Path("/likes/beers")
	public List<DrinkerDto> getAllDrinkersWhoLikeBeers() {
		return getLikesRepository().getAllDrinkersWhoLikeItemType(ItemDto.ItemType.beer);
	}

	@GET
	@Path("/likes/softdrinks")
	public List<DrinkerDto> getAllDrinkersWhoLikeSoftDrinks() {
		return getLikesRepository().getAllDrinkersWhoLikeItemType(ItemDto.ItemType.soft_drink);
	}

	@GET
	@Path("/likes/foods")
	public List<DrinkerDto> getAllDrinkersWhoLikeFoods() {
		return getLikesRepository().getAllDrinkersWhoLikeItemType(ItemDto.ItemType.food);
	}
	
	@GET
	@Path("/likes/{itemId}")
	public List<DrinkerDto> getAllDrinkersWhoLikeItemById(@PathParam("itemId") int itemId) {
		return getLikesRepository().getAllDrinkersWhoLikeItemById(itemId);
	}

	@GET
	@Path("/{drinkerId}/likes")
	public List<LikesResponseDto> getAllItemsLikedByDrinker(@PathParam("drinkerId") int drinkerId) {
		return getLikesRepository().getAllItemsLikedByDrinker(drinkerId);
	}
	
	@POST
	@Path("/{drinkerId}/likes")
	public LikesResponseDto createDrinkerLikesItem(@PathParam("drinkerId") int drinkerId, LikesRequestDto likes) {
		return getLikesRepository().createItemLikedByDrinker(drinkerId, likes);
	}
	
	@GET
	@Path("/{drinkerId}/likes/beers")
	public List<LikesResponseDto> getAllBeersLikedByDrinker(@PathParam("drinkerId") int drinkerId) {
		return getLikesRepository().getAllItemsOfTypeLikedByDrinker(drinkerId, ItemType.beer);
	}

	@GET
	@Path("/{drinkerId}/likes/foods")
	public List<LikesResponseDto> getAllFoodsLikedByDrinker(@PathParam("drinkerId") int drinkerId) {
		return getLikesRepository().getAllItemsOfTypeLikedByDrinker(drinkerId, ItemType.food);
	}

	@GET
	@Path("/{drinkerId}/likes/softdrinks")
	public List<LikesResponseDto> getAllSoftDrinksLikedByDrinker(@PathParam("drinkerId") int drinkerId) {
		return getLikesRepository().getAllItemsOfTypeLikedByDrinker(drinkerId, ItemType.soft_drink);
	}
	
	@GET
	@Path("/{drinkerId}/likes/{itemId}")
	public LikesResponseDto getItemLikedByDrinker(@PathParam("drinkerId") int drinkerId, @PathParam("itemId") int itemId) {
		return getLikesRepository().getItemLikedByDrinker(drinkerId, itemId);
	}
	
	@DELETE
	@Path("/{drinkerId}/likes/{itemId}")
	public LikesResponseDto deleteItemLikedByDrinker(@PathParam("drinkerId") int drinkerId, @PathParam("itemId") int itemId) {
		return getLikesRepository().deleteItemLikedByDrinker(drinkerId, itemId);
	}
		
	/*********************************************************************************
	* 						CRUD ON DRINKERS ORDERS RELATION 				   	   *
	*********************************************************************************/
		
	@GET
	@Path("/{drinkerId}/orders")
	public List<OrderDto> getOrdersByDrinkerId(@PathParam("drinkerId") int drinkerId) {
		return getOrdersRepository().getOrdersByDrinkerId(drinkerId);
	}
	
	/*********************************************************************************
	* 						CRUD ON DRINKERS PAYS RELATION	 				   	   *
	*********************************************************************************/
	
	
	@GET
	@Path("/pays")
	public List<PaysResponseDto> getAllPaymentsByDrinkers() {
		return getPaysRepository().getAllPaymentsByDrinkers();
	}

	@GET
	@Path("/{drinkerId}/pays")
	public List<PaysResponseDto> getAllPaymentsByDrinkerById(@PathParam("drinkerId") int drinkerId) {
		return getPaysRepository().getAllPaymentsByDrinkerById(drinkerId);
	}
	
	@GET
	@Path("/{drinkerId}/pays/{billId}")
	public PaysResponseDto getPaymentsByDrinkerById(@PathParam("drinkerId") int drinkerId, @PathParam("billId") int billId) {
		return getPaysRepository().getPaymentByDrinkerById(drinkerId, billId);
	}
	
	@POST
	@Path("/{drinkerId}/pays")
	public PaysResponseDto createPaymentByDrinker(@PathParam("drinkerId") int drinkerId, PaysRequestDto pays) {
		return getPaysRepository().createPaymentByDrinker(drinkerId, pays);
	}
	
	@PUT
	@Path("/{drinkerId}/pays/{billId}")
	public PaysResponseDto updatePaymentByDrinkerById(@PathParam("drinkerId") int drinkerId, @PathParam("billId") int billId, PaysRequestDto pays) {
		return getPaysRepository().updatePaymentByDrinkerById(drinkerId, billId, pays);
	}
	
	@DELETE
	@Path("/{drinkerId}/pays/{billId}")
	public PaysResponseDto deletePaymentByDrinkerById(@PathParam("drinkerId") int drinkerId, @PathParam("billId") int billId) {
		return getPaysRepository().deletePaymentByDrinkerById(drinkerId, billId);
	}

	
	/*********************************************************************************
	* 					CRUD ON DRINKERS FREQUENTS RELATION	 				   	   *
	*********************************************************************************/
	
	@GET
	@Path("/frequents")
	public List<FrequentsResponseDto> getAllBarsFrequentedByDrinkers() {
		return getFrequentsRepository().getAllBarsFrequentedByDrinkers();
	}

	@GET
	@Path("/{drinkerId}/frequents")
	public List<FrequentsResponseDto> getAllBarsFrequentedByDrinker(@PathParam("drinkerId") int drinkerId) {
		return getFrequentsRepository().getAllBarsFrequentedByDrinker(drinkerId);
	}
	
	@GET
	@Path("/{drinkerId}/frequents/{barId}")
	public FrequentsResponseDto getBarFrequentedByDrinker(@PathParam("drinkerId") int drinkerId, @PathParam("barId") int barId) {
		return getFrequentsRepository().getBarFrequentedByDrinker(drinkerId, barId);
	}
	
	@POST
	@Path("/{drinkerId}/frequents/{barId}")
	public FrequentsResponseDto createDrinkerFrequentsBarById(@PathParam("drinkerId") int drinkerId, @PathParam("barId") int barId) {
		return getFrequentsRepository().createDrinkerFrequentsBarById(drinkerId, barId);
	}
	
	@DELETE
	@Path("/{drinkerId}/frequents/{barId}")
	public FrequentsResponseDto deleteDrinkerFrequentsBarById(@PathParam("drinkerId") int drinkerId, @PathParam("barId") int barId) {
		return getFrequentsRepository().deleteDrinkerFrequentsBarById(drinkerId, barId);
	}
	
}
