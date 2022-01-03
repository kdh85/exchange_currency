package com.exchange.currency.exchange_currency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exchange.currency.exchange_currency.api.domain.ReceiptNation;
import com.exchange.currency.exchange_currency.api.domain.RemittanceNation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ExchangeViewController {

	@GetMapping("/response-exchange-view")
	public String exchangeView(Model model) {

		model.addAttribute("remittanceNationInfo", RemittanceNation.remittanceNationNameWithCurrency("미국"));
		model.addAttribute("receiptNationInfo", ReceiptNation.receiptNationsNameWithCurrency());

		return "exchange/exchange-view";
	}

}
