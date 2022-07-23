package com.carlos.beerstock.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class QuantityDTO {
	@NotNull
	@Max(100)
	private Integer quantity;

}
