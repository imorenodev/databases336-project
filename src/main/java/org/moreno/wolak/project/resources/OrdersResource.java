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

import org.moreno.wolak.project.dtos.OrderDto;
import org.moreno.wolak.project.repository.orders.OrdersRepository;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/orders")
public class OrdersResource {
	
	private OrdersRepository _repository = null;
	
	public OrdersResource() {
		this._repository = OrdersRepository.getSingletonInstance();
	}
	
	public OrdersRepository getOrdersRepository() {
		return this._repository;
	}


	@GET
	@Path("/")
	public List<OrderDto> getOrders() {
		return getOrdersRepository().getAllOrders();
	}
	
	@GET
	@Path("/{orderId}")
	public OrderDto getOrders(@PathParam("orderId") int orderId) {
		return getOrdersRepository().getOrderById(orderId);
	}
	
	@DELETE
	@Path("/{orderId}")
	public int deleteOrderById(@PathParam("orderId") int orderId) {
		return getOrdersRepository().deleteOrderById(orderId);
	}
	
	@PUT
	@Path("/{orderId}")
	public OrderDto updateOrderById(@PathParam("orderId") int orderId, OrderDto order) {
		return getOrdersRepository().updateOrderById(orderId, order);
	}
	
	@POST
	@Path("/")
	public OrderDto createOrder(OrderDto order) {
		return getOrdersRepository().createOrder(order);
	}
}
