package com.company.logistics.vo.sys.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * 用户信息导出模板实体
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021/6/11
 */
@Data
public class UserExportVo {

    /**
     * 主键id
     */
    @Excel(name = "主键id", orderNum = "1", width = 15)
    private Long id;
    /**
     * 账号
     */
    @Excel(name = "账号", orderNum = "2", width = 15)
    private String loginAccount;
    /**
     * 电话
     */
    @Excel(name = "电话", orderNum = "3", width = 15)
    private String phone;
    /**
     * 用户名称
     */
    @Excel(name = "用户名称", orderNum = "4", width = 15)
    private String userName;

    public UserExportVo(Long id, String loginAccount, String phone, String userName) {
        this.id = id;
        this.loginAccount = loginAccount;
        this.phone = phone;
        this.userName = userName;
    }
}
