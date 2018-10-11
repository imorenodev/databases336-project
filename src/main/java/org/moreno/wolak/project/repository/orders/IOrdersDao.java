package org.moreno.wolak.project.repository.orders;

import java.util.List;

import org.moreno.wolak.project.dtos.OrderDto;

public interface IOrdersDao {
	OrderDto getOrderById(int orderId);
	OrderDto updateOrderById(int orderId, OrderDto order);
	int deleteOrderById(int orderId);
	List<OrderDto> getAllOrders();
	OrderDto createOrder(OrderDto order);
	List<OrderDto> getOrdersByBillId(int billId);
	List<OrderDto> getOrdersByDrinkerId(int drinkerId);
}
