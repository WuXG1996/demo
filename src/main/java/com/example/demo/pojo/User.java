package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Id;

public class User implements Serializable{
	private static final long serialVersionUID = 2246880492943450897L;
	private Integer id;
	@Excel(name = "用户名")
	private String username;
	@Excel(name = "密码")
	private String password;
	@Excel(name = "地址")
	private String address;
    @Excel(name = "状态",replace = {"启用_0", "停用_1"})
	private int status;

    private List<Tag> tags;
    /**
     * 引入WebConfig里的FastJson格式化
     */
    @JSONField(format="yyyy-MM-dd")
    private Date birthday;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
