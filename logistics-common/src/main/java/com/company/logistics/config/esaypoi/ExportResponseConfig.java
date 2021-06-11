package com.company.logistics.config.esaypoi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * @date 2020/10/10 11:04
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

    /**
     * excel添加下拉框选项
     *
     * @param sheet              sheet页
     * @param firstRow           开始行（0为第一行）
     * @param lastRow            结束行（0为第一行）
     * @param firstCol           开始列（0为第一列）
     * @param lastCol            结束列（0为第一列）
     * @param explicitListValues 下拉框选项值
     * @return
     * @author wangzhijun
     * @date 2021/1/5 13:48
     */
    public static void addSelectList(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, List<String> explicitListValues) {
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DVConstraint explicitListConstraint = DVConstraint.createExplicitListConstraint(explicitListValues.stream().toArray(String[]::new));
        HSSFDataValidation validation = new HSSFDataValidation(cellRangeAddressList, explicitListConstraint);
        sheet.addValidationData(validation);
    }

    /**
     * excel添加下拉框选项(支持大数据量)
     *
     * @param workbook           sheet页
     * @param firstRow           开始行（0为第一行）
     * @param lastRow            结束行（0为第一行）
     * @param firstCol           开始列（0为第一列）
     * @param lastCol            结束列（0为第一列）
     * @param explicitListValues 下拉框选项值
     * @param sheetHidden        影藏的sheet编号（顺序赋值1,2,3...），多个下拉数据不能使用同一个
     * @return
     * @author wangzhijun
     * @date 2021/1/15 16:16
     */
    public static void addSelectList(Workbook workbook, int firstRow, int lastRow, int firstCol, int lastCol, List<String> explicitListValues, int sheetHidden) {
        String hiddenName = "hidden_" + sheetHidden;
        //添加下拉框选项
        Sheet sheet = workbook.getSheetAt(0);
        Sheet hiddenSheet = workbook.createSheet(hiddenName);
        for (int i = 0; i < explicitListValues.size(); i++) {
            Row row = hiddenSheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(explicitListValues.get(i));
        }
        Name namedCell = workbook.createName();
        namedCell.setNameName(hiddenName);
        namedCell.setRefersToFormula(hiddenName + "!$A$1:$A$" + explicitListValues.size());
        DVConstraint dvConstraint = DVConstraint.createFormulaListConstraint(hiddenName);
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        HSSFDataValidation validation = new HSSFDataValidation(cellRangeAddressList, dvConstraint);
        workbook.setSheetHidden(sheetHidden, true);
        sheet.addValidationData(validation);
    }
}
