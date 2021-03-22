package com.company.logistics.controller.company;

import com.company.logistics.base.Result;
import com.company.logistics.mapper.entity.company.Company;
import com.company.logistics.service.company.CompanyService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getCompany")
    public Result<List<Company>> getCompany(){
        List<Company> list = companyService.selectCompanyList();
        return Result.success(list);
    }
}
