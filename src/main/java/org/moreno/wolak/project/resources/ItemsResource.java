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
@Path("/items")
public class ItemsResource {

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
