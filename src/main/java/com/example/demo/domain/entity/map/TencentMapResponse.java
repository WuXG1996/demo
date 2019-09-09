package com.example.demo.domain.entity.map;

import lombok.Data;

/**
 * @author void
 * @date 2019/9/9 16:31
 * @desc
 */
@Data
public class TencentMapResponse {
    private Integer status;
    private String message;
    private IpResult result;
}
