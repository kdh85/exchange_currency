package com.exchange.currency.exchange_currency.api.dto;

import com.exchange.currency.exchange_currency.api.domain.Amount;
import com.exchange.currency.exchange_currency.api.domain.MonetaryUnit;
import com.exchange.currency.exchange_currency.api.domain.ReceiptInfo;

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

	public Amount toTransferAmount() {
		return Amount.builder()
			.amount(transferAmount)
			.build();
	}

	public Amount toExchangeAmount() {
		return Amount.builder()
			.amount(exchangeAmount)
			.build();
	}

	public MonetaryUnit toTransferMonetaryUnit() {
		return MonetaryUnit.builder()
			.monetaryUnit(receiptMonetary)
			.build();
	}

	public ReceiptInfo toReceiptInfo() {
		return ReceiptInfo.builder()
			.receiptAmount(toExchangeAmount().multiplyAmount(toTransferAmount()))
			.receiptMonetaryUnit(toTransferMonetaryUnit())
			.build();
	}
}
