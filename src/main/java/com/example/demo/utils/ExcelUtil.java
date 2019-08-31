package com.example.demo.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2018/7/6.
 * easypoi操作excel导出
 */
public class ExcelUtil {

    /**
     * 根据excel模板导出excel
     *
     * @param templatePath 模板文件路径 eg:'D:/template.xlsx'
     * @param list         结果集
     * @param resultPath   生成文件夹 eg:'D:/excel/'
     */
    public static String exportByTemplate(String templatePath, List list, String resultPath) {
        String filePath = "";
        TemplateExportParams params = new TemplateExportParams(templatePath);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);

        File dir = new File(resultPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        filePath = resultPath + new SimpleDateFormat("ddHHmmssS").format(new Date());
        if (workbook instanceof HSSFWorkbook) {
            filePath = filePath + ".xls";
        } else {
            filePath = filePath + ".xlsx";
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public static String export(String title, Class<?> pojoClass, List list, String resultPath){
        String filePath = "";
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title,"sheet1"),pojoClass, list);

        File dir = new File(resultPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        filePath = resultPath + new SimpleDateFormat("ddHHmmssS").format(new Date());
        if (workbook instanceof HSSFWorkbook) {
            filePath = filePath + ".xls";
        } else {
            filePath = filePath + ".xlsx";
        }


        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 通过流输出excel文件
     * @param title
     * @param pojoClass
     * @param list
     * @param response
     * @throws IOException
     */
    public static void exportByStream(String title, Class<?> pojoClass, List list, HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title,"sheet1"),pojoClass, list);

        String fileName = new SimpleDateFormat("ddHHmmssS").format(new Date());
        if (workbook instanceof HSSFWorkbook) {
            fileName = fileName + ".xls";
        } else {
            fileName = fileName + ".xlsx";
        }

        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

}
