package org.moreno.wolak.project.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
