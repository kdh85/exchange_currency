package com.exchange.currency.exchange_currency.api.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.exchange.currency.exchange_currency.api.dto.NationDto;

import lombok.Getter;

@Getter
public enum ReceiptNation {

	KOREA("한국", "KRW"),
	JAPAN("일본", "JPY"),
	PHILIPPINES("필리핀", "PHP");

	private final String name;
	private final String monetaryUnit;

	ReceiptNation(String name, String monetaryUnit) {
		this.name         = name;
		this.monetaryUnit = monetaryUnit;
	}

	public static List<NationDto> receiptNationsNameWithCurrency() {
		return Arrays.stream(ReceiptNation.values())
			.map(nation -> new NationDto(nation.getName(), nation.getMonetaryUnit()))
			.collect(Collectors.toList());
	}
}
