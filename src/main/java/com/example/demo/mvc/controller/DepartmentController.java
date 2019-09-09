package com.example.demo.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mvc.pojo.Department;
import com.example.demo.mvc.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("save")
	public Map<String, Object> save(Department department) {
		this.departmentService.save(department);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		map.put("msg", "保存成功");
		return map;
	}
	
	@GetMapping("get/{id}")
	public Map<String,Object> get(@PathVariable("id") Integer id) {
	    Department department = this.departmentService.getDepartmentById(id);
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("code", "200");
	    map.put("msg", "获取成功");
	    map.put("data", department);
	    return map;
	}
	
	/**
	 * 加了RequestBody后就需要以json格式传递
	 * 不加可以用context-type为application/x-www-form-urlencoded的来接收
	 * @param department
	 * @return
	 */
	@PutMapping("update")
	public Map<String, Object> update(@RequestBody Department department) {
		this.departmentService.update(department);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		map.put("msg", "修改成功");
		return map;
	}

	@DeleteMapping("delete/{id}")
	public Map<String, Object> delete(@PathVariable("id") Integer id) {
		this.departmentService.delete(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		map.put("msg", "删除成功");
		return map;
	}
}
