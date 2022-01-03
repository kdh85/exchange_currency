package com.exchange.currency.exchange_currency.api.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MonetaryUnit {

	private static final String SEPARATOR = "/";
	private static final String ERROR_WRONG_MONETARY_UNIT = "금액 단위가 올바르지 않습니다.";
	private static final String PATTERN_ONLY_ENGLISH = "^[A-Z]*$";

	private final String monetaryUnit;

	@Builder
	public MonetaryUnit(String monetaryUnit) {
		validationUnit(monetaryUnit);
		this.monetaryUnit = monetaryUnit;
	}

	private void validationUnit(String monetaryUnit) {
		if (isEmptyValue(monetaryUnit) || isNotMatchEnglishToUppercase(monetaryUnit)) {
			throw new IllegalArgumentException(ERROR_WRONG_MONETARY_UNIT);
		}
	}

	private boolean isNotMatchEnglishToUppercase(String monetaryUnit) {
		return !Pattern.matches(PATTERN_ONLY_ENGLISH, monetaryUnit);
	}

	private boolean isEmptyValue(String monetaryUnit) {
		return monetaryUnit == null || monetaryUnit.isEmpty();
	}

	public String exchangeUnit(MonetaryUnit remittanceMonetaryUnit) {
		return this.monetaryUnit + SEPARATOR + remittanceMonetaryUnit.getMonetaryUnit();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MonetaryUnit that = (MonetaryUnit)o;
		return Objects.equals(monetaryUnit, that.monetaryUnit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(monetaryUnit);
	}

	@Override
	public String toString() {
		return "MonetaryUnit{" +
			   "monetaryUnit='" + monetaryUnit + '\'' +
			   '}';
	}
}
