package com.example.demo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.pojo.StudentEntity;
import com.example.demo.pojo.User;
import com.example.demo.utils.ExcelUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by void on 2018/7/6.
 */
public class ExcelTest {

    /**
     * 导出
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        List<User> list = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("张三");
        user1.setPassword("aaa");
        user1.setAddress("五指山");
        user1.setStatus(0);

        User user2 = new User();
        user2.setUsername("李四");
        user2.setPassword("bbb");
        user2.setAddress("水帘洞");
        user2.setStatus(1);

        User user3 = new User();
        user3.setUsername("王五");
        user3.setPassword("ccc");
        user3.setAddress("快乐老家");
        user3.setStatus(2);

        list.add(user1);
        list.add(user2);
        list.add(user3);

        ExcelUtil.exportByTemplate("templates/export.xlsx", list, "D:/EXCEL测试/");
//        Date start = new Date();
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),User.class, list);
//        System.out.println(new Date().getTime() - start.getTime());
//        File savefile = new File("D:/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D:/excel/1111.xls");
//        workbook.write(fos);
//        fos.close();
    }

    /**
     * 导入
     */
    @Test
    public void test2() throws Exception {
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<User> list = ExcelImportUtil.importExcel(new FileInputStream(new File("templates/import.xlsx")),User.class,params);

            System.out.println(list.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws Exception{
        List<StudentEntity> list = new ArrayList<>();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId("1");
        studentEntity.setName("吴相旰");
        studentEntity.setSex(1);
        studentEntity.setBirthday(new Date());
        list.add(studentEntity);

       /* Date start = new Date();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),StudentEntity.class, list);
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/1111.xls");
        workbook.write(fos);
        fos.close();*/

       ExcelUtil.export("测试标题", StudentEntity.class, list, "D:/excel/");
    }
}
