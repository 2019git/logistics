package com.company.logistics.service.sys;

import com.company.logistics.base.BaseService;
import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.mapper.entity.sys.SysRole;
import com.company.logistics.vo.sys.SysRoleVo;

/**
 * 角色表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-04-18 18:31:08
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 分页列表
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    Pagination queryPage(PageParam pageParam);

    /**
     * 查询详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    SysRoleVo queryInfo(Long id);

    /**
     * 添加
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    void save(SysRoleVo sysRoleVo);

    /**
     * 修改详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    void updateInfo(SysRoleVo sysRoleVo);

    /**
     * 删除信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    void deleteInfo(Long id);
}

