package com.finance;

import com.finance.aspect.MyAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@MapperScan("com.finance.archives.mapper")
@ComponentScan("com.finance")
@EntityScan("com.finance.archives.po")
@EnableJpaRepositories(basePackages = {"com.finance.archives.dao"})
@PropertySource(value = "classpath:jdbc.properties",ignoreResourceNotFound = true)
public class FinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

    @Bean
    public MyAspect initMyAspect(){
        return new MyAspect();
    }


}
