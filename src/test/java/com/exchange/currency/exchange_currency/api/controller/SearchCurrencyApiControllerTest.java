package com.exchange.currency.exchange_currency.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
class SearchCurrencyApiControllerTest {

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

	@ParameterizedTest
	@CsvSource(value = {"KRW, KRW/USD", "JPY, JPY/USD", "PHP, PHP/USD"})
	void 환율정보_api_통신_테스트(String remittanceMonetary, String exchangeMonetaryUnit) throws Exception {

		String url = "http://localhost:" + port + "/currency/exchange/api/realtime-info";

		ExchangeRateForm exchangeRateForm = ExchangeRateForm.builder()
			.remittanceMonetary(remittanceMonetary)
			.exchangeAmount(0D)
			.exchangeMonetaryUnit(exchangeMonetaryUnit)
			.transferAmount(0D)
			.receiptMonetary("USD")
			.build();

		mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(exchangeRateForm))
			).andExpect(status().isOk())
			.andExpect(jsonPath("$.[?(@.exchangeMonetaryUnit == '%s')]", exchangeMonetaryUnit).exists())
			.andDo(print());
	}
}