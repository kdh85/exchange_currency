package com.exchange.currency.exchange_currency.api.service;

import static java.util.Optional.*;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.exchange.currency.exchange_currency.api.domain.ExchangeRateInfo;
import com.exchange.currency.exchange_currency.api.dto.CurrencyDto;

import lombok.extern.slf4j.Slf4j;
import reactor.util.retry.Retry;

@Slf4j
@Service
public class SearchCurrencyService {

	public static final String BASE_URL = "http://api.currencylayer.com/";
	public static final String ACCESS_KEY = "209154726d9b40490bce6e79d867cfcb";
	public static final String ENDPOINT = "live";

	private final WebClient webClient;

	public SearchCurrencyService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY).build();
	}

	public ExchangeRateInfo getCurrencyInfos(String targetSource) {

		log.debug("getCurrencyInfos currencies={}", targetSource);
		return createCurrencyInfo(targetSource).toExchangeRateInfo(targetSource);
	}

	private CurrencyDto createCurrencyInfo(String targetSource) {
		return this.webClient
			.get()
			.uri(uriBuilder -> uriBuilder
				.queryParamIfPresent("currencies", ofNullable(targetSource))
				.build()
			)
			.retrieve()
			.onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
				, clientResponse -> clientResponse.bodyToMono(String.class).map(RuntimeException::new))
			.bodyToMono(CurrencyDto.class)
			.delayElement(Duration.ofMillis(2000))
			.retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
			.block();
	}
}
