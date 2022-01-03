package com.exchange.currency.exchange_currency.api.domain;

import java.util.Objects;

import com.exchange.currency.exchange_currency.api.dto.ExchangeRateDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExchangeRateInfo {

	private final Amount exchangeAmount;
	private final MonetaryUnit receiptMonetaryUnit;
	private final MonetaryUnit remittanceMonetaryUnit;

	public ExchangeRateInfo(Double exchangeAmount, String receiptMonetaryUnit, String remittanceMonetaryUnit) {
		this(new Amount(exchangeAmount), new MonetaryUnit(receiptMonetaryUnit),
			new MonetaryUnit(remittanceMonetaryUnit));
	}

	@Builder
	public ExchangeRateInfo(Amount exchangeAmount, MonetaryUnit receiptMonetaryUnit,
							MonetaryUnit remittanceMonetaryUnit) {
		this.exchangeAmount         = exchangeAmount;
		this.receiptMonetaryUnit    = receiptMonetaryUnit;
		this.remittanceMonetaryUnit = remittanceMonetaryUnit;
	}

	public ExchangeRateDto createExchangeDto() {
		return new ExchangeRateDto(exchangeAmount.getAmount(),
			receiptMonetaryUnit.exchangeUnit(remittanceMonetaryUnit));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ExchangeRateInfo that = (ExchangeRateInfo)o;
		return Objects.equals(exchangeAmount, that.exchangeAmount) && Objects.equals(
			receiptMonetaryUnit, that.receiptMonetaryUnit) && Objects.equals(remittanceMonetaryUnit,
			that.remittanceMonetaryUnit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(exchangeAmount, receiptMonetaryUnit, remittanceMonetaryUnit);
	}

	@Override
	public String toString() {
		return "ExchangeRate{" +
			   "exchangeAmount=" + exchangeAmount +
			   ", receiptMonetaryUnit=" + receiptMonetaryUnit +
			   ", remittanceMonetaryUnit=" + remittanceMonetaryUnit +
			   '}';
	}
}
