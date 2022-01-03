package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.exchange.currency.exchange_currency.api.dto.NationDto;

class RemittanceNationTest {
	@Test
	void 송금국가별_국가명목록_테스트() {
		assertThat(RemittanceNation.USA.getName()).isEqualTo("미국");
	}

	@Test
	void 송금국가별_화폐단위목록_테스트() {
		assertThat(RemittanceNation.USA.getMonetaryUnit()).isEqualTo("USD");
	}

	@Test
	void 송금국가별_국가명_화폐조합_테스트() {
		assertThat(RemittanceNation.remittanceNationNameWithCurrency("미국")).isEqualTo(new NationDto("미국", "USD"));
	}
}