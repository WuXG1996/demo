package com.example.demo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author void
 * @date 2019/3/28 11:05
 * @desc
 */
public class JwtTest {

    //https://www.extlight.com/2018/11/26/JWT-%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/


    @Test
    public void t1(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        System.out.println("---------创建JWT---------");
        Date now = new Date();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("admin")
                .setIssuedAt(now)
                .setIssuer("void1996")
                .claim("id", 123456)
                .claim("name", "wxg")
                .claim("sex", "man")
                .signWith(key);

        String jwt = builder.compact();
        System.out.println("生成的token:"+jwt);

//        String jwt ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYWNhY2IwYi1kN2M4LTQ0OWMtYmUxNS1kM2YyMjQ1ZDc1NjciLCJzdWIiOiJhZG1pbiIsImlhdCI6MTU1Mzc2NzM5NiwiaXNzIjoidm9pZDE5OTYiLCJpZCI6MTIzNDU2LCJuYW1lIjoid3hnIiwic2V4IjoibWFuIn0.AHJZaD6iy5iG1WhboOz7BzT3-eB6J6qf6pzllo5NTfc";

        System.out.println("-------解析jwt-------");

        try{
            Jws<Claims> result = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);

            Claims body = result.getBody();

            System.out.println("载荷-标准中注册的声明 id:" + body.getId());
            System.out.println("载荷-标准中注册的声明 subject:" + body.getSubject());
            System.out.println("载荷-标准中注册的声明 issueAt:" + body.getIssuedAt());


            System.out.println("载荷-公共的声明的 id:" + result.getBody().get("id"));
            System.out.println("载荷-公共的声明的 name:" + result.getBody().get("name"));
            System.out.println("载荷-公共的声明的 sex:" + result.getBody().get("sex"));

        }catch (JwtException e){
            e.printStackTrace();
        }
    }
}
