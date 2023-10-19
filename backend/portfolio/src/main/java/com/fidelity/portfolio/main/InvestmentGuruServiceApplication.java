package com.fidelity.portfolio.main;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import java.util.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * The Spring Boot application that launches the WarehouseService 
 * which is a RESTful web service.
 * 
 * @author ROI Instructor Team
 *
 */
@SpringBootApplication
// tell Spring Boot where to scan for annotated components
@ComponentScan(basePackages={
	"com.fidelity.portfolio",
	"com.fidelity.portfolio.dao",
	"com.fidelity.portfolio.exceptions",
	"com.fidelity.portfolio.mapper",
	"com.fidelity.portfolio.restcontroller",
	"com.fidelity.portfolio.services"
})
// tell MyBatis where to scan for mapping interface files
@MapperScan(basePackages="com.fidelity.portfolio.mapper")  
public class InvestmentGuruServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvestmentGuruServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		
	final RestTemplate restTemplate = new RestTemplate();
	   List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	   MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	   converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
	   messageConverters.add(converter);
	   restTemplate.setMessageConverters(messageConverters);

	    return restTemplate;
	}

	/**
	 * This method creates a Logger that can be autowired in other classes:{@code
	 *    @Autowired 
	 *    private Logger logger;
	 }*/
	@Bean
	@Scope("prototype")
	Logger createLogger(InjectionPoint ip) {
	    Class<?> classThatWantsALogger = ip.getField().getDeclaringClass();
	    return LoggerFactory.getLogger(classThatWantsALogger);
	}
}

