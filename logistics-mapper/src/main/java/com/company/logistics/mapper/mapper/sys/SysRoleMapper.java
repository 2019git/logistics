package com.company.logistics.mapper.mapper.sys;

import com.company.logistics.base.BaseMapper;
import com.company.logistics.mapper.entity.sys.SysRole;
import com.company.logistics.vo.sys.SysRoleVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 角色表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-04-18 18:31:08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询集合
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    List<SysRoleVo> queryList();

    /**
     * 删除信息(逻辑删除)
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @Update("update tb_sys_role set deleted = 1 where id = #{id}")
    void deleteInfo(@Param("id") Long id);
}
