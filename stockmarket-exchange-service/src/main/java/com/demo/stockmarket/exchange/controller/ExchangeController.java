package com.demo.stockmarket.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.entity.Exchange;
import com.demo.stockmarket.exchange.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@GetMapping
	public Page<Exchange> findAllExchanges(@RequestParam int page, @RequestParam int pageSize) {

		System.out.println("find all exchanges with pager...");
		return exchangeService.findAllExchanges(page, pageSize);
	}

	@PostMapping
	public ResponseEntity<Exchange> register(@RequestBody Exchange exchange) {
		exchange = exchangeService.addExchange(exchange);
		return ResponseEntity.status(HttpStatus.CREATED).body(exchange);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Exchange exchange, @PathVariable int id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		
		if (role.equals("ADMIN")) {
			exchange = exchangeService.updateExchange(exchange, id);
			if (exchange != null) {
				return ResponseEntity.ok(exchange);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Do not have permission to update current exchange.");
		}
	}

	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		if (role.equals("ADMIN")) {
			exchangeService.deleteExchange(id);
			return ResponseEntity.ok("Delete exchange successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
