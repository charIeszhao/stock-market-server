package com.demo.stockmarket.price.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.entity.Price;
import com.demo.stockmarket.price.servcie.PriceService;

@RestController
@RequestMapping("/price")
public class PriceController {

	@Autowired
	private PriceService priceService;

	@GetMapping
	public List<Price> getPricesByCompanyId(@PathVariable int companyId, @RequestParam Date from, @RequestParam Date to) {
		return priceService.getPricesByCompanyId(companyId, from, to);
	}

	@PostMapping
	public ResponseEntity<?> create(@PathVariable int companyId, @RequestParam double price) {
		Price priceObj = priceService.addPrice(companyId, price);
		return ResponseEntity.status(HttpStatus.CREATED).body(priceObj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int companyId, @RequestParam double price, @RequestParam String dateStr) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();

		Price priceObj = priceService.updatePrice(companyId, price, new Date(dateStr));
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
