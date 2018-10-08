package org.moreno.wolak.project.repository.items.beers;

import java.util.List;

import org.moreno.wolak.project.dtos.BeerDto;

public interface IBeersDao {
	BeerDto getBeerById(int beerId);
	List<BeerDto> getAllBeers();
	BeerDto createBeer(BeerDto beer);
}
