package com.exchange.currency.exchange_currency.api.domain;

import java.util.Arrays;

import com.exchange.currency.exchange_currency.api.dto.NationDto;

import lombok.Getter;

@Getter
public enum RemittanceNation {

	USA("미국", "USD");

	private static final String MSG_ERROR_FIND_NATION = "일치하는 국가가 없습니다.";

	private final String name;
	private final String monetaryUnit;

	RemittanceNation(String name, String monetaryUnit) {
		this.name         = name;
		this.monetaryUnit = monetaryUnit;
	}

	public static NationDto remittanceNationNameWithCurrency(String remittanceMonetary) {
		return Arrays.stream(RemittanceNation.values())
			.filter(receiptNation -> receiptNation.getName().equals(remittanceMonetary))
			.map(nation -> new NationDto(nation.getName(), nation.getMonetaryUnit()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(MSG_ERROR_FIND_NATION));
	}
}
