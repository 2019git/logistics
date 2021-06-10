package com.company.logistics.config.esaypoi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 导出添加Response信息
 * @author wzj
 * @date 2020/12/8 23:42
 */
@Slf4j(topic = "ExportResponseConfig")
public class ExportResponseConfig {

    /**
     * 导出excel添加response信息
     *
     * @param response
     * @param workbook
     * @param nameHeader 文件名称头
     * @return
     * @author wangzhijun
     * @date 2020/12/8 23:42
     */
    public static void addExportExcelResponse(HttpServletResponse response, Workbook workbook, String nameHeader) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Encoding", "UTF-8");
        // 当前日期，用于导出文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String name = nameHeader + "-" + format + ".xls";
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + URLEncoder.encode(name, "UTF-8") + "\"");
            response.setHeader("fileName", URLEncoder.encode(format, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.error("导出失败", e);
        }
    }
}
