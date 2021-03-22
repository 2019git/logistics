package com.company.logistics.service.company.impl;

import com.company.logistics.mapper.entity.company.Company;
import com.company.logistics.mapper.mapper.company.CompanyMapper;
import com.company.logistics.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> selectCompanyList() {
        return companyMapper.selectCompanyList();
    }
}
