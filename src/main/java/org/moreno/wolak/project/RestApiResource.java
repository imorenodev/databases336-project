package org.moreno.wolak.project;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.moreno.wolak.project.resources.BarsResource;
import org.moreno.wolak.project.resources.BeersResource;
import org.moreno.wolak.project.resources.DrinkersResource;
import org.moreno.wolak.project.resources.FoodsResource;
import org.moreno.wolak.project.resources.QueriesResource;
import org.moreno.wolak.project.resources.SoftDrinksResource;

/**
 * Root resource (exposed at "/api/" path)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class RestApiResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
	@Path("/bars")
    public BarsResource getBarsResource() {
    		return new BarsResource();
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

	@GET
	@Path("/drinkers")
    public DrinkersResource getDrinkersResource() {
    		return new DrinkersResource();
    }
	
	@GET
	@Path("/queries")
    public QueriesResource getQueriesResource() {
    		return new QueriesResource();
    }
}
