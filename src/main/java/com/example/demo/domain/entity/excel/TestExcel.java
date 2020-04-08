package com.example.demo.domain.entity.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.example.demo.domain.constant.CommonConstant;
import lombok.Data;

import java.util.Date;

/**
 * @author void
 * @date 2020/3/18 15:48
 * @desc
 */
@Data
public class TestExcel {
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "时间戳")
    private Long timestamp;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "性别1")
    private CommonConstant.Sex sex1;
    @Excel(name = "性别2", enumExportField = "code", enumImportMethod = "getSex")
    private CommonConstant.Sex sex2;
    @Excel(name = "性别3", enumExportField = "name", enumImportMethod = "getSex")
    private CommonConstant.Sex sex3;
    @Excel(name = "性别4", replace = {"男_1", "女_2"}, suffix = "生")
    private Integer sex4;

    @Excel(name = "布尔值")
    private Boolean result;
    @Excel(name = "网络图片", type = 2)
    private String imgUrl;
    @Excel(name = "分数")
    private Double score;
}
