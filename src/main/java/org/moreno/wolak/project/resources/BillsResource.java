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

import org.moreno.wolak.project.dtos.BillDto;
import org.moreno.wolak.project.repository.bills.BillsRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/bills")
public class BillsResource {
	
	private BillsRepository _repository = null;
	
	public BillsResource() {
		this._repository = BillsRepository.getSingletonInstance();
	}
	
	public BillsRepository getBillsRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<BillDto> getBills() {
		return getBillsRepository().getAllBills();
	}
	
	@GET
	@Path("/{billId}")
	public BillDto getBills(@PathParam("billId") int billId) {
		return getBillsRepository().getBillById(billId);
	}
	
	@DELETE
	@Path("/{billId}")
	public int deleteBillById(@PathParam("billId") int billId) {
		return getBillsRepository().deleteBillById(billId);
	}
	
	@PUT
	@Path("/{billId}")
	public BillDto updateBillById(@PathParam("billId") int billId, BillDto bill) {
		return getBillsRepository().updateBillById(billId, bill);
	}
	
	@POST
	@Path("/")
	public BillDto createBill(BillDto bill) {
		return getBillsRepository().createBill(bill);
	}
}
