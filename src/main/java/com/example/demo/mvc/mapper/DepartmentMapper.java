package com.example.demo.mvc.mapper;

import com.example.demo.domain.BaseVo;
import com.example.demo.mvc.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List<Department> selectList(@Param("name") String name, @Param("descr") String descr);

    List<Department> selectList2(String name, String descr);
}
