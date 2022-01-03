package com.exchange.currency.exchange_currency.api.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.exchange.currency.exchange_currency.api.dto.NationDto;

class ReceiptNationTest {
	@Test
	void 수취국가별_국가명목록_테스트() {
		assertAll(
			() -> assertThat(ReceiptNation.KOREA.getName()).isEqualTo("한국"),
			() -> assertThat(ReceiptNation.JAPAN.getName()).isEqualTo("일본"),
			() -> assertThat(ReceiptNation.PHILIPPINES.getName()).isEqualTo("필리핀")
		);
	}

	@Test
	void 수취국가별_화폐단위목록_테스트() {
		assertAll(
			() -> assertThat(ReceiptNation.KOREA.getMonetaryUnit()).isEqualTo("KRW"),
			() -> assertThat(ReceiptNation.JAPAN.getMonetaryUnit()).isEqualTo("JPY"),
			() -> assertThat(ReceiptNation.PHILIPPINES.getMonetaryUnit()).isEqualTo("PHP")
		);
	}

	@Test
	void 수취국가_화폐단위_조합_테스트() {
		List<NationDto> nationDtos = ReceiptNation.receiptNationsNameWithCurrency();

		assertAll(
			() -> assertThat(nationDtos.get(0).getDisplayName()).isEqualTo("한국(KRW)"),
			() -> assertThat(nationDtos.get(1).getDisplayName()).isEqualTo("일본(JPY)"),
			() -> assertThat(nationDtos.get(2).getDisplayName()).isEqualTo("필리핀(PHP)")
		);
	}
}