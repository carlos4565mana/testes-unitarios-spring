package com.carlos.beerstock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {

	LAGER("Lager"),
	MALZEBIER("Malzebier"),
	WITBIER("Witbier"),
    WEISS("Weiss"),
    ALE("Ale"),
    IPA("IPA"),
    STOUT("Stout");
	
	BeerType(String string) {
		this.description = "";
		// TODO Auto-generated constructor stub
	}

	private final String description;

}
