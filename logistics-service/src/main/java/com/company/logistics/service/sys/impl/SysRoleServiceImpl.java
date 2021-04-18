package com.company.logistics.service.sys.impl;

import com.company.logistics.base.BaseServiceImpl;
import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.mapper.entity.sys.SysRole;
import com.company.logistics.mapper.mapper.sys.SysRoleMapper;
import com.company.logistics.service.sys.SysRoleService;
import com.company.logistics.util.BaseEntityUtil;
import com.company.logistics.vo.sys.SysRoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Pagination queryPage(PageParam pageParam) {
        Page<SysRoleVo> page = PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<SysRoleVo> list = mapper.queryList();
        return new Pagination(pageParam.getPage(), pageParam.getSize(), (int) page.getTotal(), list);
    }

    @Override
    public SysRoleVo queryInfo(Long id) {
        SysRole sysRole = this.selectById(id);
        SysRoleVo sysRoleVo = new SysRoleVo();
        BeanUtils.copyProperties(sysRole, sysRoleVo);
        return sysRoleVo;
    }

    @Override
    public void save(SysRoleVo sysRoleVo) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleVo, sysRole);
        BaseEntityUtil.createEntityInfo(sysRole);
        mapper.insert(sysRole);
    }

    @Override
    public void updateInfo(SysRoleVo sysRoleVo) {
        SysRole sysRole = this.selectById(sysRoleVo.getId());
        BeanUtils.copyProperties(sysRoleVo, sysRole, BaseEntityUtil.ignoreProperties);
        BaseEntityUtil.updateEntityInfo(sysRole);
        mapper.updateByPrimaryKey(sysRole);
    }

    @Override
    public void deleteInfo(Long id) {
        mapper.deleteInfo(id);
    }
}