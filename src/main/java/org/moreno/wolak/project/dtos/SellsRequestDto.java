package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"itemId", "price"
})
public class SellsRequestDto {

	private int itemId;
	private double price;
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
