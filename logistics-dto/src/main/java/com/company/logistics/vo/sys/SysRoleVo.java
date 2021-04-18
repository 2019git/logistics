package com.company.logistics.vo.sys;

import java.io.Serializable;
import java.util.Date;

import com.company.logistics.base.PageParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 角色表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-04-18 18:31:08
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleVo extends PageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
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
