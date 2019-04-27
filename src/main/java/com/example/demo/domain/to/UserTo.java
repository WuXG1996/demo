package com.example.demo.domain.to;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author void
 * @date 2019/4/22 16:28
 * @desc
 */
@Data
@AllArgsConstructor
public class UserTo {
    @JSONField(name = "user_id")
    private Long userId;
    private String username;
    @JSONField(name = "create_time")
    private Date createTime;
}
