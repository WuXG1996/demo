package com.example.demo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author void
 * @date 2020/4/17 16:00
 * @desc
 */
@Data
public class Bank {

    @Excel(name = "编码")
    private String code;
    @Excel(name = "名称")
    private String name;
}
