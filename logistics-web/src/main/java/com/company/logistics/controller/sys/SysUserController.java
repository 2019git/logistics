package com.company.logistics.controller.sys;

import java.util.Arrays;
import java.util.Map;

import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.base.Result;
import com.company.logistics.service.sys.SysUserService;
import com.company.logistics.vo.sys.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-03-24 22:40:14
 */
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页列表
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-03-24 22:40:14
     */
    @PostMapping("/queryPage")
    public Result queryPage(HttpServletRequest request, @RequestBody PageParam pageParam) {
        Pagination pagination = sysUserService.queryPage(pageParam);
        return Result.success(pagination);
    }

    /**
     * 查询详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/3/27 18:06
     */
    @GetMapping("/queryInfo/{id}")
    public Result queryInfo(@PathVariable("id") Long id) {
        SysUserVo userVo = sysUserService.queryInfo(id);
        return Result.success(userVo);
    }

    /**
     * 添加用户
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/3/27 18:04
     */
    @PostMapping("/save")
    public Result save(HttpServletRequest request, @RequestBody SysUserVo userVo) {
        sysUserService.addUser(userVo);
        return Result.success();
    }

    /**
     * 修改详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/3/27 18:39
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(HttpServletRequest request, @RequestBody SysUserVo userVo) {
        sysUserService.updateInfo(userVo);
        return Result.success();
    }

    /**
     * 删除信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/3/27 18:45
     */
    @GetMapping("/deleteInfo/{id}")
    public Result deleteInfo(HttpServletRequest request, @PathVariable("id") Long id) {
        sysUserService.deleteInfo(id);
        return Result.success();
    }
}
