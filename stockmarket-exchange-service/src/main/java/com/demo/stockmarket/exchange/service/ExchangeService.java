package com.demo.stockmarket.exchange.service;

import org.springframework.data.domain.Page;

import com.demo.stockmarket.entity.Exchange;

public interface ExchangeService {

	public Page<Exchange> findAllExchanges(int page, int pageSize);

	public Exchange addExchange(Exchange exchange);

	public Exchange updateExchange(Exchange exchange, int id);

	public void deleteExchange(int id);

}
