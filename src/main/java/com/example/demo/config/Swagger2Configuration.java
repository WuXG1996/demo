package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 前后端分离时生成的接口文档
 * 默认用localhost:端口/swagger-ui.html进入
 * @author void
 * 2018年4月30日 上午10:59:37
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()) // 配置说明
                .groupName("api")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")) // 拦截的包路径
                .paths(PathSelectors.regex("/*/.*"))// 拦截的接口路径
                .build(); // 创建
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
                .title("接口文档")// 标题
                .description("springboot swagger 接口文档")// 描述
                .termsOfServiceUrl("http://www.wxg123.com")//
                .contact(new Contact("void", "http://www.wxg123.com", "289665247@qq.com"))// 联系
                .version("1.0")// 版本
                .build();
    }
}
