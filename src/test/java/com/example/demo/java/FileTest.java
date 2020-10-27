package com.example.demo.java;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author void
 * @date 2020/10/27 19:13
 * @desc
 */
public class FileTest {
    
    @Test
    public void test1() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/pic/桔梗.png");
        FileInputStream fin = new FileInputStream(file);
        byte[] bytes  = new byte[fin.available()];
        fin.read(bytes);
        fin.close();
        
        File imageFile = new File("D://", "test.jpg");
        Files.write(bytes, imageFile);
    }

    /**
     * 解析txt按行划分
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        List<String> lines = Files.readLines(ResourceUtils.getFile("classpath:static/txt/user_name.txt"), Charsets.UTF_8);
        System.out.println(lines);
    }
}
