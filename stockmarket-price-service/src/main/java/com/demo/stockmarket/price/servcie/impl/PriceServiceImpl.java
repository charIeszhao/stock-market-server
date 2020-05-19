package com.demo.stockmarket.price.servcie.impl;

import java.util.Date;
import java.util.List;

import com.demo.stockmarket.entity.Price;
import com.demo.stockmarket.price.servcie.PriceService;

public class PriceServiceImpl implements PriceService {

	@Override
	public List<Price> getPricesByCompanyId(int companyId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Price addPrice(int companyId, double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Price updatePrice(int companyId, double price, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrice(int id) {
		// TODO Auto-generated method stub

	}
}
