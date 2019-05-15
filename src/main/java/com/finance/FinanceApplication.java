package com.finance;

import com.finance.aspect.MyAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan("com.finance.archives.mapper")
@EntityScan("com.finance.archives.po")
@EnableJpaRepositories(basePackages = {"com.finance.archives.dao"})
@PropertySource(value = "classpath:jdbc.properties",ignoreResourceNotFound = true)
@ComponentScan("com.finance")
public class FinanceApplication {

    @Autowired
    private RedisTemplate redisTemplate = null;

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

    @Bean
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    @Autowired
    private PlatformTransactionManager transactionManager = null;

    @PostConstruct
    public void  viewTransactionManager(){
        System.out.println(transactionManager.getClass().getName());
    }

    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    private void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }
}


