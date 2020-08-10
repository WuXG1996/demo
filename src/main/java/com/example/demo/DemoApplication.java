package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring boot启动类
 * @SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan
 * @Configuration+@Bean：可以替代一个xml配置文件
 * @EnableAutoConfiguration：自动配置spring的上下文
 * @ComponentScan：自动扫描制定包下全部标有@Component注册成bean,也包含子注解@Service,@Repository,@Controller
 * 默认当前层级及以下包的子集所有注解都会被扫描
 * 
 * EnableCaching:开启缓存功能
 * @author void
 */
@EnableCaching
@SpringBootApplication
@EnableAsync
@ServletComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
	}
	
	/**
	 * 针对自定义 Servlet、Filter 和 Listener 的另一种配置，需要implements ServletContextInitializer
	 */
	/*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置 Servlet
        servletContext.addServlet("servletTest",new ServletTest())
                      .addMapping("/servletTest");
        // 配置过滤器
        servletContext.addFilter("timeFilter",new TimeFilter())
                      .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        // 配置监听器
        servletContext.addListener(new ListenerTest());
    }*/
}
