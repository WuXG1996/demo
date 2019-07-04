package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.pojo.IUser;
import com.example.demo.utils.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2018/6/12.
 */
@Controller
public class ExcelController {

    /**
     * excel上传数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/excelUpload")
    @ResponseBody
    public String excelUpload(HttpServletRequest request, HttpServletResponse response){
        String result = "导入失败";

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setHeadRows(2);//依情况而定
            try {
                List<IUser> list = ExcelImportUtil.importExcel(file.getInputStream(),IUser.class,params);
                for (IUser u : list) {
                    System.out.println(u.getUsername());
                    System.out.println(u.getPassword());
                    System.out.println(u.getAddress());
                }
                System.out.println("导入成功");
                result = "ok";
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("导入失败");
            }finally{
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @RequestMapping("/excelExport")
    public void excelExport(HttpServletRequest request, HttpServletResponse response){
        List<IUser> list = new ArrayList<>();

        IUser user1 = new IUser();
        user1.setUsername("张三");
        user1.setPassword("aaa");
        user1.setAddress("五指山");

        IUser user2 = new IUser();
        user2.setUsername("李四");
        user2.setPassword("bbb");
        user2.setAddress("水帘洞");

        IUser user3 = new IUser();
        user3.setUsername("王五");
        user3.setPassword("ccc");
        user3.setAddress("快乐老家");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        try {
            ExcelUtil.exportByStream("用户", IUser.class, list, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put(NormalExcelConstants.FILE_NAME,"用户信息");
        modelMap.put(NormalExcelConstants.CLASS,User.class);
        modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("User列表", "导出人void",
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST,list);
        try{
            //renderMergedOutputModel(modelMap,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    /*public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codedFileName = "临时文件";
        Workbook workbook = null;
        if(model.containsKey("mapList")) {
            List<Map<String, Object>> list = (List)model.get("mapList");
            if(list.size() == 0) {
                throw new RuntimeException("MAP_LIST IS NULL");
            }

            workbook = ExcelExportUtil.exportExcel((ExportParams)((Map)list.get(0)).get("params"), (Class)((Map)list.get(0)).get("entity"), (Collection)((Map)list.get(0)).get("data"));

            for(int i = 1; i < list.size(); ++i) {
                (new ExcelExportServer()).createSheet(workbook, (ExportParams)((Map)list.get(i)).get("params"), (Class)((Map)list.get(i)).get("entity"), (Collection)((Map)list.get(i)).get("data"));
            }
        } else {
            workbook = ExcelExportUtil.exportExcel((ExportParams)model.get("params"), (Class)model.get("entity"), (Collection)model.get("data"));
        }

        if(model.containsKey("fileName")) {
            codedFileName = (String)model.get("fileName");
        }

        if(workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if(this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    public boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0;
    }*/

}
