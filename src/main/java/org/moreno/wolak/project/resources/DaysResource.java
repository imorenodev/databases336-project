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
import org.moreno.wolak.project.dtos.ItemDto;
import org.moreno.wolak.project.repository.bars.BarsRepository;
import org.moreno.wolak.project.repository.days.DaysRepository;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/days")
public class DaysResource {
	
	private DaysRepository _repository = null;
	
	public DaysResource() {
		this._repository = DaysRepository.getSingletonInstance();
	}
	
	public DaysRepository getDaysRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<DayDto> getAllDays() {
		return getDaysRepository().getAllDays();
	}
	
	@GET
	@Path("/{dayName}")
	public DayDto getDay(@PathParam("dayName") DayDto.DayName dayName) {
		return getDaysRepository().getDayByName(dayName.toString());
	}

}
