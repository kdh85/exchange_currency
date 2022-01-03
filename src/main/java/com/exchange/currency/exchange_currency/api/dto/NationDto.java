package com.exchange.currency.exchange_currency.api.dto;

import lombok.Data;

@Data
public class NationDto {

	private final String nationName;
	private final String monetaryUnit;

	public String getDisplayName() {
		return nationName + "(" + monetaryUnit + ")";
	}
}
