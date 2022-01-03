package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.exchange.currency.exchange_currency.api.dto.ExchangeRateDto;

class ExchangeRateInfoTest {

	@Test
	void 환율정보_생성_테스트() {
		assertThat(ExchangeRateInfo.builder()
			.exchangeAmount(new Amount(100d))
			.receiptMonetaryUnit(new MonetaryUnit("KRW"))
			.remittanceMonetaryUnit(new MonetaryUnit("USD"))
			.build()
		).isEqualTo(new ExchangeRateInfo(100d, "KRW", "USD"));
	}

	@Test
	void 환율데이터오브젝트_생성_테스트() {
		assertThat(new ExchangeRateInfo(100d, "KRW", "USD").createExchangeDto())
			.isEqualTo(new ExchangeRateDto(100d, "KRW/USD"));
	}
}