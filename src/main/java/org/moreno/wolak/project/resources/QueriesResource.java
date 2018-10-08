package org.moreno.wolak.project.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/queries")
public class QueriesResource {

	@GET
	@Path("/")
	public String getQueries() {
		return "All the queries!";
	}
}
