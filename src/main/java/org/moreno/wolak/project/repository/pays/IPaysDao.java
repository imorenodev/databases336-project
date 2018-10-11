package org.moreno.wolak.project.repository.pays;

import java.util.List;

import org.moreno.wolak.project.dtos.IssuesResponseDto;
import org.moreno.wolak.project.dtos.PaysRequestDto;
import org.moreno.wolak.project.dtos.PaysResponseDto;

public interface IPaysDao {

	List<PaysResponseDto> getAllPaymentsByDrinkers();
	List<PaysResponseDto> getAllPaymentsByDrinkerById(int drinkerId);
	PaysResponseDto createPaymentByDrinker(int drinkerId, PaysRequestDto pays);
	PaysResponseDto deletePaymentByDrinkerById(int drinkerId, int billId);
	PaysResponseDto getPaymentByDrinkerById(int drinkerId, int billId);
	PaysResponseDto updatePaymentByDrinkerById(int drinkerId, int billId, PaysRequestDto pays);

}
