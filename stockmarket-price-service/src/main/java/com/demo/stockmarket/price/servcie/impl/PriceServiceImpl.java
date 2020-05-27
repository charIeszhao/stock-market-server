package com.demo.stockmarket.price.servcie.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<Price> getPricesByCompanyId(int companyId, Date fromDate, Date toDate) {
		return null;
	}

	@Override
	public List<Price> importFromExcel(InputStream stream) throws IOException, RuntimeException, ParseException {
		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		Iterator<Sheet> sheetIterator = workbook.iterator();
		
		List<Price> importedPrices = new ArrayList<Price>();
		
		while(sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				
				Price entry = new Price();
				
//				int companyId = Integer.parseInt(row.getCell(0).getStringCellValue());
				int companyId = (int) row.getCell(0).getNumericCellValue();
				if (companyRepository.existsById(companyId)) {
					entry.setCompany(companyRepository.findById(companyId).get());
				} else {
					throw new RuntimeException("Company does not exist!");
				}
				
				entry.setPrice(row.getCell(1).getNumericCellValue());
				entry.setDate(row.getCell(2).getDateCellValue());
				
				DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
				entry.setTime(dateFormat.parse(row.getCell(3).getStringCellValue()));
				
				importedPrices.add(entry);
				// For each row, iterate through all the columns
//				Iterator<Cell> cellIterator = row.cellIterator();

//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					switch (cell.getCellType()) {
//					case NUMERIC:
//						System.out.print(cell.getNumericCellValue() + "t");
//						break;
//					case STRING:
//						System.out.print(cell.getStringCellValue() + "t");
//						break;
//					default:
//						break;
//					}
//				}
//				System.out.println("");
			}
		}
		
		if (!importedPrices.isEmpty()) {
			Iterable<Price> iterable = importedPrices;
			importedPrices = priceRepository.saveAll(iterable);
		}
		
		workbook.close();
		stream.close();
		return importedPrices;

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
}
