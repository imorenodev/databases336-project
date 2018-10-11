package org.moreno.wolak.project.repository.issues;

import java.util.List;

import org.moreno.wolak.project.dtos.IssuesResponseDto;

public interface IIssuesDao {

	List<IssuesResponseDto> getAllBillsIssuedByBars();
	List<IssuesResponseDto> getAllBillsIssuedByBarById(int barId);
	IssuesResponseDto createBillIssuedByBar(int barId, int billId);
	IssuesResponseDto getBillIssuedByBarById(int barId, int billId);
	IssuesResponseDto deleteBillIssuedByBar(int barId, int billId);

}
