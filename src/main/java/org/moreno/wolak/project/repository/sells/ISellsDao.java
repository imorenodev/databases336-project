package org.moreno.wolak.project.repository.sells;

import java.util.List;

import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.SellsRequestDto;
import org.moreno.wolak.project.dtos.SellsResponseDto;

public interface ISellsDao {
	
	List<SellsResponseDto> getAllItemsSoldByBar(int barId);
	List<SellsResponseDto> getAllItemsOfTypeSoldByBar(int barId, ItemType type);
	SellsResponseDto getItemSoldByBar(int barId, int itemId);
	SellsResponseDto deleteItemSoldByBar(int barId, int itemId);
	SellsResponseDto createItemSoldByBar(int barId, SellsRequestDto sells);
	SellsResponseDto updateItemSoldByBar(int barId, int itemId, SellsRequestDto sells);
	
}
