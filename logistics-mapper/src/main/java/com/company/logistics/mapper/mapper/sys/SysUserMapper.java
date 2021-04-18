package com.company.logistics.mapper.mapper.sys;

import com.company.logistics.base.*;
import com.company.logistics.mapper.entity.sys.SysUser;
import com.company.logistics.vo.sys.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-03-24 22:40:14
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询集合
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-03-24 22:40:14
     */
    List<SysUserVo> queryList();

    /**
     * 删除信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/3/27 18:45
     */
    @Update("update tb_sys_user set deleted = 1 where id = #{id}")
    void deleteInfo(@Param("id") Long id);
}
