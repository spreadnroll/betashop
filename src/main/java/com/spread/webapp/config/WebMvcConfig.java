package com.spread.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spread.webapp"})
@PropertySource(value = "classpath:application.properties")
public class WebMvcConfig implements WebMvcConfigurer
{
   @Bean
   public InternalResourceViewResolver resolver() 
   {
	   
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setViewClass(JstlView.class);
    	resolver.setPrefix("/WEB-INF/views/");
    	resolver.setSuffix(".jsp");
    
    	return resolver;
   }
   
   private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
           "classpath:/META-INF/resources/", "classpath:/resources/",
           "classpath:/static/", "classpath:/public/" };
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/**")
           .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

   }
   
}
