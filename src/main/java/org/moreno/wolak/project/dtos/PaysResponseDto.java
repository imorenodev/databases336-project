package org.moreno.wolak.project.dtos;


public class PaysResponseDto {

	private int billId;
	private int drinkerId;
	private String paymentDate;
	private double totalPaid;
	
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getDrinkerId() {
		return drinkerId;
	}
	public void setDrinkerId(int drinkerId) {
		this.drinkerId = drinkerId;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
	
}
