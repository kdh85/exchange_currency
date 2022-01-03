package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exchange.currency.exchange_currency.api.dto.ReceiptDto;

class ReceiptInfoTest {

	@ParameterizedTest
	@CsvSource(value = {"100, KRW", "200, JPY", "5000, PHP"})
	void 수취정보_생성_테스트(Double amount, String unit) {
		assertThat(ReceiptInfo.builder()
			.receiptAmount(new Amount(amount))
			.receiptMonetaryUnit(new MonetaryUnit(unit))
			.build()
		).isEqualTo(new ReceiptInfo(amount, unit));
	}

	@ParameterizedTest
	@CsvSource(value = {"100, KRW", "200, JPY", "5000, PHP"})
	void 수취정보데이터오브젝트_생성_테스트(Double amount, String unit) {

		ReceiptInfo receiptInfo = ReceiptInfo.builder()
			.receiptAmount(new Amount(amount))
			.receiptMonetaryUnit(new MonetaryUnit(unit))
			.build();

		assertThat(receiptInfo.createReceiptDto()).isEqualTo(new ReceiptDto(amount, unit));
	}
}