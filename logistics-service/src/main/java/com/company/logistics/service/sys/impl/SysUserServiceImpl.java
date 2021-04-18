package com.company.logistics.service.sys.impl;

import com.company.logistics.base.BaseServiceImpl;
import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.mapper.entity.sys.SysUser;
import com.company.logistics.mapper.mapper.sys.SysUserMapper;
import com.company.logistics.service.sys.SysUserService;
import com.company.logistics.util.BaseEntityUtil;
import com.company.logistics.vo.sys.SysUserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Pagination queryPage(PageParam pageParam) {
        Page<SysUserVo> page = PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<SysUserVo> list = mapper.queryList();
        return new Pagination(pageParam.getPage(), pageParam.getSize(), (int) page.getTotal(), list);
    }

    @Override
    public void addUser(SysUserVo userVo) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userVo, user);
        BaseEntityUtil.createEntityInfo(user);
        mapper.insert(user);
    }

    @Override
    public SysUserVo queryInfo(Long id) {
        SysUser user = this.selectById(id);
        SysUserVo userVo = new SysUserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public void updateInfo(SysUserVo userVo) {
        SysUser user = this.selectById(userVo.getId());
        BeanUtils.copyProperties(userVo, user, BaseEntityUtil.ignoreProperties);
        BaseEntityUtil.updateEntityInfo(user);
        mapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteInfo(Long id) {
        mapper.deleteInfo(id);
    }

}