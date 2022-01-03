package com.exchange.currency.exchange_currency.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReceiptDto {

	private final Double receiptAmount;
	private final String receiptMonetaryUnit;
}
