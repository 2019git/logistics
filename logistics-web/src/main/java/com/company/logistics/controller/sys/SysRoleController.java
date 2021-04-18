package com.company.logistics.controller.sys;

import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.base.Result;
import com.company.logistics.service.sys.SysRoleService;
import com.company.logistics.vo.sys.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 角色表
 *
 * @author wangzhijun
 * @email developer@gmail.com
 * @date 2021-04-18 18:31:08
 */
@RestController
@RequestMapping("/sys/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页列表
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @PostMapping("/queryPage")
    public Result queryPage(HttpServletRequest request, @RequestBody PageParam pageParam) {
        Pagination pagination = sysRoleService.queryPage(pageParam);
        return Result.success(pagination);
    }

    /**
     * 查询详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @GetMapping("/queryInfo/{id}")
    public Result queryInfo(@PathVariable("id") Long id) {
        SysRoleVo sysRoleVo = sysRoleService.queryInfo(id);
        return Result.success(sysRoleVo);
    }

    /**
     * 添加
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @PostMapping("/save")
    public Result save(HttpServletRequest request, @RequestBody SysRoleVo sysRoleVo) {
        sysRoleService.save(sysRoleVo);
        return Result.success();
    }

    /**
     * 修改详情信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(HttpServletRequest request, @RequestBody SysRoleVo sysRoleVo) {
        sysRoleService.updateInfo(sysRoleVo);
        return Result.success();
    }

    /**
     * 删除信息
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021-04-18 18:31:08
     */
    @GetMapping("/deleteInfo/{id}")
    public Result deleteInfo(HttpServletRequest request, @PathVariable("id") Long id) {
        sysRoleService.deleteInfo(id);
        return Result.success();
    }
}
