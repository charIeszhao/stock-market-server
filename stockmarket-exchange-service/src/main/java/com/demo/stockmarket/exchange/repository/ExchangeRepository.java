package com.demo.stockmarket.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

}
