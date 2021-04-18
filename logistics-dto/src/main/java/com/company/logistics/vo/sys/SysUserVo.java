package com.company.logistics.vo.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.company.logistics.base.PageParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-03-24 22:40:14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVo extends PageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 账号
     */
    private String loginAccount;
    /**
     * 电话
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户类型(管理端0,承运商1,司机2,货主3)
     */
    private Integer userType;
    /**
     * 钉钉用户ID
     */
    private String dingDingUserId;
    /**
     * 微信登录id
     */
    private String openId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 启用禁用
     */
    private Boolean disable;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 企业id
     */
    private Long companyId;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;
    /**
     *
     */
    private String createUser;
    /**
     *
     */
    private String updateUser;
    /**
     *
     */
    private Boolean deleted;

}
