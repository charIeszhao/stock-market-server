package com.demo.stockmarket.price.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.entity.Price;
import com.demo.stockmarket.price.model.PriceHistory;
import com.demo.stockmarket.price.model.SectorPrice;
import com.demo.stockmarket.price.response.ImportResponse;
import com.demo.stockmarket.price.servcie.PriceService;

@RestController
@RequestMapping("/price")
public class PriceController {

	@Autowired
	private PriceService priceService;

	@GetMapping("/{companyId}")
	public List<Price> getPrices(
			@PathVariable int companyId) {
		return priceService.getCompanyStockPrices(companyId, null, null);
	}
	
	@GetMapping("/{companyId}/{from}/{to}")
	public List<Price> getPrices(
			@PathVariable int companyId, 
			@PathVariable(name="from", required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ") Date fromDate, 
			@PathVariable(name="to", required=false) @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ") Date toDate) {
		return priceService.getCompanyStockPrices(companyId, fromDate, toDate);
	}
	
	@GetMapping("/{companyId}/{date}")
	public List<Price> getPricesByDate(
			@PathVariable int companyId, 
			@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ") Date date) {
		return priceService.getCompanyStockPricesByDate(companyId, date);
	}

	@GetMapping("/{companyId}/history")
	public PriceHistory getPriceHistory(
			@PathVariable int companyId) {
		return priceService.getCompanyStockHistory(companyId);
	}

	@GetMapping("/sectorPrices")
	public List<SectorPrice> getSectorPrices() {
		return priceService.getSectorPrices();
	}
	
	@PostMapping("/import")
	public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
		try {
			InputStream stream = file.getInputStream();
			List<Price> importedPrices = priceService.importFromExcel(stream);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ImportResponse(true, file.getSize(), importedPrices.size(), "File uploaded successfully."));
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@PathVariable int companyId, @RequestParam double price) {
		Price priceObj = priceService.addPrice(companyId, price);
		return ResponseEntity.status(HttpStatus.CREATED).body(priceObj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int companyId, @RequestBody Price price) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();

		Price priceObj = priceService.updatePrice(companyId, price);
		if (priceObj != null) {
			return ResponseEntity.ok(priceObj);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		if (role.equals("ADMIN")) {
			priceService.deletePrice(id);
			return ResponseEntity.ok("Delete price record successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
