package org.moreno.wolak.project.repository.likes;

import java.util.List;

import org.moreno.wolak.project.dtos.DrinkerDto;
import org.moreno.wolak.project.dtos.ItemDto.ItemType;
import org.moreno.wolak.project.dtos.LikesRequestDto;
import org.moreno.wolak.project.dtos.LikesResponseDto;

public interface ILikesDao {
	
	List<LikesResponseDto> getAllItemsLikedByDrinker(int drinkerId);
	List<LikesResponseDto> getAllItemsOfTypeLikedByDrinker(int drinkerId, ItemType type);
	LikesResponseDto getItemLikedByDrinker(int drinkerId, int itemId);
	LikesResponseDto deleteItemLikedByDrinker(int drinkerId, int itemId);
	LikesResponseDto createItemLikedByDrinker(int drinkerId, LikesRequestDto sells);
	List<DrinkerDto> getAllDrinkersWhoLikeItemById(int itemId);

}
