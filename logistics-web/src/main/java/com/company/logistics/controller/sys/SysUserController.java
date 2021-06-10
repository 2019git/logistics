package com.company.logistics.controller.sys;

import java.util.Arrays;
import java.util.Map;

import com.company.logistics.base.PageParam;
import com.company.logistics.base.Pagination;
import com.company.logistics.base.Result;
import com.company.logistics.config.esaypoi.ExportResponseConfig;
import com.company.logistics.service.sys.SysUserService;
import com.company.logistics.vo.sys.SysUserVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

    /**
     * 用户信息导出
     *
     * @author wangzhijun
     * @email developer@gmail.com
     * @date 2021/6/10 23:57
     */
    @GetMapping("/exportExcelCarrierOrder")
    public ResponseEntity<Resource> exportExcelCarrierOrder(HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = sysUserService.exportExcelUserInfo();
        ExportResponseConfig.addExportExcelResponse(response, workbook, "user");
        return new ResponseEntity<Resource>(null, null, HttpStatus.OK);
    }
}
