package com.carlos.beerstock.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carlos.beerstock.dto.BeerDTO;
import com.carlos.beerstock.entity.Beer;
import com.carlos.beerstock.exception.BeerAlreadyRegisteredException;
import com.carlos.beerstock.exception.BeerNotFoundException;
import com.carlos.beerstock.exception.BeerStockExceededException;
import com.carlos.beerstock.mapper.BeerMapper;
import com.carlos.beerstock.repository.BeerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BeerService {

	private final BeerRepository beerRepository = null;
	private final BeerMapper beerMapper = BeerMapper.INSTANCE;

	public BeerDTO createBeet(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {

		verifyIfIsAlreadyRegistered(beerDTO.getName());
		Beer beer = beerMapper.toModel(beerDTO);
		Beer savedBeer = beerRepository.save(beer);
		return beerMapper.toDTO(savedBeer);

	}

	public BeerDTO findByName(String name) throws BeerNotFoundException {

		Beer foundBeer = beerRepository.findByName(name).orElseThrow(() -> new BeerNotFoundException(name));
		return beerMapper.toDTO(foundBeer);

	}

	public List<BeerDTO> listAll() {
		return beerRepository.findAll().stream().map(beerMapper::toDTO).collect(Collectors.toList());
	}

	public void deleteById(Long id) throws BeerNotFoundException {

		verifyIfExists(id);
		beerRepository.deleteById(id);

	}

	private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {

		Optional<Beer> optSavedBeer = beerRepository.findByName(name);
		if (optSavedBeer.isPresent()) {
			throw new BeerAlreadyRegisteredException(name);
		}
	}

	private Beer verifyIfExists(Long id) throws BeerNotFoundException  {

		return beerRepository.findById(id)
				.orElseThrow(() -> new BeerNotFoundException(id));

	}
	
	public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        Beer beerToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
            Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new BeerStockExceededException(id, quantityToIncrement);
    }

	
	
	

}
