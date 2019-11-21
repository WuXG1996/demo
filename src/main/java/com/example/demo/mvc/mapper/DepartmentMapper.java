package com.example.demo.mvc.mapper;

import com.example.demo.domain.BaseVo;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.mvc.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    void insert(Department department);
    
    Department getById(Integer id);

    List<Department> selectDepartment(BaseVo baseVo);

    Long countDepartment();
    
    void update(Department department);
    
    void deleteById(Integer id);

    Long batchInsert(List<Department> list);

    void batchUpdate(List<Department> list);
}
