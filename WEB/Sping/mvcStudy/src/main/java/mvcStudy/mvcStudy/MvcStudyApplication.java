package mvcStudy.mvcStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class MvcStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcStudyApplication.class, args);
	}

	@Bean
	InternalResourceViewResolver internalResourceViewResolver(){
		return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
	}
}
