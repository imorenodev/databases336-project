package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"dayName", "openTime", "closeTime"
})
public class OpenRequestDto {

	private String dayName;
	private String openTime;
	private String closeTime;
	

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
