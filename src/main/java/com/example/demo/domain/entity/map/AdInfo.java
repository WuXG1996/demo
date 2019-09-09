package com.example.demo.domain.entity.map;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author void
 * @date 2019/9/9 16:33
 * @desc
 */
@Data
public class AdInfo {
    private String nation;
    private String province;
    private String city;
    private String district;
    private String adcode;
}
