package com.example.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class Department implements Serializable{
    private static final long serialVersionUID = 6067283535977178571L;

	@Excel(name = "主键")
    private Integer id;
	@Excel(name = "部门名")
    private String name;
	@Excel(name = "描述")
    private String descr;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
    
    
}
