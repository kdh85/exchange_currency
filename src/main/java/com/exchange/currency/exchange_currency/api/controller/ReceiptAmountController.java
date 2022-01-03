package com.exchange.currency.exchange_currency.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.currency.exchange_currency.api.dto.ExchangeRateForm;
import com.exchange.currency.exchange_currency.api.dto.ReceiptDto;
import com.exchange.currency.exchange_currency.api.service.ReceiptAmountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ReceiptAmountController {

	private final ReceiptAmountService receiptAmountService;

	@PostMapping("/currency/exchange/api/calculate-receipt-amount")
	@ResponseBody
	public ReceiptDto calculateAmount(@RequestBody ExchangeRateForm exchangeForm) {
		return receiptAmountService.calculateAmount(exchangeForm);
	}
}
