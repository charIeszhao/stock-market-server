package com.demo.stock.company.service;

import java.util.List;

import com.demo.stock.entity.CompanyEntity;

public interface CompanyService {

	List<CompanyEntity> listCompanies();
	
	CompanyEntity get(int companyId);
	
	CompanyEntity update(CompanyEntity company);
	
	void delete(int companyId);
	
	CompanyEntity add(CompanyEntity company);
}
