package com.exchange.currency.exchange_currency.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExchangeRateDto {

	private final Double exchangeAmount;
	private final String exchangeMonetaryUnit;

	@Builder
	public ExchangeRateDto(Double exchangeAmount, String exchangeMonetaryUnit) {
		this.exchangeAmount       = exchangeAmount;
		this.exchangeMonetaryUnit = exchangeMonetaryUnit;
	}
}
