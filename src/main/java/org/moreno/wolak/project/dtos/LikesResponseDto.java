package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"drinkerId", "item"
})
public class LikesResponseDto {

	private int drinkerId;
	private ItemDto item;
	
	
	public int getDrinkerId() {
		return drinkerId;
	}
	public void setDrinkerId(int drinkerId) {
		this.drinkerId = drinkerId;
	}
	public ItemDto getItem() {
		return item;
	}
	public void setItem(ItemDto item) {
		this.item = item;
	}

}
