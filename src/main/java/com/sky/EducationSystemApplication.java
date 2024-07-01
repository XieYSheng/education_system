package com.sky;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 启用缓存
@MapperScan("com/sky/mapper")
@EnableCreateCacheAnnotation
@EnableTransactionManagement
public class EducationSystemApplication {

    // git测试1 2
    public static void main(String[] args) {
        SpringApplication.run(EducationSystemApplication.class, args);
    }

}
