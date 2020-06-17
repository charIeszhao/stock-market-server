package com.demo.stockmarket.price.servcie;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.demo.stockmarket.entity.Price;
import com.demo.stockmarket.price.model.PriceHistory;
import com.demo.stockmarket.price.model.SectorPrice;

public interface PriceService {
	
	public List<Price> getCompanyStockPrices(int companyId, Date fromDate, Date toDate);
	
	public List<Price> getCompanyStockPricesByDate(int companyId, Date date);

	public PriceHistory getCompanyStockHistory(int companyId);

	public List<SectorPrice> getSectorPrices();
	
	public List<Price> importFromExcel(InputStream stream) throws IOException, ParseException;
	
	public Price addPrice(int companyId, double price);
	
	public Price updatePrice(int companyId, Price price);
	
	public void deletePrice(int id);

}
