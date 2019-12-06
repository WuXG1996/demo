package com.example.demo.database;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.domain.BaseVo;
import com.example.demo.mvc.mapper.DepartmentMapper;
import com.example.demo.mvc.pojo.Department;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
        for (int i=0;i<=1000000;i++){
            Department department = new Department();
            department.setName("测试部门"+i);
            department.setDescr("描述"+i);
            department.setUuid(UUID.randomUUID().toString());
            result.add(department);
            if(result.size()==2000){
                departmentMapper.batchInsert(result);
                log.info("插入{}条", i+1);
                result.clear();
            }
        }
        departmentMapper.batchInsert(result);

        System.out.println(System.currentTimeMillis()-d1);
    }

    /**
     * 注明一个坑,一定要再数据源配置url上加&allowMultiQueries=true允许多条查询操作语句
     */
    @Test
    public void testBatchUpdate(){
        BaseVo baseVo = new BaseVo();
        baseVo.setPageSize(100);
        List<Department> list = departmentMapper.selectDepartment(baseVo);

        List<Department> updateList = new ArrayList<>();
        for (Department department : list) {
            department.setName("修改后的名字");
            department.setDescr("修改后的描述");
            updateList.add(department);
            if(updateList.size()==2){
                departmentMapper.batchUpdate(updateList);
                updateList.clear();
            }
        }
    }

    @Test
    public void selectTest1(){
        List<Department> list = departmentMapper.selectList("名字", "描述");
        System.out.println(111);
    }

    @Test
    public void selectTest2(){
        List<Department> list = departmentMapper.selectList2("名字", "描述");
        System.out.println(111);
    }
}
