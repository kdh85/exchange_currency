package com.exchange.currency.exchange_currency.api.domain;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Amount {

	private static final int DEFAULT_VALUE = 0;
	private static final String ERROR_MSG_AMOUNT = String.format("금액이 %d 미만입니다.", DEFAULT_VALUE);

	private final Double amount;

	@Builder
	public Amount(Double amount) {
		validationAmountValue(amount);
		this.amount = amount;
	}

	private void validationAmountValue(Double amount) {
		if (amount < DEFAULT_VALUE) {
			throw new IllegalArgumentException(ERROR_MSG_AMOUNT);
		}
	}

	public Amount multiplyAmount(Amount targetAmount) {
		return new Amount(multiplication(targetAmount));
	}

	private double multiplication(Amount targetAmount) {
		return this.amount * targetAmount.amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Amount amount1 = (Amount)o;
		return Objects.equals(amount, amount1.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public String toString() {
		return "Amount{" +
			   "amount=" + amount +
			   '}';
	}
}
