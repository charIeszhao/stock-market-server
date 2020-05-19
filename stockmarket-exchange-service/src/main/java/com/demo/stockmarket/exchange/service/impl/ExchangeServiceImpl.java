package com.demo.stockmarket.exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.entity.Exchange;
import com.demo.stockmarket.exchange.repository.ExchangeRepository;
import com.demo.stockmarket.exchange.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {
	
	@Autowired
	private ExchangeRepository exchangeRepository;

	@Override
	public Page<Exchange> findAllExchanges(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return exchangeRepository.findAll(pageable);
	}

	@Override
	public Exchange addExchange(Exchange exchange) {
		return exchangeRepository.save(exchange);
	}

	@Override
	public Exchange updateExchange(Exchange exchange, int id) {
		Exchange oldExchange = exchangeRepository.findById(id).get();
		if (oldExchange != null) {
			return exchangeRepository.save(exchange);
		} else {
			return null;
		}
		
	}

	@Override
	public void deleteExchange(int id) {
		exchangeRepository.deleteById(id);
	}

}
