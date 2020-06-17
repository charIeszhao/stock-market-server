package com.demo.stockmarket.company.controller;

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

import java.util.List;

import com.demo.stockmarket.RequestContext;
import com.demo.stockmarket.RequestContextManager;
import com.demo.stockmarket.company.service.CompanyService;
import com.demo.stockmarket.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public Page<Company> findAllCompanies(@RequestParam int page, @RequestParam int pageSize) {
		return companyService.getCompanies(page, pageSize);
	}

	@GetMapping("/all")
	public List<Company> findAllCompanies() {
		return companyService.getCompanies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
		Company company = companyService.getCompanyById(id);
		return ResponseEntity.ok(company);
	}

	@PostMapping
	public ResponseEntity<Company> create(@RequestBody Company company) {
		company = companyService.addCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(company);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Company company, @PathVariable int id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		
		if (role.equals("ADMIN")) {
			company = companyService.updateCompany(company, id);
			if (company != null) {
				return ResponseEntity.ok(company);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Do not have permission to update selected company.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		RequestContext requestContext = RequestContextManager.getContext();
		String role = requestContext.getRole();
		if (role.equals("ADMIN")) {
			companyService.deleteCompany(id);
			return ResponseEntity.ok("Delete company successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
