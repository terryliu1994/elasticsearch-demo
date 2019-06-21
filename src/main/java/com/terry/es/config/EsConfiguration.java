package com.terry.es.config;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.terry.es.repository")
public class EsConfiguration  implements WebMvcConfigurer {

    /*@Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client){
        return new ElasticsearchTemplate(client);
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }



}
