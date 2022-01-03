package com.exchange.currency.exchange_currency.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.currency.exchange_currency.api.domain.ExchangeRateInfo;
import com.exchange.currency.exchange_currency.api.dto.ExchangeRateDto;
import com.exchange.currency.exchange_currency.api.dto.ExchangeRateForm;
import com.exchange.currency.exchange_currency.api.service.SearchCurrencyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class SearchCurrencyApiController {

	private final SearchCurrencyService currencyService;

	@PostMapping("/currency/exchange/api/realtime-info")
	public ExchangeRateDto callRealtimeCurrencyInfo(@RequestBody ExchangeRateForm exchangeForm) {
		ExchangeRateInfo exchangeRate = currencyService.getCurrencyInfos(exchangeForm.getRemittanceMonetary());
		log.debug("currencyInfos = {}", exchangeRate);
		return exchangeRate.createExchangeDto();
	}
}
