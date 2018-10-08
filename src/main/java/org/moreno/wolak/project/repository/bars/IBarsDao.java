package org.moreno.wolak.project.repository.bars;

import java.util.List;

import org.moreno.wolak.project.dtos.BarDto;

public interface IBarsDao {
	BarDto getBarById(int barId);
	BarDto updateBarById(int barId, BarDto bar);
	int deleteBarById(int barId);
	List<BarDto> getAllBars();
	BarDto createBar(BarDto bar);
}
