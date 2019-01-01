package kui.cams;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("kui.cams.dao")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
    
	/*
	 * @Bean 
	 * public MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory(); //文件最大
	 * factory.setMaxFileSize("10MB"); //KB,MB /// 设置总上传数据总大小
	 * factory.setMaxRequestSize("10MB"); return factory.createMultipartConfig(); }
	 */

}
		