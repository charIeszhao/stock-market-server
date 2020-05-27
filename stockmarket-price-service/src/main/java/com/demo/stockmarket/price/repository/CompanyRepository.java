package com.demo.stockmarket.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
