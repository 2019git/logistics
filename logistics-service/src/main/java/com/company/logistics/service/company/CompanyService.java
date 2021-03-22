package com.company.logistics.service.company;

import com.company.logistics.mapper.entity.company.Company;

import java.util.List;

public interface CompanyService {
    List<Company> selectCompanyList();
}
