package com.demo.stockmarket.price.servcie;

import java.util.Date;
import java.util.List;

import com.demo.stockmarket.entity.Price;

public interface PriceService {
	
	public List<Price> getPricesByCompanyId(int companyId, Date fromDate, Date toDate);
	
	public Price addPrice(int companyId, double price);
	
	public Price updatePrice(int companyId, double price, Date date);
	
	public void deletePrice(int id);

}
