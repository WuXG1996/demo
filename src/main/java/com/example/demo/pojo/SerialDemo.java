package com.example.demo.pojo;

import java.io.Serializable;

/**
 * 序列化测试pojo
 * @author void
 * 2018年3月21日下午10:45:56
 */
public class SerialDemo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	
	public SerialDemo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "SerialDemo [name=" + name + ", age=" + age + "]";
	}
	
}
