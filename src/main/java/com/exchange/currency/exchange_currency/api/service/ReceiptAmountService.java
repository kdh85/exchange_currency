package com.exchange.currency.exchange_currency.api.service;

import org.springframework.stereotype.Service;

import com.exchange.currency.exchange_currency.api.dto.ExchangeRateForm;
import com.exchange.currency.exchange_currency.api.dto.ReceiptDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReceiptAmountService {
	public ReceiptDto calculateAmount(ExchangeRateForm exchangeForm) {
		log.debug("exchangeForm = {}", exchangeForm);

		return exchangeForm.toReceiptInfo().createReceiptDto();
	}
}
