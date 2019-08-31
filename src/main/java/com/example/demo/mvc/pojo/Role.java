package com.example.demo.mvc.pojo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Role implements Serializable{
	private static final long serialVersionUID = -8827802661944564302L;

	private Integer id;
	
	private String name;
	
	private String descr;
}
