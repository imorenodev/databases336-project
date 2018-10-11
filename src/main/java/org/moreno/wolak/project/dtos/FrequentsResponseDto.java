package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"drinkerId", "bar"
})
public class FrequentsResponseDto {

	private BarDto bar;
	private int drinkerId;
	

	public BarDto getBar() {
		return bar;
	}
	public void setBar(BarDto bar) {
		this.bar = bar;
	}
	public int getDrinkerId() {
		return drinkerId;
	}
	public void setDrinkerId(int drinkerId) {
		this.drinkerId = drinkerId;
	}
	
}
