package com.example.demo.domain.entity.map;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author void
 * @date 2019/9/9 16:32
 * @desc
 */
@Data
public class IpResult {

    private String ip;
    private Location location;
    @JSONField(name = "ad_info")
    private AdInfo adInfo;
}
