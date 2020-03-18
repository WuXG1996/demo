package com.example.demo.other;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.demo.domain.constant.CommonConstant;
import com.example.demo.domain.entity.excel.TestExcel;
import com.example.demo.mvc.pojo.IUser;
import com.example.demo.mvc.pojo.StudentEntity;
import com.example.demo.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        List<IUser> list = new ArrayList<>();

        IUser user1 = new IUser();
        user1.setUsername("张三");
        user1.setPassword("aaa");
        user1.setAddress("五指山");
        user1.setStatus(0);

        IUser user2 = new IUser();
        user2.setUsername("李四");
        user2.setPassword("bbb");
        user2.setAddress("水帘洞");
        user2.setStatus(1);

        IUser user3 = new IUser();
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
            List<IUser> list = ExcelImportUtil.importExcel(new FileInputStream(new File("templates/import.xlsx")),IUser.class,params);

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

    @Test
    public void test4() throws IOException {
        List<TestExcel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestExcel client = new TestExcel();
            client.setAge(10);
            client.setTimestamp(System.currentTimeMillis());
            client.setCreateTime(new Date());
            client.setSex1(CommonConstant.Sex.Male);
            client.setSex2(CommonConstant.Sex.Female);
            client.setSex3(CommonConstant.Sex.Male);
            client.setSex4(CommonConstant.Sex.Female.getCode());
            client.setResult(true);
            //网络上可直接访问的图片,http://y3.ifengimg.com/a/2016_03/6154e935f8a0fc6.jpg
            //阿里云的私有bucket图片无法直接请求到,就算是生成了后缀带临时token和过期时间的
            client.setImgUrl("D://80120998_p0.png");
            list.add(client);
        }
        Date start = new Date();
        ExportParams params = new ExportParams("枚举测试", "测试", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, TestExcel.class, list);
        System.out.println(new Date().getTime() - start.getTime());
        FileOutputStream fos = new FileOutputStream("D:/my.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
