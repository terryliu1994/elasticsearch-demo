package com.terry.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
//@EnableElasticsearchRepositories(basePackages = "com.terry.es.repository")
@MapperScan("**.*.mapper")
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

}
