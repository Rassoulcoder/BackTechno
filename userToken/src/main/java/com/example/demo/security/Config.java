package com.example.demo.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
@Configuration
public class Config implements WebMvcConfigurer  {
	  @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        // Ajoute le MappingJackson2HttpMessageConverter pour gérer JSON
	        converters.add(new MappingJackson2HttpMessageConverter());
	    }
}
