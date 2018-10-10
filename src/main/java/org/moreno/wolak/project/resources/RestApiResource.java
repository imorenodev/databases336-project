package org.moreno.wolak.project.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "/api/" path)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class RestApiResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello Drinker!";
    }

    @GET
	@Path("/bars")
    public BarsResource getBarsResource() {
    		return new BarsResource();
    }

    @GET
	@Path("/items")
    public ItemsResource getItemsResource() {
    		return new ItemsResource();
    }

	@GET
	@Path("/drinkers")
    public DrinkersResource getDrinkersResource() {
    		return new DrinkersResource();
    }
	
	@GET
	@Path("/days")
    public DaysResource getDaysResource() {
    		return new DaysResource();
    }
	
	@GET
	@Path("/bills")
    public BillsResource getBillsResource() {
    		return new BillsResource();
    }
	
	@GET
	@Path("/orders")
    public OrdersResource getOrdersResource() {
    		return new OrdersResource();
    }
	
	@GET
	@Path("/sells")
    public SellsResource getSellsResource() {
    		return new SellsResource();
    }

	@GET
	@Path("/queries")
    public QueriesResource getQueriesResource() {
    		return new QueriesResource();
    }
}
