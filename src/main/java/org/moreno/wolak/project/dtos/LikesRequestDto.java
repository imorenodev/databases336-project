package org.moreno.wolak.project.dtos;


public class LikesRequestDto {

	private int drinkerId;
	private int itemId;
	
	
	public int getDrinkerId() {
		return drinkerId;
	}
	public void setDrinkerId(int drinkerId) {
		this.drinkerId = drinkerId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
