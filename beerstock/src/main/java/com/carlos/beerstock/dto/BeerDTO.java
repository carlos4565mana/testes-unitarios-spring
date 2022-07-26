package com.carlos.beerstock.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.carlos.beerstock.enums.BeerType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BeerDTO {

	private Long id;
	
	
	@NotNull
	@Size(min = 1, max = 200)
	private String name;
	
	
	@NotNull
	@Size(min = 1, max = 200)
	private String brand;
	
	
	@NotNull
	@Max(500)
	private Integer max;
	
	
	@NotNull
	@Max(100)
	private Integer quantity;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private BeerType type;


	public String getName() {
		return name;
	}



	

	
}
