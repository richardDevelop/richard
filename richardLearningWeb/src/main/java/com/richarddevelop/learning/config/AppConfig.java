package com.richarddevelop.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.richarddevelop.learning.security.SecurityConfig;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.richarddevelop.learning.*" })
@Import({  AppConfigCommons.class, SecurityConfig.class })
public class AppConfig {

      @Bean
	  public InternalResourceViewResolver getViewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/");
	    viewResolver.setSuffix(".xhtml");
	    viewResolver.setViewClass(JstlView.class);
	    return viewResolver;
	  }
	 
	 

	 
	
}
