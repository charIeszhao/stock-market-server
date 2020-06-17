package com.demo.stockmarket.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import com.demo.stockmarket.company.repository.CompanyRepository;
import com.demo.stockmarket.company.service.CompanyService;
import com.demo.stockmarket.entity.Company;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<Company> getCompanies(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return companyRepository.findAll(pageable);
	}

	@Override
	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Company company, int id) {
		Company oldCompany = companyRepository.findById(id).get();
		if (oldCompany != null) {
			company.setId(id);
			return companyRepository.save(company);
		}
		return null;
	}

	@Override
	public void deleteCompany(int id) {
		companyRepository.deleteById(id);
	}

	@Override
	public Company getCompanyById(int id) {
		return companyRepository.findById(id).get();
	}

}
