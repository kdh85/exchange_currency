package com.exchange.currency.exchange_currency.api.domain;

import java.util.Objects;

import com.exchange.currency.exchange_currency.api.dto.ReceiptDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceiptInfo {

	private final Amount receiptAmount;
	private final MonetaryUnit receiptMonetaryUnit;

	@Builder
	public ReceiptInfo(Amount receiptAmount, MonetaryUnit receiptMonetaryUnit) {
		this.receiptAmount       = receiptAmount;
		this.receiptMonetaryUnit = receiptMonetaryUnit;
	}

	public ReceiptInfo(double receiptAmount, String receiptMonetaryUnit) {
		this(new Amount(receiptAmount), new MonetaryUnit(receiptMonetaryUnit));
	}

	public ReceiptDto createReceiptDto() {
		return new ReceiptDto(receiptAmount.getAmount(), receiptMonetaryUnit.getMonetaryUnit());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ReceiptInfo that = (ReceiptInfo)o;
		return Objects.equals(receiptAmount, that.receiptAmount) && Objects.equals(receiptMonetaryUnit,
			that.receiptMonetaryUnit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(receiptAmount, receiptMonetaryUnit);
	}

	@Override
	public String toString() {
		return "ReceiptInfo{" +
			   "receiptAmount=" + receiptAmount +
			   ", receiptMonetaryUnit=" + receiptMonetaryUnit +
			   '}';
	}
}
