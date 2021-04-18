package com.company.logistics.mapper.entity.company;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_sys_company")
public class Company implements Serializable {
    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业简称
     */
    private String easyCompanyName;

    /**
     * 业务对接人id
     */
    private Long businessUserId;

    /**
     * 企业地址
     */
    private String companyAddress;

    /**
     * 统一信用编码
     */
    private String unifiedCreditCode;

    /**
     * 营业执照文件ID
     */
    private String businessLicense;

    /**
     * 开户许可
     */
    private String accountPermit;

    /**
     * 注册资本
     */
    private String registerCapital;

    /**
     * 截至营业日期
     */
    private String businessExpireDate;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 身份证号
     */
    private String idCardNumber;

    /**
     * 证件有效期
     */
    private String idCardExpireDate;

    /**
     * 身份证正面照片id
     */
    private String idCardObverse;

    /**
     * 身份证反面照片id
     */
    private String idCardReverse;
}
