package com.example.demo.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.mvc.pojo.Role;

/**
 * Spring data jpa是spring中集成的调用orm框架的Repository层实现
 * 默认调用hibernate
 * @author void
 * 2018年4月30日 下午1:41:55
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
