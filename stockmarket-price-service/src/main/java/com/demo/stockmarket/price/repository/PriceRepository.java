package com.demo.stockmarket.price.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

	@Query(value = "select * from t_price p where p.company_id = ? and p.date_time >= ? and p.date_time < ?", nativeQuery = true)
	List<Price> findAllByDatesBetween(int companyId, Date from, Date to);

}
