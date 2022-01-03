package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AmountTest {
	@Test
	void 금액을_생성_테스트() {
		assertThat(new Amount(10D)).isEqualTo(new Amount(10D));
	}

	@Test
	void 입력값_검증_테스트() {
		assertThatThrownBy(
			() -> new Amount(-10D)
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 금액계산_테스트() {
		assertThat(new Amount(10D).multiplyAmount(new Amount(20D))).isEqualTo(new Amount(200D));
	}
}