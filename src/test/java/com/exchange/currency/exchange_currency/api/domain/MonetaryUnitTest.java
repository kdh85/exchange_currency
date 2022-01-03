package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class MonetaryUnitTest {
	@Test
	void 화폐단위_생성_테스트() {
		assertThat(new MonetaryUnit("KRW")).isEqualTo(new MonetaryUnit("KRW"));
	}

	@ParameterizedTest
	@CsvSource(value = {"!@#, 123#"})
	@NullAndEmptySource
	void 입력값_검증_테스트(String monetaryUnit) {
		assertThatThrownBy(
			() -> new MonetaryUnit(monetaryUnit)
		).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"KRW,USD,KRW/USD"})
	void 환율단위_테스트(String receiptMonetaryUnit, String remittanceMonetaryUnit, String result) {
		assertThat(
			new MonetaryUnit(receiptMonetaryUnit).exchangeUnit(new MonetaryUnit(remittanceMonetaryUnit))).isEqualTo(
			result);
	}
}