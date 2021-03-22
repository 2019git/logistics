package com.company.logistics.mapper.mapper.company;

import com.company.logistics.mapper.entity.company.Company;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CompanyMapper{

    List<Company> selectCompanyList();
}
