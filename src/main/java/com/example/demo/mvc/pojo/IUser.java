package com.example.demo.mvc.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class IUser implements Serializable{
	private static final long serialVersionUID = 2246880492943450897L;
	private Integer id;
	@Excel(name = "用户名")
	private String username;
	@Excel(name = "密码")
	private String password;
	@Excel(name = "地址")
	private String address;
    @Excel(name = "状态",replace = {"启用_0", "停用_1"})
	private Integer status;

    private List<Tag> tags;

    private List<String> phones;
    /**
     * 引入WebConfig里的FastJson格式化
     */
    @JSONField(format="yyyy-MM-dd")
    private Date birthday;
}
