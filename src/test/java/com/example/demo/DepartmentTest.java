package com.example.demo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.domain.BaseVo;
import com.example.demo.pojo.User;
import com.example.demo.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.pojo.Department;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentMapper departmentMapper;
    
    //@Test
    public void testInsert() {
        Department department = new Department();
        department.setId(1);
        department.setName("研发部");
        department.setDescr("开发产品");
        this.departmentMapper.insert(department);
    }
    
    @Test
    public void testGetById() {
        Department department = this.departmentMapper.getById(1);
        System.out.println(department);
    }
    
    //@Test
    public void testUpdate() {
        Department department = new Department();
        department.setId(1);
        department.setDescr("开发高级产品");
        this.departmentMapper.update(department);
    }
    
    //@Test
    public void testDeleteById() {
        this.departmentMapper.deleteById(1);
    }

    @Test
    public void testBatch(){
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<Department> list = ExcelImportUtil.importExcel(new FileInputStream(new File("D:/batch.xlsx")),Department.class,params);
            Date date1 = new Date();
            List<Department> result = new ArrayList<>();
            for(Department d: list){
                d.setId(null);
                d.setDescr("描述");
                result.add(d);
                if(result.size()==1000){
                    departmentMapper.batchInsert(result);
                    result.clear();
                }
            }
            departmentMapper.batchInsert(result);

            Date date2 = new Date();
            System.out.println(list.size()+"---"+(date2.getTime()-date1.getTime()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBatch1(){
        Long d1 = System.currentTimeMillis();
        List<Department> result = new ArrayList<>();
        for (int i=0;i<=50000;i++){
            Department department = new Department();
            department.setName("测试部门");
            department.setDescr("描述");
            result.add(department);
            if(result.size()==2000){
                departmentMapper.batchInsert(result);
                result.clear();
            }
        }
        departmentMapper.batchInsert(result);

        System.out.println(System.currentTimeMillis()-d1);
    }

    @Test
    public void bigExport() throws IOException {
        Long d1 = System.currentTimeMillis();
        Long count = departmentMapper.countDepartment();

        ExportParams params = new ExportParams("大数据测试", "测试");
        Workbook workbook = null;

        int step = 2000;
        int i = 0;
        while(i*step<count){
            BaseVo baseVo = new BaseVo();
            baseVo.setStartRow(i*step);
            baseVo.setPageSize(step);
            List<Department> list = departmentMapper.selectDepartment(baseVo);

            workbook = ExcelExportUtil.exportBigExcel(params, Department.class, list);

            i++;
            System.out.println(i);
        }
        ExcelUtil.exportBigData(workbook);
        System.out.println(System.currentTimeMillis()-d1);
    }
}
