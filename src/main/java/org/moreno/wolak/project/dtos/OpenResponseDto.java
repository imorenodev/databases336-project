package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"dayName", "bar", "openTime", "closeTime"
})
public class OpenResponseDto {

	private BarDto bar;
	private String dayName;
	private String openTime;
	private String closeTime;
	
	
	public BarDto getBar() {
		return bar;
	}
	public void setBar(BarDto bar) {
		this.bar = bar;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

}
