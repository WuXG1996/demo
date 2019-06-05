package com.example.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class Department implements Serializable{

    private static final long serialVersionUID = 6067283535977178571L;

	@Excel(name = "主键")
    private Integer id;
	@Excel(name = "部门名")
    private String name;
	@Excel(name = "描述")
    private String descr;

	private String uuid;
}
