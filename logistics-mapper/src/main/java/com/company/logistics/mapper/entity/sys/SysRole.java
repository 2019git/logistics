package com.company.logistics.mapper.entity.sys;

import com.company.logistics.base.BaseEntity;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.Table;

/**
 * 角色表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-04-18 18:31:08
 */
@Data
@Table(name = "tb_sys_role")
public class SysRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

}
