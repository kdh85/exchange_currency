package com.exchange.currency.exchange_currency.api.dto;

import java.util.Map;

import com.exchange.currency.exchange_currency.api.domain.Amount;
import com.exchange.currency.exchange_currency.api.domain.ExchangeRateInfo;
import com.exchange.currency.exchange_currency.api.domain.MonetaryUnit;

import lombok.Builder;
import lombok.Data;

@Data
public class CurrencyDto {

	private static final String MSG_ERROR_EMPTY_CURRENCY = "환율 정보가 없습니다.";

	private static final String DECIMAL_POINT_PATTEN = "#.##";

	private boolean success;

	private String terms;

	private String privacy;

	private String timestamp;

	private String source;

	private Map<String, Object> quotes;

	private String targetSource;

	@Builder
	public CurrencyDto(boolean success, String terms, String privacy, String timestamp, String source,
					   Map<String, Object> quotes) {
		this.success   = success;
		this.terms     = terms;
		this.privacy   = privacy;
		this.timestamp = timestamp;
		this.source    = source;
		this.quotes    = quotes;
	}

	private Amount createExchangeRateAmount() {
		return this.quotes.entrySet()
			.stream()
			.map(stringObjectEntry -> new Amount((Double)stringObjectEntry.getValue()))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(MSG_ERROR_EMPTY_CURRENCY));
	}

	public ExchangeRateInfo toExchangeRateInfo(String targetSource) {
		return ExchangeRateInfo.builder()
			.exchangeAmount(createExchangeRateAmount())
			.receiptMonetaryUnit(new MonetaryUnit(targetSource))
			.remittanceMonetaryUnit(new MonetaryUnit(this.source))
			.build();
	}
}
