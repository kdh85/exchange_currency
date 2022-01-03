package com.exchange.currency.exchange_currency.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExchangeRateForm {
	private final String remittanceMonetary;
	private final Double exchangeAmount;
	private final String exchangeMonetaryUnit;
	private final Double transferAmount;
	private final String receiptMonetary;

	@Builder
	public ExchangeRateForm(String remittanceMonetary, Double exchangeAmount, String exchangeMonetaryUnit,
							Double transferAmount, String receiptMonetary) {
		this.remittanceMonetary   = remittanceMonetary;
		this.exchangeAmount       = exchangeAmount;
		this.exchangeMonetaryUnit = exchangeMonetaryUnit;
		this.transferAmount       = transferAmount;
		this.receiptMonetary      = receiptMonetary;
	}
}
