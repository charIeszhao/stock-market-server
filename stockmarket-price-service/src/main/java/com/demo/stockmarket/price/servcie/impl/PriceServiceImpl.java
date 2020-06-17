package com.demo.stockmarket.price.servcie.impl;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.entity.Price;
import com.demo.stockmarket.price.model.PriceHistory;
import com.demo.stockmarket.price.model.SectorPrice;
import com.demo.stockmarket.price.repository.CompanyRepository;
import com.demo.stockmarket.price.repository.PriceRepository;
import com.demo.stockmarket.price.servcie.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Price> getCompanyStockPrices(int companyId, Date from, Date to) {
		if (from == null || to == null) {
			return priceRepository.findAllByCompanyId(companyId);
		} else {
			if (from.equals(to)) {
				return this.getCompanyStockPricesByDate(companyId, from);
			} else {
				return priceRepository.findAllByDatesBetween(companyId, from, to);
			}
		}
	}

	@Override
	public List<Price> getCompanyStockPricesByDate(int companyId, Date date) {
		LocalDate from = convertDateToLocalDate(date);
		Date to = convertLocalDateToDate(from.plusDays(1));
		return priceRepository.findAllByDatesBetween(companyId, date, to);
	}

	@Override
	public PriceHistory getCompanyStockHistory(int companyId) {
		List<Price> prices = priceRepository.getLatestAndPriorPrices(companyId);
		PriceHistory history = new PriceHistory();
		history.setCompanyId(prices.get(0).getCompany().getId());
		history.setCompanyName(prices.get(0).getCompany().getName());
		history.setLatestPrice(prices.get(0).getPrice());
		history.setPriorPrice(prices.get(prices.size() - 1).getPrice());
		return history;
	}

	@Override
	public List<Price> importFromExcel(InputStream stream) throws RuntimeException {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(stream);
			Iterator<Sheet> sheetIterator = workbook.iterator();

			List<Price> importedPrices = new ArrayList<Price>();

			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() == 0) {
						continue;
					}

					Price entry = new Price();

					int companyId = (int) row.getCell(0).getNumericCellValue();
					if (companyRepository.existsById(companyId)) {
						entry.setCompany(companyRepository.findById(companyId).get());
					} else {
						throw new RuntimeException("Company does not exist!");
					}

					entry.setPrice(row.getCell(1).getNumericCellValue());

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String date = dateFormat.format(row.getCell(2).getDateCellValue());
					String time = row.getCell(3).getStringCellValue();

					DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					entry.setDateTime(dateTimeFormat.parse(date + " " + time));

					importedPrices.add(entry);
				}
			}

			if (!importedPrices.isEmpty()) {
				Iterable<Price> iterable = importedPrices;
				importedPrices = priceRepository.saveAll(iterable);
			}

			if (workbook != null) {
				workbook.close();
			}
			stream.close();

			return importedPrices;

		} catch (Exception e) {
			throw new RuntimeException("Failed to import data from excel.");
		}
	}

	@Override
	public Price addPrice(int companyId, double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Price updatePrice(int companyId, Price price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrice(int id) {
		// TODO Auto-generated method stub

	}

	private LocalDate convertDateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private Date convertLocalDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public List<SectorPrice> getSectorPrices() {
		List<SectorPrice> prices = new ArrayList<SectorPrice>();
		prices.add(new SectorPrice("Energy", -0.46));
		prices.add(new SectorPrice("Financial", 0.33));
		prices.add(new SectorPrice("Health Care	", 3.31));
		prices.add(new SectorPrice("Industrial", 1.89));
		prices.add(new SectorPrice("Information Technology", 4.17));
		return prices;
	}
}
