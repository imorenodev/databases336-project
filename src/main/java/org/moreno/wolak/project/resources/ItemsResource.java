package org.moreno.wolak.project.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.repository.items.ItemsRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/items")
public class ItemsResource {

	private ItemsRepository _itemsRepository = null;

	public ItemsResource() {
		this._itemsRepository = ItemsRepository.getSingletonInstance();
	}
	
	public ItemsRepository getItemsRepository() {
		return this._itemsRepository;
	}
	
	
	@GET
	@Path("/")
    public List<ItemDto> getAllItems() {
    		return getItemsRepository().getAllItems();
    }

	@GET
	@Path("/beers")
    public BeersResource getBeersResource() {
    		return new BeersResource();
    }
	
	@GET
	@Path("/foods")
    public FoodsResource getFoodsResource() {
    		return new FoodsResource();
    }
	
	@GET
	@Path("/softdrinks")
    public SoftDrinksResource getSoftDrinksResource() {
    		return new SoftDrinksResource();
    }
}
