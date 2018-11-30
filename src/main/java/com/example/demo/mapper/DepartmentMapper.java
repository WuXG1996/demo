package com.example.demo.mapper;

import com.example.demo.domain.BaseVo;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    public void insert(Department department);
    
    public Department getById(Integer id);

    List<Department> selectDepartment(BaseVo baseVo);

    Long countDepartment();
    
    public void update(Department department);
    
    public void deleteById(Integer id);

    Long batchInsert(List<Department> list);

    void batchUpdate(List<Department> list);
}
