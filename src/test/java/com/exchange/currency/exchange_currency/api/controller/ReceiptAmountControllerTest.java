package com.exchange.currency.exchange_currency.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.exchange.currency.exchange_currency.api.dto.ExchangeRateForm;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReceiptAmountControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(context)
			.build();
	}

	@Test
	void 수취금액_api_통신_테스트() throws Exception {

		String url = "http://localhost:" + port + "/currency/exchange/api/calculate-receipt-amount";

		ExchangeRateForm exchangeRateForm = ExchangeRateForm.builder()
			.remittanceMonetary("KRW")
			.exchangeAmount(1000D)
			.exchangeMonetaryUnit("KRW/USD")
			.transferAmount(2200D)
			.receiptMonetary("USD")
			.build();

		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(exchangeRateForm))
			).andExpect(status().isOk())
			.andExpect(jsonPath("$.[?(@.receiptAmount == '%s')]", "2200000.0").exists())
			.andExpect(jsonPath("$.[?(@.receiptMonetaryUnit == '%s')]", "USD").exists())
			.andDo(print());
	}
}