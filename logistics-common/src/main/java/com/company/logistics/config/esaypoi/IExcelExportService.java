package com.company.logistics.config.esaypoi;

import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import cn.afterturn.easypoi.exception.excel.enums.ExcelExportEnum;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Excel导出服务
 *
 * @author wangzhijun
 * @date 2021/6/11 16:37
 */
public class IExcelExportService extends ExcelExportService {

    public void createSheet(Workbook workbook, ExportParams entity, Class<?> pojoClass, Map<String, String> exportCustomFields,
                            Collection<?> dataSet) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Excel export start ,class is {}", pojoClass);
            LOGGER.debug("Excel version is {}",
                    entity.getType().equals(ExcelType.HSSF) ? "03" : "07");
        }
        if (workbook == null || entity == null || pojoClass == null || dataSet == null) {
            throw new ExcelExportException(ExcelExportEnum.PARAMETER_ERROR);
        }
        try {
            List<ExcelExportEntity> excelParams = new ArrayList<ExcelExportEntity>();
            // 得到所有字段
            Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
            //自定义导出字段
            if (null != exportCustomFields && !exportCustomFields.isEmpty()) {
                fileds = Arrays.stream(fileds).filter(o -> StringUtils.isNotEmpty(exportCustomFields.get(o.getName()))).toArray(Field[]::new);
            }
            ExcelTarget etarget = pojoClass.getAnnotation(ExcelTarget.class);
            String targetId = etarget == null ? null : etarget.value();
            getAllExcelField(entity.getExclusions(), targetId, fileds, excelParams, pojoClass,
                    null, null);
            //获取所有参数后,后面的逻辑判断就一致了
            createSheetForMap(workbook, entity, excelParams, dataSet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, e.getCause());
        }
    }
}
