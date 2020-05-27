package com.demo.stockmarket.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

}
