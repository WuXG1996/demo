package com.example.demo.utils;

import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.net.URISyntaxException;

/**
 * 文件处理工具
 */
public class FileUtilTest {

    public static String getWebRootPath(String filePath) {
        try {
            String path = PoiPublicUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("WEB-INF/classes/", "");
            path = path.replace("file:/", "");
            return path + filePath;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
