package org.moreno.wolak.project.dtos;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({
	"barId", "bill"
})
public class IssuesResponseDto {

	private int barId;
	private BillDto bill;
	
	
	public int getBarId() {
		return barId;
	}
	public void setBarId(int barId) {
		this.barId = barId;
	}
	public BillDto getBill() {
		return bill;
	}
	public void setBill(BillDto bill) {
		this.bill = bill;
	}
	
}
