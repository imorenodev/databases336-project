package org.moreno.wolak.project.repository.bills;

import java.util.List;

import org.moreno.wolak.project.dtos.BillDto;

public interface IBillsDao {
	BillDto getBillById(int billId);
	BillDto updateBillById(int billId, BillDto bill);
	int deleteBillById(int billId);
	List<BillDto> getAllBills();
	BillDto createBill(BillDto bill);
}
